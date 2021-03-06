package com.avectis.transportcontrol.web.controller.archive;

import com.avectis.transportcontrol.facade.CarFacade;
import com.avectis.transportcontrol.view.CarView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Dima
 */
public class ArchiveController extends AbstractController {
    static final Logger logger = LogManager.getLogger(ArchiveController.class.getName());

    private CarFacade carFacade;

    public void setCarFacade(CarFacade carFacade) {
        this.carFacade = carFacade;
    }
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        //do cmd
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "getCars": 
                    return doGetCarsCmd(arg0);
                default:
                    Map<String,String>  data;
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("archive/json/resultJSON", data);
            }
        }
        //do action
        String action = getAction(arg0.getRequestURI());
        switch(action){
            case "cars":
                return doCarsAction(arg0);
        }
        arg1.sendRedirect("archive/cars");
        return null;
    }
    private ModelAndView doGetCarsCmd(HttpServletRequest arg0) throws JsonProcessingException{
        Map<String,String>  data = new HashMap<>();
        try{
            ObjectMapper mapper;
            List<CarView> cars= getCarsByPeriod(arg0);
            mapper = new ObjectMapper();
            String carsJson = mapper.writeValueAsString(cars);
            data.put("cars", carsJson);
        }catch(Exception ex){
            logger.error("doGetCarsCmd: "+ex.getMessage());
        }
        return new ModelAndView("archive/json/carsJSON", data);
    } 
    private ModelAndView doCarsAction(HttpServletRequest arg0){
        return new ModelAndView("archive/carsArchive", null);
    }
    private String getAction(String url){
        String action="";
        String[] URLparts = url.split("/", 0);
        if (URLparts.length==4){
            action=URLparts[3];
        }
        return action;
    }
    private List<CarView> getCarsByPeriod(HttpServletRequest arg0){
        Date from = new Date(arg0.getParameter("from"));
        Date to = new Date(arg0.getParameter("to"));
        String carNumber=arg0.getParameter("carNumber");
        List<CarView> cars = null;
        if (carNumber == null || carNumber.length()==0 ) {
            cars = carFacade.getList(from, to);
        } else {
            cars = carFacade.getList(from, to, carNumber);
        }
        return cars;
    }
}
