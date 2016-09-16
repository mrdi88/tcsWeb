package com.avectis.transportcontrol.web.controller.laboratory;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author ASaburov
 */
public class AssignSampleController extends AbstractController {
  
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        Map<String,String>  data = new HashMap<>();
        return new ModelAndView("laboratory/assignSample", data);
    }
}
