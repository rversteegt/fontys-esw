package sb.fontys.esw.blog.dao;

import java.util.List;
import java.util.Optional;
import sb.fontys.esw.blog.models.Comment;
import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
public interface PostingService {
    
    public IdentifiablePosting addPosting(Posting posting);
    
    public List<IdentifiablePosting> all();
    
    public Optional<IdentifiablePosting> byId(int id);
    
    public IdentifiablePosting editPosting(
            IdentifiablePosting oldPosting,
            Posting newPosting
    );
    
    public void deletePosting(IdentifiablePosting posting);
    
    public IdentifiablePosting addCommentToPosting(
            IdentifiablePosting idPosting,
            Comment comment
    );
}
