package com.fabulouslab.util;

import static com.fabulouslab.util.UnsafeUtil.getUnsafe;


public class Memory {

    public static void  copyMemory(long oldValueAddress, long newValueAddress, long lenght){
        try {
            getUnsafe().copyMemory(oldValueAddress, newValueAddress, lenght);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw  new RuntimeException(e);
        }
    }

}
