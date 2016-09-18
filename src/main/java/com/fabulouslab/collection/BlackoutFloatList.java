package com.fabulouslab.collection;


import com.fabulouslab.exception.BlackoutException;
import com.fabulouslab.util.Memory;

import java.util.Collection;
import java.util.Objects;

public class BlackoutFloatList extends BlackoutAbstractList<Float>{

    private static final int FLOAT_LENGTH = 4;

    public BlackoutFloatList(int capacity) {
        if(size < 0 )
            throw new IllegalArgumentException("Size must be higher than 0");
        this.size = 0;
        this.capacity = capacity;
        address = Memory.allocate(this.capacity * getLength());
    }

    public BlackoutFloatList(float... values) {
        Objects.requireNonNull(values);
        createOffHeapArray(values, values.length);
    }

    private void createOffHeapArray(float[] values, int length) {
        this.size = length;
        this.capacity = length;
        address = Memory.allocate(this.capacity * getLength());

        for (int i = 0; i < length; i++) {
            long addr = address + i * getLength();
            Memory.putFloat(addr, values[i]);
        }
    }

    @Override
    public Float get(int index) {
        checkSizeBounds(index);
        long addr = address + index * getLength();
        return Memory.getFloat(addr);
    }
    @Override
    public Float set(int index, Float element) {
        Float oldValue = get(index);
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putFloat(addr,element);
        return oldValue;
    }

    @Override
    public Object[] toArray() {
        Float[] floats = new Float[size];

        for (int i = 0; i < size ; i++) {
            floats[i] = get(i);
        }

        return floats;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        float[] elementData = new float[size];
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
    public boolean addAll(int index, Collection<? extends Float> c) {

        try {
            checkLimits(index);
            if(!checkCapacity(c.size())){
                address = reallocate(c.size(), index);
            }
            int i = 0;
            for (Float element : c) {
                long addr = Memory.computeAddr(address, index + i, getLength());
                Memory.putFloat(addr,element);
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
    public boolean addAll(Collection<? extends Float> c) {
        try {
            if(!checkCapacity(c.size())){
                address = reallocate(c.size());
            }
            int i = 0;
            for (Float element : c) {
                long addr = Memory.computeAddr(address, size + i, getLength());
                Memory.putFloat(addr,element);
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
    public void add(int index, Float element) {
        checkLimits(index);
        if(!checkCapacity(1)){
            address = reallocate(1, index);
        }
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putFloat(addr,element);
        size = size + 1;
    }

    @Override
    public int getLength() {
        return FLOAT_LENGTH;
    }
}
