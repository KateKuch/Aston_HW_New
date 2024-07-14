package org.array;

import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {

    private final Object[] elements;
    private int size = 0;

    public CustomArrayList() {
        elements = new Object[size + 10];
    }

    public void add(int index, E element){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }
    public boolean addAll(Collection<? extends E> c) {
        Object[] newElements = c.toArray();
        int newSize = newElements.length;
        System.arraycopy(newElements, 0, elements, size, newSize);
        size += newSize;
        return newSize != 0;
    }
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    public E get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void remove(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int movedNum = size - index - 1;
        if(movedNum > 0){
            System.arraycopy(elements, index + 1, elements, index, movedNum);
        }
        elements[--size] = null;
    }

    public void remove(Object o){
        for (int i = 0; i < size; i++) {
            if(o.equals(elements[i])){
                remove(i);
            }
        }
    }
    public void sort(Comparator<? super E> c){
        if(size > 1){
            mergeSort(0, size - 1, c);
        }
    }

    private void mergeSort(int left, int right, Comparator<? super E> c) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid, c);
            mergeSort(mid + 1, right, c);
            merge(left, mid, right, c);
        }
    }

    private void merge(int left, int mid, int right, Comparator<? super E> c) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Object[] leftArray = new Object[n1];
        Object[] rightArray = new Object[n2];

        System.arraycopy(elements, left, leftArray, 0, n1);
        System.arraycopy(elements, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {

            E leftElement = (E) leftArray[i];
            E rightElement = (E) rightArray[j];

            if (c.compare(leftElement, rightElement) <= 0) {
                elements[k++] = leftArray[i++];
            } else {
                elements[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            elements[k++] = leftArray[i++];
        }

        while (j < n2) {
            elements[k++] = rightArray[j++];
        }
    }


    public int getSize(){
        return size;
    }
}
