/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

/**
 *
 * @author Joon
 */
public interface Collection {

    /**
     * Returns the number of elements in this collection.
     *
     * @return
     */
    public int size();
    


    /**
     * Removes all of the elements from this collection.
     */
    public void clear();



    /**
     * Returns true if this collection contains no elements.
     *
     * @return
     */
    public boolean isEmpty();
    

}
