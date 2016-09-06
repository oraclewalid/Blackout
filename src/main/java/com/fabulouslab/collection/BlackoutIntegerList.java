package com.fabulouslab.collection;


import java.util.*;

import com.fabulouslab.exception.BlackoutException;
import com.fabulouslab.util.Memory;

public class BlackoutIntegerList extends BlackoutAbstractList<Integer> {

    private static final int INTEGER_LENGTH = 4;

    public BlackoutIntegerList(int capacity) {
        if(size < 0 )
            throw new IllegalArgumentException("Size must be higher than 0");
        this.size = 0;
        this.capacity = capacity;
        address = Memory.allocate(this.capacity * getLength());
    }

    public BlackoutIntegerList(int... values) {
        Objects.requireNonNull(values);
        createOffHeapArray(values, values.length);
    }

    private void createOffHeapArray(int[] values, int length) {
        this.size = length;
        this.capacity = length;
        address = Memory.allocate(this.capacity * getLength());

        for (int i = 0; i < length; i++) {
            long addr = address + i * getLength();
            Memory.putInt(addr, values[i]);
        }
    }

    @Override
    public Integer get(int index) {
        checkSizeBounds(index);
        long addr = address + index * getLength();
        return Memory.getInt(addr);
    }
    public Integer set(int index, Integer element) {
        Integer oldValue = get(index);
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putInt(addr,element);
        return oldValue;
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
    public boolean removeAll(Collection<?> c) {
        int[] elementData = new int[size];
        int newIndex = 0;
        for (int i = 0; i < size ; i++) {
            if (!c.contains(get(i))) {
                elementData[newIndex++] = get(i);
            }
        }
        if (newIndex != size) {
            long oldAddress = address;
            createOffHeapArray(elementData, newIndex);
            Memory.free(oldAddress);
            return true;
        }

        return false;
    }


    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {

        try {
            checkLimits(index);
            if(!checkCapacity(c.size())){
                address = realocate(c.size(), index);
            }
            int i = 0;
            for (int element : c) {
                long addr = Memory.computeAddr(address, index + i, getLength());
                Memory.putInt(addr,element);
                i++;
            }
            size = size + c.size();
            return true;
        }
        catch (BlackoutException ex){
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        try {
            if(!checkCapacity(c.size())){
                address = realocate(c.size());
            }
            int i = 0;
            for (int element : c) {
                long addr = Memory.computeAddr(address, size + i, getLength());
                Memory.putInt(addr,element);
                i++;
            }
            size = size + c.size();
            return true;
        }
        catch (BlackoutException ex){
            return false;
        }
    }

    @Override
    public void add(int index, Integer element) {
        checkLimits(index);
        if(!checkCapacity(1)){
            address = realocate(1, index);
        }
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putInt(addr,element);
        size = size + 1;
    }

    @Override
    public int getLength() {
        return INTEGER_LENGTH;
    }

}
