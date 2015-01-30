/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.queue;

import collections.Collection;
import collections.trees.Heap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joon
 */
public class PriorityQueue<V extends Comparable<? super V>> implements Collection, Queue<V> {

    Heap<V> data;
    int size;

    public PriorityQueue() {
        data = new Heap();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        data = new Heap();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean insert(V item) {
        try {
            data.insert(item);
        } catch (Exception ex) {
            Logger.getLogger(PriorityQueue.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public V remove() {
        try {
            return data.remove();
        } catch (Exception ex) {
            Logger.getLogger(PriorityQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public V examine() {
        return data.max();
    }

    public static void main(String[] args) {
        PriorityQueue p = new PriorityQueue();
        
        p.insert("Hello");
        p.insert("Hi");
        p.insert("Hey");
        System.out.println(p.examine());
        System.out.println(p.remove());
        System.out.println(p.examine());
    }
}
