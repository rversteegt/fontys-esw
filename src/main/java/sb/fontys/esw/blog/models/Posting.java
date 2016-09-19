package sb.fontys.esw.blog.models;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 *
 * @author Robert
 */
public class Posting {    
    private final String message;
    
    private final String title;
    
    private final ImmutableList<Comment> comments;

    public Posting(String message, String title, List<Comment> comments) {
        this.title = title;
        this.message = message;
        this.comments = ImmutableList.copyOf(comments);
    }

    public String getMessage() {
        return message;
    }
    
    public String getTitle() {
        return title;
    }
    
    public ImmutableList<Comment> getComments() {
        return comments;
    }
}
