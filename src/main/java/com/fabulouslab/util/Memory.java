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

    public static void  copyMemory(long oldPositionAddress, long newPositionAddress, long length){
        try {
            getUnsafe().copyMemory(oldPositionAddress, newPositionAddress, length);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  swap(long firstAddress, long secondAddress, int lenght){
        try {
            long tmpAdress = getUnsafe().allocateMemory(lenght);
            copyMemory(firstAddress, tmpAdress, lenght);
            copyMemory(secondAddress, firstAddress, lenght);
            copyMemory(tmpAdress, secondAddress, lenght);
            free(tmpAdress);

        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  swapIndex(long addr, int firstIndex, int secondIndex, int length){
        long firstAddr = computeAddr(addr, firstIndex, length);
        long secondAddr = computeAddr(addr, secondIndex, length);
        swap(firstAddr, secondAddr, length);
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

    public static void  putFloat(long addr, float value){
        try {
            getUnsafe().putFloat(addr ,value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static float getFloat(long addr){
        try {
            return getUnsafe().getFloat(addr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  putByte(long addr, byte value){
        try {
            getUnsafe().putByte(addr ,value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static byte getByte(long addr){
        try {
            return getUnsafe().getByte(addr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  putShort(long addr, short value){
        try {
            getUnsafe().putShort(addr ,value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static short getShort(long addr){
        try {
            return getUnsafe().getShort(addr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  putLong(long addr, long value){
        try {
            getUnsafe().putLong(addr ,value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static long getLong(long addr){
        try {
            return getUnsafe().getLong(addr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static void  putDouble(long addr, double value){
        try {
            getUnsafe().putDouble(addr ,value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static double getDouble(long addr){
        try {
            return getUnsafe().getDouble(addr);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new BlackoutException(e);
        }
    }

    public static long computeAddr(long addr, int index, int length){
        return addr + index * length;
    }

}
