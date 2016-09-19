/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import sb.fontys.esw.blog.models.Comment;
import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
public class InMemoryPostingService implements PostingService {
    
    protected final Map<Integer, Posting> postings;
    
    private static final InMemoryPostingService INSTANCE =
            new InMemoryPostingService();

    private InMemoryPostingService() {
        postings = new HashMap<>();
    }
    
    protected InMemoryPostingService(Map<Integer, Posting> postings) {
        this.postings = postings;
    }
    
    public static InMemoryPostingService getInstance() {
        return INSTANCE;
    }
        
    @Override
    public IdentifiablePosting addPosting(Posting posting) {
        int nextId = postings.
                keySet().
                stream().
                reduce(0, (a, b) -> 
                        ( a >= b ? a : b ) + 1);
        
        postings.put(nextId, posting);
        
        return new IdentifiablePosting(nextId, posting);
    }
    
    @Override
    public IdentifiablePosting editPosting(
            IdentifiablePosting oldPosting,
            Posting newPosting
    ) {       
        postings.put(oldPosting.getId(), newPosting);
        
        return new IdentifiablePosting(oldPosting.getId(), newPosting);
    }
    
    @Override
    public void deletePosting(IdentifiablePosting posting) {
        postings.remove(posting.getId());
    }
    
    @Override
    public IdentifiablePosting addCommentToPosting(
            IdentifiablePosting idPosting,
            Comment comment
    ) {
        Posting posting = idPosting.getPosting();
        List<Comment> newComments = new ArrayList<>(posting.getComments());
        
        newComments.add(comment);
        
        Posting newPosting = new Posting(
                posting.getMessage(), posting.getMessage(), newComments);
        
        postings.put(idPosting.getId(), newPosting);
        
        return new IdentifiablePosting(idPosting.getId(), newPosting);
    }
    
    @Override
    public List<IdentifiablePosting> all() {
        return postings.
                entrySet().
                stream().
                map(entry -> 
                        new IdentifiablePosting(
                                entry.getKey(),
                                entry.getValue()
                        )
                ).
                collect(Collectors.toList());
    }
    
    @Override
    public Optional<IdentifiablePosting> byId(int id) {
        if (postings.containsKey(id)) {
            return Optional.of(new IdentifiablePosting(id, postings.get(id)));
        } else {
            return Optional.empty();
        }
    }
}
