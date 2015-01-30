/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections.trees;

import collections.Collection;

/**
 *
 * @author Joon
 * @param <Key>
 * @param <V>
 */
public class BinarySearchTree<Key extends Comparable<? super Key>, V> implements Collection, Cloneable {

    private Node<Key, V> root;
    private int size;
    static final String PREORDER = "preorder";
    static final String POSTORDER = "postorder";
    static final String INORDER = "inorder";

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void insert(Key key, V value) {
        Node<Key, V> newNode = new Node(key, value);
        Node<Key, V> cursor;
        boolean done = false;
        if (root == null) {
            root = new Node(key, value);
        } else {

            cursor = root;
            while (!done) {
                if (newNode.getKey().compareTo(cursor.getKey()) < 0) {
                    if (cursor.getLeft() == null) {
                        cursor.setLeft(newNode);
                        size++;
                        done = true;
                    } else {
                        cursor = cursor.getLeft();
                    }
                } else if (newNode.getKey().compareTo(cursor.getKey()) > 0) {
                    if (cursor.getRight() == null) {
                        cursor.setRight(newNode);
                        size++;
                        done = true;
                    } else {
                        cursor = cursor.getRight();
                    }
                } else {
                    cursor.setValue(value);
                    done = true;
                }
            }
        }
    }

    public void insert(Node<Key, V> newNode) {
        Node<Key, V> cursor;
        boolean done = false;
        if (root == null) {
            root = newNode;
        } else {

            cursor = root;
            while (!done) {
                if (newNode.getKey().compareTo(cursor.getKey()) < 0) {
                    if (cursor.getLeft() == null) {
                        cursor.setLeft(newNode);
                        size++;
                        done = true;
                    } else {
                        cursor = cursor.getLeft();
                    }
                } else if (newNode.getKey().compareTo(cursor.getKey()) > 0) {
                    if (cursor.getRight() == null) {
                        cursor.setRight(newNode);
                        size++;
                        done = true;
                    } else {
                        cursor = cursor.getRight();
                    }
                } else {
                    cursor = newNode;
                    done = true;
                }
            }
        }
    }

    public int height() {
        return root.height();
    }

    @Override
    public int size() {
        return size;
    }

    public boolean remove(Key key) {
        Node<Key, V> parent = null;
        Node<Key, V> cursor = root;

        while (cursor != null) {

            if (key.compareTo(cursor.getKey()) < 0) {

                parent = cursor;

                cursor = cursor.getLeft();
            } else if (key.compareTo(cursor.getKey()) > 0) {

                parent = cursor;

                cursor = cursor.getRight();
            } else {
                break; // element is in the tree pointed by current
            }

        }

        if (cursor == null) { // not found
            return false;
        }

        // case 1: current node has no left children
        if (cursor.left == null) {

            // connect the parent with the right child of the current node
            if (parent == null) {
                root = cursor.getRight();
            } else {
                if (key.compareTo(parent.getKey()) < 0) {
                    parent.setLeft(cursor.getRight());
                } else {
                    parent.setRight(cursor.getRight());
                }
            }
        } else {
            // case 2: current node has a left child
            // locate the rightmost node in the left subtree of
            // the current node and also its parent
            Node<Key, V> parentOfRightMost = cursor;
            Node<Key, V> rightMost = cursor.getLeft();

            while (rightMost.getRight() != null) {
                parentOfRightMost = rightMost;

                rightMost = rightMost.getRight(); // keep going to the right
            }

            // replace the element in current node by the element in rightmost
            cursor.setKey(rightMost.getKey());
            cursor.setValue(rightMost.getValue());

            // eliminate rightmost node
            if (parentOfRightMost.getRight() == rightMost) {
                parentOfRightMost.setRight(rightMost.getLeft());
            } else {
                // special case: parentOfRightMost == current
                parentOfRightMost.setLeft(rightMost.getLeft());
            }
        }

        size--;
        return true;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public boolean contains(Key key) {
        Node<Key, V> cursor = root;

        while (cursor != null) {

            if (key.compareTo(cursor.getKey()) < 0) {

                cursor = cursor.getLeft();
            } else if (key.compareTo(cursor.getKey()) > 0) {

                cursor = cursor.getRight();
            } else {
                break;
            }

        }

        return (cursor != null);
    }

    public V getMax() {
        V max = root.value;
        if (root != null) {
            max = root.max();
        }
        return max;
    }

    public V getMin() {
        V min = root.value;
        if (root != null) {
            min = root.min();
        }
        return min;
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    public String toString(String order) {
        return "BST: " + root.toString(order);
    }

    public boolean equals(BinarySearchTree check) {
        // Shortcut for reference equality; also handles equals(null, null) 
        if (this.root == check.root) {
            return true;
        }
        if (this.root == null || check.root == null) {
            return false;
        }
        return root.equals(check.root);
    }

    public BinarySearchTree clone() {
        BinarySearchTree copy = new BinarySearchTree();

        copy.insert(root.clone());
        copy.size = this.size;

        return copy;
    }

    class Node<Key extends Comparable<? super Key>, V> {

        private Key key;
        private V value;
        private Node<Key, V> left;
        private Node<Key, V> right;

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V item) {
            this.value = item;
        }

        public Node<Key, V> getLeft() {
            return left;
        }

        public void setLeft(Node<Key, V> left) {
            this.left = left;
        }

        public Node<Key, V> getRight() {
            return right;
        }

        public void setRight(Node<Key, V> right) {
            this.right = right;
        }

        public boolean isLeaf() {
            return (left == null && right == null);
        }

        public int height() {
            if (isLeaf()) {
                return 0;
            } else {
                return 1 + Math.max(left.height(), right.height());
            }
        }

        public String toString(String order) {
            String s = "";

            if (order.equalsIgnoreCase("preorder")) {
                s += value + " ";
            }

            if (left != null) {
                s += left.toString(order);
            }

            if (order.equalsIgnoreCase("inorder")) {
                s += value + " ";
            }

            if (right != null) {
                s += right.toString(order);
            }

            if (order.equalsIgnoreCase("postorder")) {
                s += value + " ";
            }

            return s;

        }

        /**
         * Does it have a left child?
         */
        public boolean hasLeft() {
            return left != null;
        }

        /**
         * Does it have a right child?
         */
        public boolean hasRight() {
            return right != null;
        }

        public Node clone() {
            Node left = null;
            Node right = null;
            if (this.left != null) {
                left = this.left.clone();
            }
            if (this.right != null) {
                right = this.right.clone();
            }
            return new Node(key, value, left, right);
        }

        public boolean equals(Node check) {
            if (hasLeft() != check.hasLeft() || hasRight() != check.hasRight()) {
                return false;
            }
            if ((!key.equals(check.key)) || !value.equals(check.value)) {
                return false;
            }
            if (hasLeft() && !left.equals(check.left)) {
                return false;
            }
            if (hasRight() && !right.equals(check.right)) {
                return false;
            }
            return true;
        }

        public V max() {
            if (right != null) {
                return right.max();
            }
            return value;
        }

        public V min() {
            if (left != null) {
                return left.min();
            }
            return value;
        }

// value = data = object
        public Node(Key key, V item, Node<Key, V> left, Node<Key, V> right) {
            this.key = key;
            this.value = item;
            this.left = left;
            this.right = right;
        }

        public Node(Key key, V item) {
            this.key = key;
            this.value = item;
            right = null;
            left = null;
        }

    }

    public static void main(String[] args) {
        BinarySearchTree b = new BinarySearchTree();

        b.insert(45, "(45)");
        b.insert(21, "(21)");
        b.insert(83, "(83)");
        b.insert(11, "(11)");
        b.insert(39, "(39)");
        b.insert(62, "(62)");
        b.insert(96, "(96)");

        System.out.println(b.toString(PREORDER));

        b.remove(11);

        System.out.println(b.toString(PREORDER));

        BinarySearchTree c = b.clone();
        
        System.out.println(b == c);
        System.out.println(b.equals(c));

        System.out.println(b.toString(PREORDER));
        System.out.println(b.toString(INORDER));
        System.out.println(b.toString(POSTORDER));
        System.out.println(c.toString(PREORDER));
        System.out.println(c.toString(INORDER));
        System.out.println(c.toString(POSTORDER));

    }

}
