package sb.fontys.esw.blog.models;

import com.google.common.collect.ImmutableList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Robert
 */
public class Posting {

    private final String message;
    
    private final String title;

    private final Date timestamp;
    
    private final ImmutableList<Comment> comments;

    public Posting(
        String message,
        String title,
        Date timestamp,
        List<Comment> comments
    ) {
        this.message = message;
        this.title = title;
        this.timestamp = timestamp;
        this.comments = ImmutableList.copyOf(comments);
    }

    public String getMessage() {
        return message;
    }
    
    public String getTitle() {
        return title;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    public ImmutableList<Comment> getComments() {
        return comments;
    }
}
