package sb.fontys.esw.blog.dao;

import java.util.List;
import java.util.Optional;
import sb.fontys.esw.blog.dao.IdentifiablePosting;
import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
public interface PostingService {
    
    public static IdentifiablePosting addPosting(Posting posting) {
        throw new UnsupportedOperationException();
    }
    
    public static IdentifiablePosting addResponseToPosting(
            IdentifiablePosting posting,
            String message
    ) {
        throw new UnsupportedOperationException();
    }
    
    public static List<IdentifiablePosting> all() {
        throw new UnsupportedOperationException();
    }
    
    public static Optional<IdentifiablePosting> byId(int id) {
        throw new UnsupportedOperationException();
    }
}
