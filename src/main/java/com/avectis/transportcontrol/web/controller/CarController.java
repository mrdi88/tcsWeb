package com.avectis.transportcontrol.web.controller;

import com.avectis.transportcontrol.facade.CarFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 *
 * @author ASaburov
 */
public class CarController extends AbstractController {

    private CarFacade carFacade;

    public void setCarFacade(CarFacade carFacade) {
        this.carFacade = carFacade;
    }
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        return new ModelAndView("car/currentCars", null);
    }
}
