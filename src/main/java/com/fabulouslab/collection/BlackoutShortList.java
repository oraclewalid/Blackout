package com.fabulouslab.collection;

import com.fabulouslab.exception.BlackoutException;
import com.fabulouslab.util.Memory;

import java.util.Collection;
import java.util.Objects;

public class BlackoutShortList extends BlackoutAbstractList<Short>{

    private static final int SHORT_LENGTH = 2;

    public BlackoutShortList(int capacity) {
        if(size < 0 )
            throw new IllegalArgumentException("Size must be higher than 0");
        this.size = 0;
        this.capacity = capacity;
        address = Memory.allocate(this.capacity * getLength());
    }

    public BlackoutShortList(short... values) {
        Objects.requireNonNull(values);
        createOffHeapArray(values, values.length);
    }

    private void createOffHeapArray(short[] values, int length) {
        this.size = length;
        this.capacity = length;
        address = Memory.allocate(this.capacity * getLength());

        for (int i = 0; i < length; i++) {
            long addr = address + i * getLength();
            Memory.putShort(addr, values[i]);
        }
    }

    @Override
    public Short get(int index) {
        checkSizeBounds(index);
        long addr = address + index * getLength();
        return Memory.getShort(addr);
    }

    @Override
    public Short set(int index, Short element) {
        Short oldValue = get(index);
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putShort(addr,element);
        return oldValue;
    }

    @Override
    public Object[] toArray() {
        Short[] shorts = new Short[size];

        for (int i = 0; i < size ; i++) {
            shorts[i] = get(i);
        }

        return shorts;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        short[] elementData = new short[size];
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
    public boolean addAll(int index, Collection<? extends Short> c) {

        try {
            checkLimits(index);
            if(!checkCapacity(c.size())){
                address = reallocate(c.size(), index);
            }
            int i = 0;
            for (short element : c) {
                long addr = Memory.computeAddr(address, index + i, getLength());
                Memory.putShort(addr,element);
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
    public boolean addAll(Collection<? extends Short> c) {
        try {
            if(!checkCapacity(c.size())){
                address = reallocate(c.size());
            }
            int i = 0;
            for (short element : c) {
                long addr = Memory.computeAddr(address, size + i, getLength());
                Memory.putShort(addr,element);
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
    public void add(int index, Short element) {
        checkLimits(index);
        if(!checkCapacity(1)){
            address = reallocate(1, index);
        }
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putInt(addr,element);
        size = size + 1;
    }

    @Override
    public int getLength() {
        return SHORT_LENGTH;
    }

}