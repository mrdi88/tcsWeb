package com.avectis.transportcontrol.web.controller.card;

import com.avectis.transportcontrol.control.barrier.Barrier;
import com.avectis.transportcontrol.control.scanner.CardScannerListener;
import com.avectis.transportcontrol.exception.ConnectionFailException;
import com.avectis.transportcontrol.facade.BarrierFacade;
import com.avectis.transportcontrol.facade.CarFacade;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.facade.ScannerFacade;
import com.avectis.transportcontrol.view.CarView;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.CargoView;
import com.avectis.transportcontrol.view.DriverView;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author Dima
 */
public class CardController extends AbstractController {
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CardController.class.getName());

    private CarFacade carFacade;
    private CardFacade cardFacade;
    private QueueFacade queueFacade;
    private ScannerFacade scannerFacade;
    private String cardScannerName;
    //control barrier
    private BarrierFacade barrierFacade;
    private String entranceBarrierName;
    private String exitBarrierName;

    public void setBarrierFacade(BarrierFacade barrierFacade) {
        this.barrierFacade = barrierFacade;
    }

    public void setEntranceBarrierName(String entranceBarrierName) {
        this.entranceBarrierName = entranceBarrierName;
    }

    public void setExitBarrierName(String exitBarrierName) {
        this.exitBarrierName = exitBarrierName;
    }

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    public String getCardScannerName() {
        return cardScannerName;
    }

    public void setCardScannerName(String newCardScannerName) {
        this.cardScannerName = newCardScannerName;
    }

    public void setCarFacade(CarFacade carFacade) {
        this.carFacade = carFacade;
    }

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setScannerFacade(ScannerFacade scannerFacade) {
        this.scannerFacade = scannerFacade;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "add":
                    return doAddCmd(arg0);
                case "delete":
                    return doDeleteCmd(arg0);
                case "getNewCardNumber": //by scanner
                    return doGetNewCardNumberCmd(arg0);
                case "getExistCardData": //by scanner
                    return doGetExistCardDataCmd(arg0);
                default:
                    Map<String,String>  defData = new HashMap<>();
                    defData.put("result", "cmd not found");
                    return new ModelAndView("card/json/resultJSON", defData);
            }
        }
        //do action
        String action = getAction(arg0.getRequestURI());
        switch(action){
            case "manage":
                return doManageAction(arg0);
            case "list":
                return doListAction(arg0);
        }
        arg1.sendRedirect("card/list");
        return null;
    }
    private ModelAndView doAddCmd(HttpServletRequest arg0){
        Map<String,String>  result = new HashMap<>();
        try{
            if (validNewCardData(arg0)){ //check parameters
                addNewCard(arg0);
                result.put("result", "true");
            } else {
                result.put("result", "false");
            }
        }catch(Exception ex){
            result.put("result", "false");
            logger.error("doAddCmd: "+ex.getMessage());
        }
        return new ModelAndView("card/json/resultJSON", result);
    }
    private ModelAndView doDeleteCmd(HttpServletRequest arg0){
        Map<String,String>  result = new HashMap<>();
        try{
            if (arg0.getParameter("cardId")!=null){
                deleteCard(Long.decode(arg0.getParameter("cardId")));
                result.put("result", "true");
            }else {
                result.put("result", "false");
            }
        }catch(Exception ex){
            result.put("result", "false");
            logger.error("doDeleteCmd: "+ex.getMessage());
        }
        return new ModelAndView("card/json/resultJSON", result);
    }
    private ModelAndView doGetNewCardNumberCmd(HttpServletRequest arg0){
        Map<String,String>  data = new HashMap<>();
        try{
            CardNumberClass ncn = getCardNumber();
            if (cardFacade.getCardByNumber(ncn.getCardNumber())!=null){
                ncn.setCardNumber("0");
            }
            data.put("cardNumber", ncn.getCardNumber());
        }catch(Exception ex){
            logger.error("doGetNewCardNumberCmd: "+ex.getMessage());
        }
        return new ModelAndView("card/json/newCardNumberJSON", data);
    }
    private ModelAndView doGetExistCardDataCmd(HttpServletRequest arg0){
        Map<String,String>  data = new HashMap<>();
        try{
            CardNumberClass ecn = getCardNumber();
            CardView card = cardFacade.getCardByNumber(ecn.getCardNumber());
            ObjectMapper mapper = new ObjectMapper();
            String cardJson = mapper.writeValueAsString(card);
            data.put("card", cardJson);
        }catch(Exception ex){
            logger.error("doGetExistCardDataCmd: "+ex.getMessage());
        }
        return new ModelAndView("card/json/existCardDataJSON", data);
    }
    private ModelAndView doManageAction(HttpServletRequest arg0){
        Map<String,List<CardView>>  data = new HashMap<>();
        return new ModelAndView("card/manageCard", data);
    }
    private ModelAndView doListAction(HttpServletRequest arg0){
        Map<String,List<CardView>>  data = new HashMap<>();
        try{
            List<CardView> cardList = cardFacade.getList();
            data.put("cardList", cardList);
        }catch(Exception ex){
            logger.error("doListAction: "+ex.getMessage());
        }
        return new ModelAndView("card/listCard", data);
    }
    private String getAction(String url){
        String action="";
        String[] URLparts = url.split("/", 0);
        if (URLparts.length==4){
            action=URLparts[3];
        }
        return action;
    }
    private void deleteCard(Long id) throws ConnectionFailException{
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        CardView card;
        card=cardFacade.getCard(id);
        if (card!=null){
            card.getCar().setLeaveDate(new Date());
            cardFacade.update(card);
            queueFacade.deleteCardFromQueues(card);
            cardFacade.delete(card);
        }
        //open exit badrrier
        openExitBarrier();
    }
    private void addNewCard(HttpServletRequest arg0) throws ConnectionFailException{
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        CarView carv= new CarView();
        DriverView dv=new DriverView();
        CargoView cargov=new CargoView();
        dv.setMobileNumber(arg0.getParameter("mobileNumber"));
        dv.setFirstName(arg0.getParameter("firstName"));
        dv.setLastName(arg0.getParameter("lastName"));
        dv.setOrganization(arg0.getParameter("organization"));
        carv.setCarNumber(arg0.getParameter("carNumber"));
        carv.setTtnNumber(arg0.getParameter("ttnNumber"));
        carv.setNomenclature(arg0.getParameter("nomenclature"));
        carv.setCargo(cargov);
        carv.setDriver(dv);
        carv.setCreateDate(new Date());
        carv.setId(carFacade.add(carv));
        CardView card=new CardView();
        card.setCar(carv);
        card.setCardNumber(arg0.getParameter("cardNumber"));
        card.setState(0);
        card.setAccessLevel(1);
        card.setCreateDate(new Date());
        cardFacade.add(card);
        //open entrance badrrier
        openEnranceBarrier();
    }
    private boolean validNewCardData(HttpServletRequest arg0){
        if (arg0.getParameter("cardNumber")==null || arg0.getParameter("cardNumber").length()<1){
            return false;
        }
        if (cardFacade.getCardByNumber(arg0.getParameter("cardNumber"))!=null){
            return false;
        }
        if (arg0.getParameter("mobileNumber")==null ){
            return false;
        }
        if (arg0.getParameter("firstName")==null || arg0.getParameter("firstName").length()<1){
            return false;
        }
        if (arg0.getParameter("lastName")==null || arg0.getParameter("lastName").length()<1){
            return false;
        }
        if (arg0.getParameter("nomenclature")==null || arg0.getParameter("nomenclature").length()<1){
            return false;
        }
        if (arg0.getParameter("organization")==null ){
            return false;
        }
        if (arg0.getParameter("carNumber")==null || arg0.getParameter("carNumber").length()<1){
            return false;
        }
        if (arg0.getParameter("ttnNumber")==null ){
            return false;
        }
        return true;
    }
    private CardNumberClass getCardNumber(){
        CardNumberClass cardNumber= new CardNumberClass("0");
        synchronized(cardNumber){
            CardScannerListener cardListener=new CardScannerListener(){
                CardNumberClass cardNumber;
                public CardScannerListener setCardNumber(CardNumberClass cardNumber) {
                    this.cardNumber=cardNumber;
                    return this;
                }
                @Override
                public void onCardLogined(String CardNumberHEX,String CardNumberDEC){
                    synchronized(cardNumber){
                        cardNumber.setCardNumber(CardNumberDEC);
                        cardNumber.notifyAll();
                    }
                }
            }.setCardNumber(cardNumber);
            scannerFacade.getElementById(cardScannerName).addListener(cardListener);
            try {
                cardNumber.wait(60000);
                scannerFacade.getElementById(cardScannerName).removeListener(cardListener);
            } catch (InterruptedException ex) {
                logger.error("getCardNumber error msg: "+ex.getMessage());
            }
        }
        return cardNumber;
    }
    private void openEnranceBarrier() throws ConnectionFailException{
        Barrier barrier=barrierFacade.GetElementById(entranceBarrierName);
        barrier.Open();
    }
    private void openExitBarrier() throws ConnectionFailException{
        Barrier barrier=barrierFacade.GetElementById(exitBarrierName);
        barrier.Open();
    }
}
