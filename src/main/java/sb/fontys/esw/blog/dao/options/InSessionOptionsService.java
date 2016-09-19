/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.dao.options;

import java.io.Serializable;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import sb.fontys.esw.blog.models.options.Options;
import sb.fontys.esw.blog.models.options.visibility.Visibility;

/**
 *
 * @author Robert
 */
public class InSessionOptionsService extends InMemoryOptionsService
        implements Serializable {
    
    public InSessionOptionsService(HttpSession session, String key) {
        super(
            Optional.ofNullable(session.getAttribute(key)).
                    map(o -> ((InSessionOptionsService) o).options).
                    orElse(new Options(Visibility.SIMPLE))
        );
        
        session.setAttribute(key, this);
    }
}
