/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.stack;

/**
 *
 * @author Joon
 */
public interface Stack<T> {
    
    public boolean push(T item);
    
    public T pop();
    
    public T peek();
    
}
