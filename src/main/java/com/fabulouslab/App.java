package com.fabulouslab;

import com.fabulouslab.text.BlackoutString;

public class App
{
    public static void main( String[] args ) throws NoSuchFieldException, IllegalAccessException {

        BlackoutString ohStrinng = new BlackoutString("toto est en memoire");
        System.out.print(ohStrinng.toString());
    }
}
