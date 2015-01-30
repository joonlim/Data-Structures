/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.stack;

import collections.Collection;
import collections.lists.LinkedList;

/**
 *
 * @author Joon
 */
public class LinkedStack<V> implements Collection, Stack<V> {

    LinkedList data;
    int size;

    public LinkedStack() {
        data = new LinkedList();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        data = new LinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean push(V item) {
        data.add(item);
        return true;
    }

    @Override
    public V pop() {
        V popped = (V) data.getFirst();
        data.remove(0);
        return popped;
    }

    @Override
    public V peek() {
        return (V) data.getFirst();
    }
    
    public static void main(String[] args) {
        LinkedStack<String> l = new LinkedStack<>();
        
        l.push("yo");
        l.push("mama");
        System.out.println(l.peek());
        System.out.println(l.pop());
        System.out.println(l.peek());
    }

}
