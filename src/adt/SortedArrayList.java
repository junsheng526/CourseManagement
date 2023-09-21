package adt;

import java.io.Serializable;

/**
 *
 * @author Kai Xin
 * @param <T>
 */
public class SortedArrayList<T extends Comparable<T>> implements ListInterface<T>, Serializable {

    private T[] array;
    private int totalObject;

    private static final int INITIAL_CAPACITY = 2;

    // Constructors
    public SortedArrayList() {
        this(INITIAL_CAPACITY);
    }

    public SortedArrayList(int initialCapacity) {
        this.array = (T[]) new Comparable[initialCapacity];
        this.totalObject = 0;
    }

    @Override
    public boolean add(T newObject) {

        if (isListFull()) {
            doubleArraySize();
        }

        int index = 0;

        while (index < totalObject && newObject.compareTo(array[index]) > 0) {
            index++;
        }

        makeRoom(index + 1);
        array[index] = newObject;
        totalObject++;
        return true;
    }

    @Override
    public boolean remove(T anObject) {
        if (isListEmpty()) {
            return false;
        } else {

            int position = 0;

            while (position < (totalObject - 1) && !array[position].equals(anObject)) {
                position++;
            }

            if (array[position].equals(anObject)) {
                removeGap(position);
                totalObject--;

                if ((totalObject * 2) == (array.length + 1)) {
                    reduceArraySize();
                }

                return true;
            }

            return false;
        }
    }

    public void emptyTheList() {

        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        totalObject = 0;
    }

    @Override
    public boolean replace(T anObject, T newObject) {
        if (isListEmpty()) {
            return false;
        } else {
            int i = 0;

            while (i < totalObject && !array[i].equals(anObject)) {
                i++;
            }

            if (anObject.equals(array[i])) {
                array[i] = newObject;
                return true;
            }

            return false;
        }
    }

    @Override
    public T getObject(int positionIndex) {
        return array[positionIndex];
    }

    private void makeRoom(int index) {
        int newIndex = index - 1, lastIndex = totalObject - 1;

        for (int i = lastIndex; i >= newIndex; i--) {
            array[i + 1] = array[i];
        }
    }

    private void removeGap(int index) {
        int remove = index, lastIndex = totalObject - 1;

        for (int i = remove; i < lastIndex; i++) {
            array[i] = array[i + 1];
        }
    }

    @Override
    public boolean contain(T anObject) {

        int first = 0, last = totalObject - 1;

        int result = recursiveBinarySearch(anObject, first, last);

        return result >= 0;
    }

    @Override
    public boolean isListEmpty() {
        return totalObject == 0;
    }

    @Override
    public boolean isListFull() {
        return totalObject == array.length;
    }

    @Override
    public int totalNumberOfObject() {
        return totalObject;
    }

    private int recursiveBinarySearch(T anObject, int first, int last) {
        if (first <= last) {
            int mid = (first + last) / 2; // get mid value

            if (array[mid].equals(anObject)) {

                return mid; // object found at mid of array

            } else if (array[mid].compareTo(anObject) > 0) {

                return recursiveBinarySearch(anObject, first, mid - 1); // call for lower half of array

            } else {

                return recursiveBinarySearch(anObject, mid + 1, last); // call for upper half of array

            }
        }
        return -(first + 1); // unable to find object
    }

    private void doubleArraySize() {
        T[] oldArray = array;
        int oldCapacity = oldArray.length;

        array = (T[]) new Comparable[oldCapacity * 2];

        System.arraycopy(oldArray, 0, array, 0, oldCapacity);
    }

    private void reduceArraySize() {
        T[] oldArray = array;
        int oldCapacity = oldArray.length / 2;

        array = array = (T[]) new Comparable[oldCapacity];

        System.arraycopy(oldArray, 0, array, 0, oldCapacity);
    }

    private String convertString(int i) {
        Integer x = i;

        return x.toString();
    }

    @Override
    public String toString() {
        String objectString = "";

        for (int i = 0; i < totalObject; i++) {
            objectString += convertString(i + 1) + ".  " + array[i] + "\n";
        }

        return objectString;
    }
}
