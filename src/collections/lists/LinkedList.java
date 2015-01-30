/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.lists;

import collections.Collection;

/**
 *
 * @author Joon
 * @param <V>
 */
public class LinkedList<V> implements Collection, List, Cloneable {

    private int size;
    private Node<V> first;
    private Node<V> last;

    public LinkedList() {
    }

    public LinkedList(V[] items) {
        first = new Node(items[0]);

        size = items.length;

        last = first;

        for (int i = 1; i < size; i++) {
            last.setNext(new Node(items[i]));
            last = last.getNext();
        }

    }

    @Override
    public int size() {
        return size;
    }

    private void checkRange(int index) throws IllegalArgumentException {
        if (index > size || index < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean add(Object item) {
        addLast(item);
        return true;
    }

    @Override
    public void add(int index, Object item) {

        checkRange(index);

        // index is 0
        if (index == 0) {
            addFirst(item);
            return;
        }

        // index is one greater than last index
        if (index == size) {
            addLast(item);
            return;
        }

        Node cursor = first;

        while (index > 1) {
            cursor = cursor.getNext();
            index--;
        }

        // index is the last index
        if (index == size - 1) {
            cursor.setNext(new Node(item));
            last = cursor;
            size++;
            return;

        } else {
            cursor.setNext(new Node(item, cursor.getNext()));
            size++;
        }

    }

    @Override
    public void addFirst(Object item) {
        if (isEmpty()) {
            first = new Node(item);
            last = first;
            size++;
            return;
        }
        Node first = new Node(item);
        first.setNext(this.first);
        this.first = first;
        size++;
    }

    @Override
    public void addLast(Object item) {
        if (isEmpty()) {
            addFirst(item);
            return;
        }
        last.setNext(new Node(item));
        last = last.getNext();
        size++;
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);
        if (index < 0) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public Object remove(int index) {
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        checkRange(index);

        Node cursor = first;

        while (index > 1) {
            cursor = cursor.getNext();
            index--;

        }

        Object o = cursor.getNext().getValue();
        cursor.setNext(cursor.getNext().getNext());

        size--;

        return o;

    }

    @Override
    public Object removeFirst() {
        checkRange(0);

        Object o = first.getValue();
        size--;

        if (size > 0) {
            first = first.getNext();
            if (size == 1) {
                last = first;
            }
        } else {
            first = null;
            last = null;
        }
        return o;
    }

    @Override
    public Object removeLast() {
        checkRange(size - 1);

        Object o = last.getValue();
        size--;

        if (size == 0) {
            first = null;
            last = null;
        } else {
            if (size == 1) {
                last = first;
                first.setNext(null);
            } else { //size >= 2
                Node cursor = first;
                int i = size;
                while (i > 1) {
                    cursor = cursor.getNext();
                    i--;
                }
                cursor.setNext(null);
            }
        }
        return o;

    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public List clone() throws CloneNotSupportedException {
        LinkedList<V> copy = new LinkedList<>(toArray());
        copy.size = size;
        return copy;
    }

    @Override
    public V get(int index) {
        Node cursor = first;
        while (index > 0) {
            cursor = cursor.getNext();
            index--;
        }
        return (V) cursor.getValue();
    }

    public V getFirst() {
        return first.getValue();
    }

    public V getLast() {
        return last.getValue();
    }

    @Override
    public V set(int index, Object item) {
        if (index < 0 || index > size()) {
            return null;
        }
        if (index == size) {
            this.addLast(item);
            return null;
        }
        if (index == 0) {
            V value = first.getValue();
            first = new Node(item, first.getNext());
            return value;
        }
        V value;
        Node prev = first;
        
        for(int i=1; i<index; i++){
        
            prev = prev.getNext();
        }
        value = (V)prev.getNext().getValue();
        prev.setNext(new Node(item, prev.getNext().getNext()));
        if(index == size-1) {
            last = prev.getNext();
        }
        
        return value;
    }

    @Override
    public void append(List list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public void reverse() {
        reverse(first);
    }

    private void reverse(Node node) {

        if (node == null) {
            return;
        }

        if (node.getNext() == null) {
            first = node;
            return;
        }

        if (node.equals(first)) {
            last = first;
        }

        reverse(node.getNext());
        node.getNext().setNext(node);
        node.setNext(null);
    }

    @Override
    public V[] toArray() {
        V[] copy = (V[]) new Object[size];
        Node cursor = first;
        int i = 0;
        while (cursor != null) {
            copy[i] = (V) cursor.getValue();
            cursor = cursor.getNext();
            i++;
        }
        return copy;
    }

    @Override
    public int indexOf(Object o) {
        Node cursor = first;
        int i = 0;

        if (o == null) {
            while (cursor != null) {
                if (cursor.getValue() == null) {
                    return i;
                }
                cursor = cursor.getNext();
                i++;
            }
        } else {
            while (cursor != null) {
                if (cursor.getValue().equals(o)) {
                    return i;
                }
                cursor = cursor.getNext();
                i++;
            }

        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node cursor = first;
        int i = 0;
        int lastIndex = -1;

        if (o == null) {
            while (cursor != null) {
                if (cursor.getValue() == null) {
                    lastIndex = i;
                }
                cursor = cursor.getNext();
                i++;
            }
        } else {
            while (cursor != null) {
                if (cursor.getValue().equals(o)) {
                    lastIndex = i;
                }
                cursor = cursor.getNext();
                i++;
            }

        }
        return lastIndex;
    }

    public String toString() {
        String s = "LinkedList: ";
        Node cursor = first;
        for (int i = 0; i < size; i++) {
            s += cursor.getValue() + " ";
            cursor = cursor.getNext();
        }
        return s;
    }

    @Override
    public boolean equals(List list) {
        if (size != list.size()) {
            return false;
        }

        V[] listArray = (V[]) list.toArray();
        V[] copy = toArray();
        for (int i = 0; i < size; i++) {
            if (!copy[i].equals(listArray[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        LinkedList<Integer> b = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            b.add(new Integer(i));
        }

        b.reverse();
        System.out.println(b);

        LinkedList<Integer> c = new LinkedList<>();
        for (int i = 49; i >= 0; i--) {
            c.add(new Integer(i));
        }
        System.out.println(c);

        System.out.println(b.equals(c));
        
        System.out.println(b.set(0, new Integer(80)));
        System.out.println(b.last.getValue());
        System.out.println(b);

    }

    class Node<V> {

        private V value;
        private Node<V> next;

        public Node(V value) {
            this.value = value;
            this.next = null;
        }

        public Node(V value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node node) {
            next = node;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
