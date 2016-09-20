package sb.fontys.esw.blog.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sb.fontys.esw.blog.controllers.settings.ControllerSettings;
import sb.fontys.esw.blog.dao.IdentifiablePosting;
import sb.fontys.esw.blog.models.Comment;
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
        
        ModelAndView mav = ControllerSettings.getMavWithOptions(
                request.getSession(), "blog/postings/overview.twig");
        
        List<IdentifiablePosting> postings = 
                ControllerSettings.getPostingService(
                        request.getSession()).all();

        mav.addObject("postings", postings);
                
        return mav;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showDetail(
            @PathVariable("id") String id,
            HttpServletRequest request
    ) {
        try {
            Optional<IdentifiablePosting> posting = 
                    ControllerSettings.getPostingService(
                            request.getSession()).byId(Integer.parseInt(id));
            
            if (posting.isPresent()) {
                ModelAndView mav = ControllerSettings.getMavWithOptions(
                        request.getSession(), "blog/postings/detail.twig");
                
                mav.addObject("posting", posting.get());
                
                return mav;
            } else {
                return new ModelAndView("redirect:/404");
            }
        } catch (NumberFormatException e) {
            return new ModelAndView("redirect:/404");
        }
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPosting(
            @RequestParam(value="title", required=false) String iMessage,
            @RequestParam(value="message", required=false) String iTitle,
            HttpServletRequest request
    ) {
        Optional<String> title = Optional.ofNullable(iMessage);
        Optional<String> message = Optional.ofNullable(iTitle);

        if (!title.isPresent() || !message.isPresent()) {
            return new ModelAndView("redirect:/505");
        } else {
            Posting newPosting = new Posting(
                    message.get(), title.get(), new ArrayList());

            IdentifiablePosting idPosting =
                    ControllerSettings.getPostingService(
                            request.getSession()).addPosting(newPosting);

            return new ModelAndView(
                    "redirect:/app/blog/" + String.valueOf(idPosting.getId())
            );
        }
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editPosting(
            @PathVariable("id") String id,
            @RequestParam(value="title", required=false) String iMessage,
            @RequestParam(value="message", required=false) String iTitle,
            HttpServletRequest request
    ) {
        Optional<String> title = Optional.ofNullable(iMessage);
        Optional<String> message = Optional.ofNullable(iTitle);
        
        Optional<IdentifiablePosting> idPosting =
                    ControllerSettings.getPostingService(request.getSession()).
                            byId(Integer.parseInt(id));

        if (!title.isPresent() || !message.isPresent() || 
                !idPosting.isPresent()) {
            return new ModelAndView("redirect:/505");
        } else {
            Posting newPosting = new Posting(message.get(), title.get(), 
                    idPosting.get().getPosting().getComments());
            
            IdentifiablePosting newIdPosting =
                    ControllerSettings.getPostingService(request.getSession()).
                            editPosting(idPosting.get(), newPosting);

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
                    ControllerSettings.getPostingService(request.getSession()).
                            byId(Integer.parseInt(id));

        if (!idPosting.isPresent()) {
            return new ModelAndView("redirect:/505");
        } else {
            ControllerSettings.getPostingService(request.getSession()).
                    deletePosting(idPosting.get());

            return new ModelAndView("redirect:/app/blog/");
        }
    }    
    
    @RequestMapping(
            value = "/addCommentToPosting/{id}", method = RequestMethod.POST)
    public ModelAndView addCommentToPosting(
            @PathVariable("id") String id,
            @RequestParam(value="message", required=false) String iMessage,
            HttpServletRequest request
    ) {
        try {
            Optional<String> message = Optional.ofNullable(iMessage);

            Optional<IdentifiablePosting> idPosting = 
                    ControllerSettings.getPostingService(request.getSession()).
                            byId(Integer.parseInt(id));

            if (!message.isPresent() || !idPosting.isPresent()) {
                return new ModelAndView("redirect:/505");
            } else {
                ControllerSettings.getPostingService(request.getSession()).
                        addCommentToPosting(
                                idPosting.get(), 
                                new Comment(message.get(), new Date())
                        );

                return new ModelAndView(
                        "redirect:/app/blog/" + String.valueOf(
                                idPosting.get().getId())
                );
            }
        } catch (NumberFormatException e) {
            return new ModelAndView("redirect:/505");
        }
    }
}    
    