package sb.fontys.esw.blog.dao;

import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
public class IdentifiablePosting {    
    private final int id;
    
    private final Posting posting;

    IdentifiablePosting(int id, Posting posting) {
        this.posting = posting;
        this.id = id;
    }

    public Posting getPosting() {
        return posting;
    }

    public int getId() {
        return id;
    }
}
