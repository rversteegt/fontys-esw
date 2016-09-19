/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.models.options;

import java.io.Serializable;
import sb.fontys.esw.blog.models.options.visibility.Visibility;

/**
 *
 * @author Robert
 */
public class Options implements Serializable {
    private Visibility visibility;

    public Options(Visibility visibility) {
        this.visibility = visibility;
    }

    public Visibility getVisibility() {
        return visibility;
    }   
    
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
