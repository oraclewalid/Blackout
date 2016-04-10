package com.fabulouslab.text;

import static com.fabulouslab.util.UnsafeUtil.getUnsafe;

//TODO implement others methode of string
//TODO implement Serializable and Comparable<String>

public class BlackoutString implements CharSequence {


    private static final int CHAR_LENGHT = 2;

    private int length;

    private long address;

    public BlackoutString(String first) {
        this.length = first.length();
        char[] inHeapStringAsChar = first.toCharArray();
        allocateOfHeap(inHeapStringAsChar);
    }

    private  void allocateOfHeap(char[] inHeapStringAsChar)  {

        try {
            address = getUnsafe().allocateMemory(length * CHAR_LENGHT);
            for (int i = 0; i < length; i++) {
                long memoryAddress = address + i * CHAR_LENGHT;
                getUnsafe().putChar(memoryAddress ,inHeapStringAsChar[i] );
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        try {
            long memoryAddress = address + index * CHAR_LENGHT;
            return getUnsafe().getChar(memoryAddress);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public CharSequence subSequence(int start, int end) {

        char[] inHeapStringAsChar = new char[end - start +1];
        for (int i = start; i <= end ; i++) {
            inHeapStringAsChar[i - start] = charAt(i);
        }
        return new String(inHeapStringAsChar);
    }

    @Override
    public String toString() {
        return (String) subSequence(0 , length);
    }
}
