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
public class InMemoryOptionsService implements OptionsService {
    protected Options options;

    public InMemoryOptionsService(Options options) {
        this.options = options;
    }

    @Override
    public Visibility getVisibility() {
        return options.getVisibility();
    }

    @Override
    public void setVisibility(Visibility visibility) {
        this.options = new Options(visibility);
    }
}
