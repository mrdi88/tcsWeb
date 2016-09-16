package com.avectis.transportcontrol.web.controller.laboratory;

import com.avectis.transportcontrol.facade.CardFacade;
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
public class ManageSamplesController extends AbstractController {

    private CardFacade cardFacade;

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        List<CardView> cardList = cardFacade.getList();
        Map<String,List<CardView>>  data = new HashMap<>();
        data.put("cardList", cardList);
        return new ModelAndView("laboratory/manageSamples", data);
    }
}
