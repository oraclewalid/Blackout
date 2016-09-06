package com.fabulouslab.collection;

import com.fabulouslab.exception.BlackoutException;
import com.fabulouslab.util.Memory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class BlackoutAbstractList<E> implements List<E> {

    protected long address;

    // Size is in heap
    protected int size ;

    protected int capacity;

    public abstract int getLength();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }


    @Override
    public <T> T[] toArray(T[] a) {
        throw new NotImplementedException();
    }

    @Override
    public E remove(int index) {

        E oldValue = get(index);
        long oldValueAddress = Memory.computeAddr(address, index, getLength());
        long nextValueAddress = Memory.computeAddr(address, index+1, getLength());
        long length = (capacity - index - 1) * getLength();

        Memory.copyMemory(nextValueAddress, oldValueAddress, length);
        size--;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        Objects.requireNonNull(o);
        //TODO replace get(i) by a loop on sublist(0,size)
        for(int i = 0; i < size; i++){
            if(o.equals(get(i)))
                return i;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = -1;
            @Override
            public boolean hasNext() {
                return cursor + 1 < size;
            }

            @Override
            public E next() {
                return get(++cursor);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.nonNull(action);
        for (int i = 0; i < size ; i++) {
            action.accept(get(i));
        }
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        int index = indexOf(o);
        if (index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.nonNull(c);
        for (Object element : c) {
            if(contains(element) == false){
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {

        Objects.nonNull(filter);
        boolean anyRemove = false;
        for (int i = 0; i < size ; i++) {
            if(filter.test(get(i))){
                remove(i);
                i--;
                anyRemove = true;
            }
        }
        return anyRemove;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Objects.nonNull(collection);
        boolean anyRemove = false;
        for (int i = 0; i < size ; i++) {
            if(!collection.contains(get(i))){
                remove(i);
                i--;
                anyRemove = true;
            }
        }
        return anyRemove;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Objects.nonNull(comparator);
        quicksort(comparator, 0, size() - 1);
    }

    /**
     *
     * from : https://fr.wikibooks.org/wiki/Impl%C3%A9mentation_d%27algorithmes_classiques/Algorithmes_de_tri/Tri_rapide
     * @param comparator
     * @param start
     * @param end
     */

    private  void quicksort(Comparator<? super E> comparator, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(comparator, start, end);
            quicksort(comparator, start, pivotIndex - 1);
            quicksort(comparator, pivotIndex + 1, end);
        }
    }

    private int partition(Comparator<? super E> comparator, int start, int end) {
        E pivotValue = this.get(start);
        int d = start+1;
        int f = end;
        while (d < f) {
            while(d < f && comparator.compare(this.get(f), pivotValue)  >= 0) f--;
            while(d < f && comparator.compare(this.get(d), pivotValue) <= 0) d++;
            Memory.swapIndex(this.address, f, d, getLength());
        }
        if (comparator.compare(this.get(d), pivotValue) >= 0) d--;
        this.set(start, this.get(d));
        this.set(d, pivotValue);
        return d;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.nonNull(operator);
        for (int i = 0; i < size ; i++) {
            set(i, operator.apply(get(i)));
        }
    }

    @Override
    public void clear() {
        Memory.free(address);
        size = 0;
        address = Memory.allocate(this.capacity * getLength());
    }

    @Override
    public int lastIndexOf(Object o) {
        Objects.requireNonNull(o);
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new BlackoutIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new BlackoutIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {

        checkRang(fromIndex, toIndex);
        List<E> subList = new ArrayList(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++ ){
            subList.add(get(i));
        }
        return subList;
    }

    @Override
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    @Override
    public Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    @Override
    public boolean add(E element) {

        try {
            add(size, element);
            return true;
        }
        catch (BlackoutException ex){
            return false;
        }
    }

    protected boolean checkCapacity(int newSize){
        if(capacity <= size + newSize){
            return false;
        }
        return true;
    }

    protected long realocate(int newSize, int padding){

        long newAddr = Memory.allocate((this.size + newSize) * getLength());
        //Copy first part before padding
        Memory.copyMemory(address, newAddr, padding * getLength());
        //Copy second part after padding
        long oldAddrWithPadding = Memory.computeAddr(address, padding, getLength());
        long newAddrWithPadding = Memory.computeAddr(newAddr, padding + newSize, getLength());
        Memory.copyMemory(oldAddrWithPadding, newAddrWithPadding , (size - padding) * getLength());

        return newAddr;
    }

    protected long realocate(int newSize){

        long newAddr = Memory.allocate((this.size + newSize) * getLength());
        Memory.copyMemory(address, newAddr, this.size  * getLength());

        return newAddr;
    }

    protected void checkSizeBounds(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("index " + index + "is out of bound");
    }

    protected void checkLimits(int index) {
        if (index >= size + 1 || index < 0)
            throw new IndexOutOfBoundsException("index " + index + "is out of bound");
    }

    protected void checkRang(int fromIndex, int toIndex) {
        checkSizeBounds(fromIndex);
        checkLimits(toIndex);
        if (fromIndex >= toIndex)
            throw new IndexOutOfBoundsException("the " + fromIndex + "is greather than" + toIndex);
    }

    private class BlackoutIterator implements ListIterator<E>{

        private int cursor = -1;


        public BlackoutIterator() {
        }

        public BlackoutIterator(int cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return cursor + 1 < size();
        }

        @Override
        public E next() {
            return get(++cursor);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public E previous() {
            return get(--cursor);
        }

        @Override
        public int nextIndex() {
            return ++cursor;
        }

        @Override
        public int previousIndex() {
            return --cursor;
        }

        @Override
        public void remove() {
            BlackoutAbstractList.this.remove(cursor);
            cursor--;
        }

        @Override
        public void set(E value) {
            BlackoutAbstractList.this.set(cursor, value);
        }

        @Override
        public void add(E value) {
            BlackoutAbstractList.this.add(cursor + 1, value);
        }
    }


}
