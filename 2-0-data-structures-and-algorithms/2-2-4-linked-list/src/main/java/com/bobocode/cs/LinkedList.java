package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
     static class Node<T>{
        T element;
        Node<T> next;

        Node(T element){
            this.element = element;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
       LinkedList linkedList = new LinkedList();
        Arrays.stream(elements).forEach(linkedList::add);
        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if(size == 0){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        //Objects.checkIndex(index, size);
        Node<T> newNode = new Node<>(element);
        if(size == 1 && index == 0) {
            tail = head;
            newNode.next = head;
            head = newNode;
        }else if(index == 0 && size > 1){
            newNode.next = head;
            head = newNode;
        }else if(size == 0 && index == 0) {
            head = tail = newNode;
        }else if(index < 0){
            throw new NullPointerException();
        }else{
            Node<T> beforeFound = getNodeByIndex(index -1);
            Node<T> found = getNodeByIndex(index);
            beforeFound.next = newNode;
            newNode.next = found;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        if( index == 0 && size == 1){
            head.element = element;
        }
        Node<T> nodeByIndex = getNodeByIndex(index);
        nodeByIndex.element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        if((index == 0 && size == 0)|| index < 0 || index >= size){
            throw new NullPointerException();
        }
       // Objects.checkIndex(index, size);
        Node<T> current = head;
        for(int i =0; i<index; i++){
            current = current.next;
        }
        return current.element;
    }

    public Node<T> getNodeByIndex(int index) {
        Node<T> current = head;
        for(int i =0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if(size == 0){
            throw new NoSuchElementException();
        }
        return head.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        return tail.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Node<T> toRemove = getNodeByIndex(index);
        if(index == 0){
            head = head.next;
        }else if(toRemove == tail) {
            Node<T> newTail = getNodeByIndex(size - 2);
            tail = newTail;
        }else{
            Node<T> nodeBeforeDeleted = getNodeByIndex(index - 1);
            Node<T> nodeAfterDeleted = getNodeByIndex(index + 1);
            nodeBeforeDeleted.next = nodeAfterDeleted;
        }
        size--;
        return toRemove.element;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        for(int i =0; i<size; i++){
            if(current.element == element){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
