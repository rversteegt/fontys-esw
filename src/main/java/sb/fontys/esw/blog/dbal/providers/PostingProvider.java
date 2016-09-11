package sb.fontys.esw.blog.dbal.providers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import sb.fontys.esw.blog.dbal.models.IdentifiablePosting;

/**
 *
 * @author Robert
 */
public class PostingProvider {
    
    public static List<IdentifiablePosting> all() {
        return Collections.EMPTY_LIST;
    }
    
    public static Optional<IdentifiablePosting> byId(int id) {
        return Optional.empty();
    }
}
