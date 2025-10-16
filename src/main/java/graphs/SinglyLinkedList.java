package graphs;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly ended singly linked list.
 * Supports adding elements at the front and end,
 * retrieving by index, checking size, and iterating over elements.
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> implements Iterable<E> {
    private Node<E> head;       // first node containing the first item in the linked list
    private Node<E> tail;       // last node containing the last item in the linked list
    private int size;           // current number of items in the linked list


    /**
     * Inner class representing a node in the singly linked list.
     */
    private static class Node<E> {
        // TODO: add necessary attributes (data, next reference) & a constructor

        // The use of E is for the type
        // Node<E> is a Reference to another node
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an item to the end of the list
     *
     * @param item the element to add
     */
    public void add(E item) {
        // TODO: create a new node and attach it at the end of the list
        // TODO: increment size

        Node<E> newNode = new Node<>(item);

        // If the Linked list is empty we set the new node as the head of the list.
        if (head == null) {
            head = newNode;
        }

        else {
            Node<E> curr = head;

            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
            newNode.prev = curr;
        }
        size++;
    }

    /**
     * Adds an item to the beginning of the list
     *
     * @param item the element to add
     */
    public void addFirst(E item) {
        // TODO: create a new node and set it as the new head

        Node<E> newNode = new Node<>(item);

        // if the list is empty we set the adress of this node as the new head and tail.
        if (head == null) {

            head = newNode;
            tail = newNode;
        }
        else {
            // newNode.next gets the location of the current head.
            newNode.next = head;
            // Updates the prev field of the current head to point to newNode
            head.prev = newNode;
            // newNode becomes the first element
            head = newNode;

        }
        size++;
    }

    /**
     * Gets the element at the specified index
     *
     * @param index the index of the element to retrieve
     * @return the item at the given position
     * @throws IndexOutOfBoundsException if index is not within range
     */
    public E get(int index) {
        // TODO: find the node at the index position and return the item from the node


        Node<E> curr = head;
        int pos = 0;

        // Checks if the index is out of bounds if it is it wil throw an error
        if (curr == null || index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + "Is out of bounds" );
        }

        // compares the location of pos with the location of the index to try to find the wright location
        while (pos < index) {
            curr = curr.next;
            pos++;
        }
        // Return the data from the element needed
         return curr.data;
    }

    /**
     * @return the number of elements in the list
     *
     */
    public int size() {
        return this.size;
    }



    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an {@link Iterator} over the elements in this list
     * @throws java.util.NoSuchElementException if the {@link Iterator#next()} method is called
     *         when no more elements are available
     */
    @Override
    public Iterator<E> iterator() {
        // TODO replace below iterator by a more memory efficient, true iterator instance
        //      that iterates along all items in the list.

        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private Node<E> curr = head;


        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            if(!hasNext()) throw new NoSuchElementException();
            E data = curr.data;
            curr = curr.next;
            return data;
        }
    }

}
