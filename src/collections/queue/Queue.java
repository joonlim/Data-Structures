/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.queue;

/**
 *
 * @author Joon
 */
public interface Queue<T> {
    
    public boolean insert(T item);
    
    public T remove();
    
    public T examine();
    
}
