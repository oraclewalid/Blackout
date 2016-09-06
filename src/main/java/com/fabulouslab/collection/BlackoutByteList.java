package com.fabulouslab.collection;

import com.fabulouslab.exception.BlackoutException;
import com.fabulouslab.util.Memory;

import java.util.Collection;
import java.util.Objects;

public class BlackoutByteList extends BlackoutAbstractList<Byte>{

    private static final int BYTE_LENGTH = 1;

    public BlackoutByteList(int capacity) {
        if(size < 0 )
            throw new IllegalArgumentException("Size must be higher than 0");
        this.size = 0;
        this.capacity = capacity;
        address = Memory.allocate(this.capacity * getLength());
    }

    public BlackoutByteList(byte... values) {
        Objects.requireNonNull(values);
        createOffHeapArray(values, values.length);
    }

    private void createOffHeapArray(byte[] values, int length) {
        this.size = length;
        this.capacity = length;
        address = Memory.allocate(this.capacity * getLength());

        for (int i = 0; i < length; i++) {
            long addr = address + i * getLength();
            Memory.putByte(addr, values[i]);
        }
    }

    @Override
    public Byte get(int index) {
        checkSizeBounds(index);
        long addr = address + index * getLength();
        return Memory.getByte(addr);
    }

    @Override
    public Byte set(int index, Byte element) {
        Byte oldValue = get(index);
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putByte(addr,element);
        return oldValue;
    }

    @Override
    public Object[] toArray() {
        Byte[] integers = new Byte[size];

        for (int i = 0; i < size ; i++) {
            integers[i] = get(i);
        }

        return integers;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        byte[] elementData = new byte[size];
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
    public boolean addAll(int index, Collection<? extends Byte> c) {

        try {
            checkLimits(index);
            if(!checkCapacity(c.size())){
                address = realocate(c.size(), index);
            }
            int i = 0;
            for (byte element : c) {
                long addr = Memory.computeAddr(address, index + i, getLength());
                Memory.putByte(addr,element);
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
    public boolean addAll(Collection<? extends Byte> c) {
        try {
            if(!checkCapacity(c.size())){
                address = realocate(c.size());
            }
            int i = 0;
            for (byte element : c) {
                long addr = Memory.computeAddr(address, size + i, getLength());
                Memory.putByte(addr,element);
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
    public void add(int index, Byte element) {
        checkLimits(index);
        if(!checkCapacity(1)){
            address = realocate(1, index);
        }
        long addr = Memory.computeAddr(address, index, getLength());
        Memory.putByte(addr,element);
        size = size + 1;
    }

    @Override
    public int getLength() {
        return BYTE_LENGTH;
    }

}
