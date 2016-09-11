package sb.fontys.esw.blog.models;

import java.util.List;

/**
 *
 * @author Robert
 */
public class Posting {
    private final String posting;
    
    private final List<String> comments;

    public Posting(String posting, List<String> comments) {
        this.posting = posting;
        this.comments = comments;
    }

    public String getPosting() {
        return posting;
    }

    public List<String> getComments() {
        return comments;
    }
}
