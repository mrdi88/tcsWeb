package com.avectis.transportcontrol.web.controller.card;

import com.avectis.transportcontrol.view.CardView;
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
public class ManageCardController extends AbstractController {
  
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        Map<String,List<CardView>>  data = new HashMap<>();
        return new ModelAndView("card/manageCard", data);
    }
}
