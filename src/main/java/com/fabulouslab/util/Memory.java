package com.fabulouslab.util;

import com.fabulouslab.exception.BlackoutException;

import static com.fabulouslab.util.UnsafeUtil.getUnsafe;


public class Memory {

    public static long allocate(long length)  {

        try {
            long address = getUnsafe().allocateMemory(length);
            return address;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  copyMemory(long oldValueAddress, long newValueAddress, long lenght){
        try {
            getUnsafe().copyMemory(oldValueAddress, newValueAddress, lenght);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  free(long addr){
        try {
            getUnsafe().freeMemory(addr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }
    public static void  putInt(long addr, int value){
        try {
            getUnsafe().putInt(addr ,value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static int getInt(long addr){
        try {
            return getUnsafe().getInt(addr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static long computeAddr(long addr, int index, int lenght){
        return addr + index * lenght;
    }

}
