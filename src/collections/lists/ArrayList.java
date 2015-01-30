/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.lists;

import collections.Collection;
import java.util.Arrays;

/**
 *
 * @author Joon
 * @param <V>
 */
public class ArrayList<V> implements Collection, List, Cloneable {

    private int size;
    private V[] value;

    public ArrayList() {
        value = (V[]) new Object[10];
        size = 0;
    }

    public ArrayList(V[] items) {
        value = (V[]) new Object[items.length];
        for (int i = 0; i < items.length; i++) {
            value[i] = items[i];
        }
        size = items.length;
    }

//    public ArrayList(LinkedList items) {
//        value = (V[]) items.toArray();
//        size = items.size();
//    }
//
//    public ArrayList(DoublyLinkedList items) {
//        value = (V[]) items.toArray();
//        size = items.size();
//    }
    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity(int newSize) {
        if (size == 0) {
            return;
        }

        if (value.length >= newSize) {
            return;
        }

        V[] copy = toArray();
        value = (V[]) new Object[newSize + 5];

        for (int i = 0; i < size; i++) {
            value[i] = copy[i];
        }
        size = newSize - 1;

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
        if (index == size) {
            ensureCapacity(size + 1);

            value[size++] = (V) item;
            return;
        }

        ensureCapacity(++size);

        for (int i = size - 1; i > index; i--) {
            value[i] = value[i - 1];
        }
        value[index] = (V) item;

    }

    @Override
    public void addFirst(Object item) {
        add(0, item);
    }

    @Override
    public void addLast(Object item) {
        add(size, item);
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);
        if (index < 0) {
            return false;
        }

        for (int i = index; i < size - 1; i++) {
            value[i] = value[i + 1];
        }
        value[--size] = null;
        return true;
    }

    @Override
    public Object remove(int index) {
        checkRange(index);

        Object o = value[index];

        for (int i = index; i < size - 1; i++) {
            value[i] = value[i + 1];
        }
        value[--size] = null;
        return o;

    }

    @Override
    public Object removeFirst() {
        return remove(0);
    }

    @Override
    public Object removeLast() {
        return remove(size - 1);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            value[i] = null;
        }
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
        ArrayList<V> copy = new ArrayList<>(toArray());
        copy.size = size;
        return copy;
    }

    @Override
    public V get(int index) {
        checkRange(index);

        return value[index];
    }

    @Override
    public V set(int index, Object item) {
        checkRange(index);

        V old = value[index];
        value[index] = (V) item;
        return old;
    }

    @Override
    public void append(List list) {
        int i = size;
        ensureCapacity(size + list.size());
        V[] appended = (V[]) list.toArray();

        size++;

        for (int j = 0; i < size; i++, j++) {
            value[i] = appended[j];
        }
    }

    @Override
    public void reverse() {
        V[] reverse = (V[]) new Object[size];
        for (int i = 0; i < size; i++) {
            reverse[i] = value[size - 1 - i];
        }
        value = reverse;
    }

    @Override
    public V[] toArray() {
        return Arrays.copyOf(this.value, size);

    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (value[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(value[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i++) {
                if (value[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i++) {
                if (o.equals(value[i])) {
                    return i;
                }
            }
        }
        return -1;

    }

    @Override
    public String toString() {
        String s = "ArrayList: ";
        for (int i = 0; i < size; i++) {
            s += value[i] + " ";
        }
        return s;
    }

    @Override
    public boolean equals(List list) {
        if (size != list.size()) {
            return false;
        }
        V[] listArray = (V[]) list.toArray();
        for (int i = 0; i < size; i++) {
            if (!value[i].equals(listArray[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        ArrayList<Integer> a = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            a.add(new Integer(i));
        }

        System.out.println(a);
        System.out.println("size: " + a.size());

        a.append(a);

        System.out.println(a);

        System.out.println("size: " + a.size());

        a.reverse();
        System.out.println(a);
        
        ArrayList<Integer> b = (ArrayList)a.clone();
        
        System.out.println(a==b);
        System.out.println(a.equals(b));
        
        

    }
}
