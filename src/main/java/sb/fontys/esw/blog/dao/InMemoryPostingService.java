/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.dao;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import sb.fontys.esw.blog.models.Posting;

/**
 *
 * @author Robert
 */
public class InMemoryPostingService implements PostingService {
    
    private static final Map<Integer, Posting> POSTINGS = new HashMap<>();
    
    public static IdentifiablePosting addPosting(Posting posting) {
        int nextId = POSTINGS.
                keySet().
                stream().
                reduce(0, (a, b) -> 
                        ( a >= b ? a : b ) + 1);
        
        POSTINGS.put(nextId, posting);
        
        return new IdentifiablePosting(nextId, posting);
    }
    
    public static IdentifiablePosting editPosting(
            IdentifiablePosting oldPosting, Posting newPosting) {
        
        POSTINGS.put(oldPosting.getId(), newPosting);
        
        return new IdentifiablePosting(oldPosting.getId(), newPosting);
    }
    
    public static void deletePosting(IdentifiablePosting posting) {
        POSTINGS.remove(posting.getId());
    }
    
    public static IdentifiablePosting addCommentToPosting(
            IdentifiablePosting idPosting,
            String message
    ) {
        Posting posting = idPosting.getPosting();
        List<String> newComments = new ArrayList<>(posting.getComments());
        
        newComments.add(message);
        
        Posting newPosting = new Posting(
                posting.getMessage(), posting.getMessage(), newComments);
        
        POSTINGS.put(idPosting.getId(), newPosting);
        
        return new IdentifiablePosting(idPosting.getId(), newPosting);
    }
    
    public static List<IdentifiablePosting> all() {
        return POSTINGS.
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
    
    public static Optional<IdentifiablePosting> byId(int id) {
        if (POSTINGS.containsKey(id)) {
            return Optional.of(new IdentifiablePosting(id, POSTINGS.get(id)));
        } else {
            return Optional.empty();
        }
    }
}
