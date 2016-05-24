package com.fabulouslab;

import com.fabulouslab.text.BlackoutString;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws NoSuchFieldException, IllegalAccessException {

        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        System.out.print(integerList.toString());
    }
}
