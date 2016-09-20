package sb.fontys.esw.blog.controllers;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sb.fontys.esw.blog.controllers.settings.ControllerSettings;
import sb.fontys.esw.blog.models.options.visibility.Visibility;

/**
 *
 * @author Robert
 */
@Controller
@RequestMapping("/blog/options")
public class OptionsController {    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView setOptions(HttpServletRequest request) {
        Optional<String> visibility =
                Optional.ofNullable(request.getParameter("visibility"));
        Optional<String> redirect =
                Optional.ofNullable(request.getParameter("redirect")).
                        map(s -> "redirect:" + s);
        
        if (visibility.isPresent()) {
            try {
                ControllerSettings.getOptionsService(request.getSession()).
                        setVisibility(Visibility.valueOf(visibility.get()));
            } catch (IllegalArgumentException e) {                
            }
        }
        
        ModelAndView mav = ControllerSettings.getMavWithOptions(
                request.getSession(),
                redirect.orElse("blog/postings/overview.twig")
        );
                
        return mav;
    }
}
