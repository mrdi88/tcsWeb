package com.avectis.transportcontrol.web.controller;

import com.avectis.transportcontrol.facade.CarFacade;
import com.avectis.transportcontrol.view.CarView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<CarView> carList = carFacade.getList(null, null);
        Map<String,List<CarView>>  data = new HashMap<>();
        data.put("carList", carList);
        return new ModelAndView("car/currentCars", data);
    }
}
