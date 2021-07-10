package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@link LinkedStack} represents a last-in-first-out (LIFO) stack of objects that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedStack<T> implements Stack<T> {
    private Node<T> head;
    private int size = 0;

    static class Node<T>{
        T element;
        Node<T> next;

        private Node(T element) {
            this.element = element;
        }
    }

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> stack = new LinkedStack<>();
        Arrays.stream(elements).forEach(stack::push);
        return stack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        Objects.requireNonNull(element);
        Node<T>  newNode= new Node<>(element);
        if(head != null){
            Node<T> holder = head;
            head = newNode;
            newNode.next = holder;
        }else{
            head = newNode;
        }
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if(size == 0){
            throw new EmptyStackException();
        }
        T toRemove = head.element;
        head = head.next;
        size--;
        return toRemove;
    }


    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

}
