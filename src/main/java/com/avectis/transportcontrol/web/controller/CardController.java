package com.avectis.transportcontrol.web.controller;

import com.avectis.transportcontrol.facade.CarFacade;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.view.CarView;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.CargoView;
import com.avectis.transportcontrol.view.DriverView;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
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

    public void setCarFacade(CarFacade carFacade) {
        this.carFacade = carFacade;
    }

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "add":
                    addNewCard(arg0);
                    arg1.sendRedirect("card.std");
                    break;
                case "delete":
                    if (arg0.getParameter("cardId")!=null){
                        deleteCard(Long.decode(arg0.getParameter("cardId")));
                    }
                    arg1.sendRedirect("card.std");
                    break;
            }
        }
        if (arg0.getParameter("view")!=null){
            switch (arg0.getParameter("view")){
                case "newCard":
                    return new ModelAndView("card/newCard", null);
            }
        }
        List<CardView> cardList = cardFacade.getList();
        Map<String,List<CardView>>  data = new HashMap<>();
        data.put("cardList", cardList);
        return new ModelAndView("card/currentCards", data);
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
        card.setCardNumber(Integer.decode(arg0.getParameter("cardNumber")));
        card.setState(0);
        card.setAccessLevel(1);
        card.setCreateDate(new Date());
        cardFacade.add(card);
    }
    private void deleteCard(Long id){
        CardView card;
        card=cardFacade.getCard(id);
        if (card!=null){
            CarView car=null;
            if (card.getCar()!=null)
                car=carFacade.get(card.getCar().getId());
            cardFacade.delete(card);
            if (car!=null){
                carFacade.delete(car);
            }
        }
    }
}
