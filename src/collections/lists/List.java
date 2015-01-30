/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.lists;

/**
 *
 * @author Joon
 */
public interface List<T> {

    /**
     * Returns the number of elements in this list.
     *
     * @return
     */
    public int size();

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param item
     * @return
     */
    public boolean add(Object item);

    /**
     * Removes a single instance of the specified element from this collection,
     * if it is present.
     *
     * @param item
     * @return
     */
    public boolean remove(Object item);

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index
     * @param item
     */
    public void add(int index, T item);

    /**
     * Inserts the specified element at the front of this list. The indices of
     * the previous elements get shifted forward one.
     *
     * @param item
     */
    public void addFirst(T item);

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param item
     */
    public void addLast(T item);

    /**
     * Removes the element at the specified position in this list. Returns the
     * removed element.
     *
     * @param index
     * @return
     */
    public T remove(int index);

    /**
     * Removes the element at the front of this list. The indices of the
     * previous elements get shifted back one. Returns the removed element.
     *
     * @return
     */
    public T removeFirst();

    /**
     * Removes the element at the back of this list. Returns the removed
     * element.
     *
     * @return
     */
    public T removeLast();

    /**
     * Returns true if this collection contains the specified element.
     *
     * @param item
     * @return
     */
    public boolean contains(Object item);

    /**
     * Returns the first index of an object in this list.
     *
     * @param o
     * @return
     */
    public int indexOf(Object o);

    /**
     * Returns the last index of an object in this list.
     *
     * @param o
     * @return
     */
    public int lastIndexOf(Object o);

    /**
     * Returns the element at this index.
     *
     * @param index
     * @return
     */
    public T get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element. Returns the original element.
     *
     * @param index
     * @param item
     * @return
     */
    public T set(int index, T item);

    /**
     * Adds another list to the end of this list.
     *
     * @param list
     */
    public void append(List list);

    /**
     * Reverses this list.
     */
    public void reverse();

    /**
     * Returns this list as an array.
     *
     * @return
     */
    public T[] toArray();

    /**
     * Returns an identical copy of this collection.
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    public List clone() throws CloneNotSupportedException;

    /**
     * Compares the specified object with this collection for equality.
     *
     * @param list
     * @return
     */
    public boolean equals(List list);
}
