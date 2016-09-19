/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sb.fontys.esw.blog.models.options.visibility;

/**
 *
 * @author Robert
 */
public enum Visibility {
    SIMPLE, ADVANCED;
    
    public <T> T match(T simple, T advanced) {
        switch(this) {
            case SIMPLE: 
                return simple;
            case ADVANCED:
                return advanced;
            default:
                throw new RuntimeException(
                        "So much for structural pattern matching in Java.");
        }
    }
}
