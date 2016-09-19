/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.controllers.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import sb.fontys.esw.blog.dao.InSessionPostingService;
import sb.fontys.esw.blog.dao.PostingService;
import sb.fontys.esw.blog.dao.options.InSessionOptionsService;
import sb.fontys.esw.blog.dao.options.OptionsService;

/**
 *
 * @author Robert
 */
public class ControllerSettings {
    public static PostingService getPostingService(HttpSession session) {
        return new InSessionPostingService(session, "blog.postings");
    }

    public static OptionsService getOptionsService(HttpSession session) {
        return new InSessionOptionsService(session, "blog.options");
    }

    public static ModelAndView getMavWithOptions(
            HttpSession session, String viewName) {
        Map<String, Object> map = new HashMap<>();

        map.put("options", getOptionsService(session));

        return new ModelAndView(viewName, map);
    }
}
