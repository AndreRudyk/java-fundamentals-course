package com.bobocode.cs;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 */
public class ArrayList<T> implements List<T> {
    public Object [] array;
    private int size;
    final int CAPACITY = 5;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if(initCapacity <= 0){
            throw new IllegalArgumentException();
        }
        array = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        array = new Object[CAPACITY];
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> ArrayList<Integer> of(T... elements) {
        ArrayList list = new ArrayList();
        list.array = Arrays.copyOf(elements, elements.length);
        list.size = elements.length;
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        increaseIfFull();
        array[size] = element;
        size++;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        increaseIfFull();
        Object[]newArray = new Object [array.length];
        if(index == size){
            array[size] = element;
        }else{
            for(int i = 0; i < size; i++){
                if( i != index){
                    newArray[i] = array[i];
                }else{
                    newArray [i] = element;
                    for(int j = i + 1; j < size + 1; j++){
                        newArray[j] = array[j - 1];
                    }
                    break;
                }
            }
            array = newArray;

        }
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return (T) array[0];
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return (T) array[size - 1];
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        array[index] = element;
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
        Object removed = array[index];
        Object []  newOne= new Object [CAPACITY];
        for(int i = 0; i < size; i++){
            if(i != index){
                newOne [i] = array [i];
            }else{
                if(i == size - 1){
                   break;
                }
                newOne[i] = array[i + 1];
                for(int j = i + 2; j < size; j++){
                    newOne[j-1] = array[j];
                }
                array = newOne;
                break;
            }
        }
        size--;
        array = new Object [size];
        for(int z = 0; z < size; z++){
            array[z] =  newOne[z];

        }        return (T) removed;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        for(int i = 0; i < size; i++){
            if(array[i] == element){
                return true;
            }
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
            return  true;
        }else{
            return false;
        }
    }

    /**
     * @return amount of saved elements
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
        array = null;
        size = 0;
    }
    private void increaseIfFull(){
        if(size == array.length){
            array = Arrays.copyOf(array, size * 2);
        }
    }
}
