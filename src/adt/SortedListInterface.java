/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Choo Jun Sheng
 * @param <T>
 */
public interface SortedListInterface <T extends Comparable <T>> {
        
    public boolean add (T newObject); //add new object
    
    public boolean remove (T index); // remove without returning the object
    
    public boolean contain (T anObject); // check whether object exist in the list
    
    public boolean replace (T anObject, T newObject); //replace an existing object
    
    public T getObject(int positionIndex); // return object at given positio index
    
    //public void emptyTheList (); // clear all object from the list
    
    @Override
    public String toString();
    
    public boolean isListEmpty (); //check whether if list is empty
    
    public boolean isListFull (); // check whether if the list is full
    
    public int totalNumberOfObject(); // return total number of object
}
