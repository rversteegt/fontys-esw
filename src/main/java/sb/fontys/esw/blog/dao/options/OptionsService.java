/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.dao.options;

import sb.fontys.esw.blog.models.options.Options;
import sb.fontys.esw.blog.models.options.visibility.Visibility;

/**
 *
 * @author Robert
 */
public interface OptionsService {
    public abstract Visibility getVisibility();
    
    public abstract void setVisibility(Visibility visibility);
}
