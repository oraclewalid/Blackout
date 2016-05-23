package com.fabulouslab.collection;


import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import com.fabulouslab.util.Memory;

public class BlackoutIntegerArray implements List<Integer>{

    private static final int DEFAULT_CAPACITY = 10;

    private static final int INTEGER_LENGHT = 4;

    private long address;

    // Size is in heap
    private int size ;

    private int capacity;

    public BlackoutIntegerArray(int capacity) {
        if(size < 0 )
            throw new IllegalArgumentException("Size must be higher than 0");
        this.size = 0;
        this.capacity = capacity;
        address = Memory.allocate(this.capacity * INTEGER_LENGHT);
    }

    public BlackoutIntegerArray(int[] values) {
        this.size = values.length;
        this.capacity = values.length;
        address = Memory.allocate(this.capacity * INTEGER_LENGHT);

        for (int i = 0; i < values.length; i++) {
            long addr = address + i * INTEGER_LENGHT;
            Memory.putInt(addr, values[i]);
        }
    }

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
    public Integer get(int index) {
        checkBounds(index);
        long addr = address + index * INTEGER_LENGHT;
        return Memory.getInt(addr);
    }

    @Override
    public Integer remove(int index) {

        int oldValue = get(index);
        long oldValueAddress = Memory.computeAddr(address, index, INTEGER_LENGHT);
        long nextValueAddress = Memory.computeAddr(address, index+1, INTEGER_LENGHT);;
        long lenght = (capacity - index - 1) * INTEGER_LENGHT;

        Memory.copyMemory(nextValueAddress, oldValueAddress, lenght);
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
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int counter = -1;
            @Override
            public boolean hasNext() {
                return counter + 1 < size;
            }

            @Override
            public Integer next() {
                return get(++counter);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {

    }

    @Override
    public Object[] toArray() {
        Integer[] integers = new Integer[size];

        for (int i = 0; i < size ; i++) {
            integers[i] = get(i);
        }

        return integers;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
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
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super Integer> filter) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void sort(Comparator<? super Integer> c) {

    }

    @Override
    public void replaceAll(UnaryOperator<Integer> operator) {

    }

    @Override
    public void clear() {
        Memory.free(address);
        size = 0;
        address = Memory.allocate(this.capacity * INTEGER_LENGHT);

    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return null;
    }

    @Override
    public Stream<Integer> stream() {
        return null;
    }

    @Override
    public Stream<Integer> parallelStream() {
        return null;
    }

    @Override
    public void add(int index, Integer element) {

    }

    @Override
    public Integer set(int index, Integer element) {
        Integer oldValue = get(index);
        long addr = Memory.computeAddr(address, index, INTEGER_LENGHT);
        Memory.putInt(addr,element);
        return oldValue;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean add(Integer integer) {
        return false;
    }

    private void checkBounds(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("index" + index + "is out of bound");
    }
}
