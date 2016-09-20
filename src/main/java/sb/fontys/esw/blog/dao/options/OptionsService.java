package sb.fontys.esw.blog.dao.options;

import sb.fontys.esw.blog.models.options.visibility.Visibility;

/**
 *
 * @author Robert
 */
public interface OptionsService {
    public abstract Visibility getVisibility();
    
    public abstract void setVisibility(Visibility visibility);
}
