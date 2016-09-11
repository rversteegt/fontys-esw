package sb.fontys.esw.blog.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sb.fontys.esw.blog.dao.IdentifiablePosting;
import sb.fontys.esw.blog.dao.InMemoryPostingService;
import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showOverview(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("blog/postings/overview.twig");
        
        List<IdentifiablePosting> postings = InMemoryPostingService.all();
        
        mav.addObject("postings", postings);
                
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showOverviewAdvanced(HttpServletRequest request) {
        ModelAndView mav = showOverview(request);
        
        mav.setViewName("blog/postings/advanced/overview.twig");
                
        return mav;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPosting(HttpServletRequest request) {
        Optional<String> title =
                Optional.ofNullable(request.getParameter("title"));
        Optional<String> message =
                Optional.ofNullable(request.getParameter("message"));

        if (!title.isPresent() || !message.isPresent()) {
            return new ModelAndView("redirect:/505");
        } else {
            Posting newPosting = new Posting(
                    message.get(), title.get(), new ArrayList());

            IdentifiablePosting idPosting =
                    InMemoryPostingService.addPosting(newPosting);

            return new ModelAndView(
                    "redirect:/app/blog/" + String.valueOf(idPosting.getId())
            );
        }
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editPosting(
        @PathVariable("id") String id,
        HttpServletRequest request
    ) {
        Optional<String> title =
                Optional.ofNullable(request.getParameter("title"));
        Optional<String> message =
                Optional.ofNullable(request.getParameter("message"));
        
        Optional<IdentifiablePosting> idPosting =
                    InMemoryPostingService.byId(Integer.parseInt(id));

        if (!title.isPresent() || !message.isPresent() || 
                !idPosting.isPresent()) {
            return new ModelAndView("redirect:/505");
        } else {
            Posting newPosting = new Posting(message.get(), title.get(), 
                    idPosting.get().getPosting().getComments());
            
            IdentifiablePosting newIdPosting =
                    InMemoryPostingService.editPosting(
                            idPosting.get(), newPosting);

            return new ModelAndView(
                    "redirect:/app/blog/" + String.valueOf(newIdPosting.getId())
            );
        }
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deletePosting(
        @PathVariable("id") String id,
        HttpServletRequest request
    ) {        
        Optional<IdentifiablePosting> idPosting =
                    InMemoryPostingService.byId(Integer.parseInt(id));

        if (!idPosting.isPresent()) {
            return new ModelAndView("redirect:/505");
        } else {
            InMemoryPostingService.deletePosting(idPosting.get());

            return new ModelAndView("redirect:/app/blog/");
        }
    }    
    
    @RequestMapping(
            value = "/addMessageToPosting/{id}", method = RequestMethod.POST)
    public ModelAndView addMessageToPosting(
        @PathVariable("id") String id,
        HttpServletRequest request
    ) {
        try {
            Optional<String> message =
                Optional.ofNullable(request.getParameter("message"));

            Optional<IdentifiablePosting> idPosting =
                    InMemoryPostingService.byId(Integer.parseInt(id));

            if (!message.isPresent() || !idPosting.isPresent()) {
                return new ModelAndView("redirect:/505");
            } else {
                InMemoryPostingService.addCommentToPosting(
                        idPosting.get(), message.get());

                return new ModelAndView(
                        "redirect:/app/blog/" + String.valueOf(
                                idPosting.get().getId())
                );
            }
        } catch (NumberFormatException e) {
            return new ModelAndView("redirect:/505");
        }
    }    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showDetail(
        @PathVariable("id") String id,
        HttpServletRequest request
    ) {
        try {
            Optional<IdentifiablePosting> posting = 
                    InMemoryPostingService.byId(Integer.parseInt(id));
            
            if (posting.isPresent()) {
                ModelAndView mav = new ModelAndView("blog/postings/detail.twig");
                
                mav.addObject("posting", posting.get().getPosting());
                mav.addObject("id", posting.get().getId());
                
                return mav;
            } else {
                return new ModelAndView("redirect:/404");
            }
        } catch (NumberFormatException e) {
            return new ModelAndView("redirect:/404");
        }
    }
}    
    