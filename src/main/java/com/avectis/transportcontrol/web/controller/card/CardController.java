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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
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
                    Map<String,String>  addData = new HashMap<>();
                    if (validNewCardData(arg0)){ //check parameters
                        addNewCard(arg0);
                        addData.put("result", "true");
                    } else {
                        addData.put("result", "false");
                    }
                    return new ModelAndView("card/resultJSON", addData);
                case "delete":
                    Map<String,String>  deleteData = new HashMap<>();
                    if (arg0.getParameter("cardId")!=null){
                        deleteCard(Long.decode(arg0.getParameter("cardId")));
                        deleteData.put("result", "true");
                    }else {
                        deleteData.put("result", "false");
                    }
                    return new ModelAndView("card/resultJSON", deleteData);
                case "getNewCardNumber": //by scanner
                    CardNumberClass ncn = getCardNumber();
                    if (cardFacade.getCardByNumber(ncn.getCardNumber())!=null){
                        ncn.setCardNumber("0");
                    }
                    Map<String,String>  data = new HashMap<>();
                    data.put("cardNumber", ncn.getCardNumber());
                    return new ModelAndView("card/newCardNumberJSON", data);
                case "getExistCardData": //by scanner
                    Map<String,String>  edata = new HashMap<>();
                    CardNumberClass ecn = getCardNumber();
                    CardView card = cardFacade.getCardByNumber(ecn.getCardNumber());
                    ObjectMapper mapper = new ObjectMapper();
                    String cardJson = mapper.writeValueAsString(card);
                    edata.put("card", cardJson);
                    return new ModelAndView("card/existCardDataJSON", edata);
                default:
                    Map<String,String>  defData = new HashMap<>();
                    defData.put("result", "cmd not found");
                    return new ModelAndView("card/resultJSON", defData);
            }
        }
        arg1.sendRedirect("card/list");
        return null;
    }
    private void addNewCard(HttpServletRequest arg0){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        CarView carv= new CarView();
        DriverView dv=new DriverView();
        CargoView cargov=new CargoView();
        dv.setMobileNumber(arg0.getParameter("mobileNumber"));
        dv.setName(arg0.getParameter("name"));
        dv.setOrganization(arg0.getParameter("organization"));
        carv.setFirstNumber(arg0.getParameter("firstNumber"));
        carv.setSecondNumber(arg0.getParameter("secondNumber"));
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
        if (arg0.getParameter("name")==null || arg0.getParameter("name").length()<1){
            return false;
        }
        if (arg0.getParameter("organization")==null ){
            return false;
        }
        if (arg0.getParameter("firstNumber")==null || arg0.getParameter("firstNumber").length()<1){
            return false;
        }
        if (arg0.getParameter("secondNumber")==null ){
            return false;
        }
        return true;
    }
    private void deleteCard(Long id){
        CardView card;
        card=cardFacade.getCard(id);
        if (card!=null){
            queueFacade.deleteCardFromQueues(card);
            cardFacade.delete(card);
        }
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
