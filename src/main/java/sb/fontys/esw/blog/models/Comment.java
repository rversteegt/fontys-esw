package sb.fontys.esw.blog.models;

import java.util.Date;

/**
 *
 * @author Robert
 */
public class Comment {
    private final String message;
    
    private final Date timestamp;

    public Comment(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
