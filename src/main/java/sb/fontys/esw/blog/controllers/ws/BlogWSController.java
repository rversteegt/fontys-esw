package sb.fontys.esw.blog.controllers.ws;

import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sb.fontys.esw.blog.controllers.settings.ControllerSettings;
import sb.fontys.esw.blog.dao.IdentifiablePosting;
import sb.fontys.esw.blog.models.Comment;

/**
 *
 * @author Robert
 */
@Controller
@RequestMapping("/api/blog")
public class BlogWSController {
    
    @RequestMapping(
            value = "/addCommentToPosting/{id}",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public @ResponseBody IdentifiablePosting addCommentToPosting(
            @PathVariable("id") String id,
            @RequestParam(value="message", required=false) String iMessage,
            HttpServletRequest request
    ) {
        try {System.out.println(id);System.out.println(iMessage);
            Optional<String> message = Optional.ofNullable(iMessage);

            Optional<IdentifiablePosting> idPosting =
                    ControllerSettings.getPostingService(request.getSession()).
                            byId(Integer.parseInt(id));

            if (!message.isPresent() || !idPosting.isPresent()) {
                return null;
            } else {
                IdentifiablePosting newPosting = ControllerSettings.
                        getPostingService(request.getSession()).
                        addCommentToPosting(
                                idPosting.get(),
                                new Comment(message.get(), new Date())
                        );

                return newPosting;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
