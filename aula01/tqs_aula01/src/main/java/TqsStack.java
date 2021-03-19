/*
    *
    * Author: Anth0nyPereira
    * number 93016
    *
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private ArrayList<T> arrayList; // use of arraylist to represent a stack
    private int maxSize; // to distinguish between a normal stack from a bounded stack

    // default constructor
    public TqsStack() {
        this.arrayList = new ArrayList<>();
        this.maxSize = 0;
    }

    // constructor to instantiate a bounded stack (with a maximum size)
    public TqsStack(int maxSize) {
        this.arrayList = new ArrayList<>();
        this.maxSize = maxSize;
    }

    // stack methods
    public boolean isEmpty(){
        return arrayList.isEmpty();
    }

    public boolean push(T element){
        if (maxSize > 0 && arrayList.size() == maxSize) {
            throw new IllegalStateException();
        }
        arrayList.add(0, element);
        return true;
    }

    public T pop(){
        if (arrayList.isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = arrayList.get(0);
        arrayList.remove(0);
        return element;
    }

    public T peek() {
        if (arrayList.isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = arrayList.get(0);
        return element;
    }

    public int size() {
        return arrayList.size();
    }
}
