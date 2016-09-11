package sb.fontys.esw.blog.dbal.models;

import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
public class IdentifiablePosting {
    private final Posting posting;
    
    private final int id;

    public IdentifiablePosting(Posting posting, int id) {
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
