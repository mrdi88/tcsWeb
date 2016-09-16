package com.avectis.transportcontrol.web.controller.laboratory;

import com.avectis.transportcontrol.control.scanner.CardScannerListener;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.facade.ScannerFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.SampleView;
import com.avectis.transportcontrol.web.controller.card.CardController;
import com.avectis.transportcontrol.web.controller.card.CardNumberClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
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
public class LaboratoryController extends AbstractController {

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

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setScannerFacade(ScannerFacade scannerFacade) {
        this.scannerFacade = scannerFacade;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        if (arg0.getParameter("cmd")!=null){
            Map<String,String>  data;
            switch (arg0.getParameter("cmd")){
                case "assignSample":
                    data = new HashMap<>();
                    if (arg0.getParameter("cardId")!=null && 
                            arg0.getParameter("cardId").length()>0 &&
                            arg0.getParameter("sampleName")!=null && 
                            arg0.getParameter("sampleName").length()>0){
                        CardView card = cardFacade.getCard(Long.decode(arg0.getParameter("cardId")));
                        if (card!=null){
                            SampleView sample=new SampleView();
                            sample.setName(arg0.getParameter("sampleName"));
                            card.getCar().getCargo().setSample(sample);
                            cardFacade.update(card);
                            data.put("result", "true");
                        }
                    } else {
                        data.put("result", "false");
                    }
                    return new ModelAndView("laboratory/resultJSON", data);
                case "getExistCardData": //by scanner
                    data = new HashMap<>();
                    CardNumberClass ecn = getCardNumber();
                    CardView card = cardFacade.getCardByNumber(ecn.getCardNumber());
                    ObjectMapper mapper = new ObjectMapper();
                    String cardJson = mapper.writeValueAsString(card);
                    data.put("card", cardJson);
                    return new ModelAndView("laboratory/existCardDataJSON", data);
                default:
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("laboratory/resultJSON", data);
            }
        }
        arg1.sendRedirect("laboratory/manage");
        return null;
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
