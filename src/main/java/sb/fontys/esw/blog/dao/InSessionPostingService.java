/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import sb.fontys.esw.blog.models.Posting;


public class InSessionPostingService extends InMemoryPostingService {

    public InSessionPostingService(HttpSession session, String key) {
        super(
            (Map<Integer, Posting>)
                    Optional.ofNullable(session.getAttribute(key)).
                            orElse(new HashMap<>())
        );
        
        session.setAttribute(key, postings);
    }
}
