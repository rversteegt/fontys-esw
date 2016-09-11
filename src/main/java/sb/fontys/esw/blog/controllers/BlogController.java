package sb.fontys.esw.blog.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sb.fontys.esw.blog.dbal.models.IdentifiablePosting;
import sb.fontys.esw.blog.dbal.providers.PostingProvider;
import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
@Controller
@RequestMapping("/")
public class BlogController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showOverview(
        HttpServletRequest request, 
        HttpServletResponse response
    ) {
        ModelAndView mav = new ModelAndView("blog/postings/overview.twig");
        
        List<IdentifiablePosting> postings = PostingProvider.all();
        
        mav.addObject("postings", postings);
                
        return mav;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showDetail(
        @PathVariable("id") String id,
        HttpServletRequest request, 
        HttpServletResponse response
    ) {
        try {
            Optional<IdentifiablePosting> posting = 
                    PostingProvider.byId(Integer.parseInt(id));
            
            if (posting.isPresent()) {
                ModelAndView mav = new ModelAndView("blog/postings/detail.twig");
                
                mav.addObject("postings", posting.get());
                
                return mav;
            } else {
                return new ModelAndView("redirect:/404");
            }
        } catch (NumberFormatException e) {
            return new ModelAndView("redirect:/404");
        }
    }
}    
    