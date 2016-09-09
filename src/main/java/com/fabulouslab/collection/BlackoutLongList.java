package com.fabulouslab.collection;

import com.fabulouslab.exception.BlackoutException;
import com.fabulouslab.util.Memory;

import java.util.Collection;
import java.util.Objects;

public class BlackoutLongList extends BlackoutAbstractList<Long>{

    private static final int SHORT_LENGTH = 8;

    public BlackoutLongList(int capacity) {
        if(size < 0 )
            throw new IllegalArgumentException("Size must be higher than 0");
        this.size = 0;
        this.capacity = capacity;
        address = Memory.allocate(this.capacity * getLength());
    }

    public BlackoutLongList(long... values) {
        Objects.requireNonNull(values);
        createOffHeapArray(values, values.length);
    }

    private void createOffHeapArray(long[] values, int length) {
        this.size = length;
        this.capacity = length;
        address = Memory.allocate(this.capacity * getLength());

        for (int i = 0; i < length; i++) {
            long addr = address + i * getLength();
            Memory.putLong(addr, values[i]);
        }
    }

    @Override
    public Long get(int index) {
        checkSizeBounds(index);
        long addr = address + index * getLength();
        return Memory.getLong(addr);
    }
    @Override
    public Long set(int index, Long element) {
        Long oldValue = get(index);
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putLong(addr,element);
        return oldValue;
    }

    @Override
    public Object[] toArray() {
        Long[] integers = new Long[size];

        for (int i = 0; i < size ; i++) {
            integers[i] = get(i);
        }

        return integers;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        long[] elementData = new long[size];
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
    public boolean addAll(int index, Collection<? extends Long> c) {

        try {
            checkLimits(index);
            if(!checkCapacity(c.size())){
                address = realocate(c.size(), index);
            }
            int i = 0;
            for (Long element : c) {
                long addr = Memory.computeAddr(address, index + i, getLength());
                Memory.putLong(addr,element);
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
    public boolean addAll(Collection<? extends Long> c) {
        try {
            if(!checkCapacity(c.size())){
                address = realocate(c.size());
            }
            int i = 0;
            for (long element : c) {
                long addr = Memory.computeAddr(address, size + i, getLength());
                Memory.putLong(addr,element);
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
    public void add(int index, Long element) {
        checkLimits(index);
        if(!checkCapacity(1)){
            address = realocate(1, index);
        }
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putLong(addr,element);
        size = size + 1;
    }

    @Override
    public int getLength() {
        return SHORT_LENGTH;
    }

}