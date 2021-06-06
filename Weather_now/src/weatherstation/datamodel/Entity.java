/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherstation.datamodel;

/**
 *
 * @author Alec192
 */
public abstract class Entity {
    protected int id = -Integer.MAX_VALUE;
    
    public int getID(){
        return this.id;
    }
    
    public void setID(int id){
        this.id = id;
    }
}
