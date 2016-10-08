package com.avectis.transportcontrol.web.controller.card;

import com.avectis.transportcontrol.control.scanner.CardScannerListener;
import com.avectis.transportcontrol.facade.CarFacade;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.facade.ScannerFacade;
import com.avectis.transportcontrol.view.CarView;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.CargoView;
import com.avectis.transportcontrol.view.DriverView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author ASaburov
 */
public class CardController extends AbstractController {

    private CarFacade carFacade;
    private CardFacade cardFacade;
    private QueueFacade queueFacade;
    private ScannerFacade scannerFacade;
    private String cardScannerName;

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
        String action = getAction(arg0.getRequestURI().toString());
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
        Map<String,String>  addData = new HashMap<>();
        if (validNewCardData(arg0)){ //check parameters
            addNewCard(arg0);
            addData.put("result", "true");
        } else {
            addData.put("result", "false");
        }
        return new ModelAndView("card/json/resultJSON", addData);
    }
    private ModelAndView doDeleteCmd(HttpServletRequest arg0){
        Map<String,String>  deleteData = new HashMap<>();
        if (arg0.getParameter("cardId")!=null){
            deleteCard(Long.decode(arg0.getParameter("cardId")));
            deleteData.put("result", "true");
        }else {
            deleteData.put("result", "false");
        }
        return new ModelAndView("card/json/resultJSON", deleteData);
    }
    private ModelAndView doGetNewCardNumberCmd(HttpServletRequest arg0){
        CardNumberClass ncn = getCardNumber();
        if (cardFacade.getCardByNumber(ncn.getCardNumber())!=null){
            ncn.setCardNumber("0");
        }
        Map<String,String>  data = new HashMap<>();
        data.put("cardNumber", ncn.getCardNumber());
        return new ModelAndView("card/json/newCardNumberJSON", data);
    }
    private ModelAndView doGetExistCardDataCmd(HttpServletRequest arg0) throws JsonProcessingException{
        Map<String,String>  edata = new HashMap<>();
        CardNumberClass ecn = getCardNumber();
        CardView card = cardFacade.getCardByNumber(ecn.getCardNumber());
        ObjectMapper mapper = new ObjectMapper();
        String cardJson = mapper.writeValueAsString(card);
        edata.put("card", cardJson);
        return new ModelAndView("card/json/existCardDataJSON", edata);
    }
    private ModelAndView doManageAction(HttpServletRequest arg0){
        Map<String,List<CardView>>  data = new HashMap<>();
        return new ModelAndView("card/manageCard", data);
    }
    private ModelAndView doListAction(HttpServletRequest arg0){
        List<CardView> cardList = cardFacade.getList();
        Map<String,List<CardView>>  data = new HashMap<>();
        data.put("cardList", cardList);
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
    private void deleteCard(Long id){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        CardView card;
        card=cardFacade.getCard(id);
        if (card!=null){
            card.getCar().setLeaveDate(new Date());
            cardFacade.update(card);
            queueFacade.deleteCardFromQueues(card);
            cardFacade.delete(card);
        }
    }
    private void addNewCard(HttpServletRequest arg0){
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
                Logger.getLogger(CardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cardNumber;
    }
}
