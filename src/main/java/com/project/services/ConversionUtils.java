package com.project.services;

import org.springframework.stereotype.Component;

@Component
public class ConversionUtils {

    public static final String CONVERSION = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVXZY-._~";
    public static final int BASE = CONVERSION.length();

    public String trim(int id){
        StringBuilder str = new StringBuilder();
        while(id > 0){
            str.insert(0, CONVERSION.charAt(id % BASE));
            id = id / BASE;
        }
        return str.toString();
    }

    public int retrieve(String str){
        int id = 0;
        int n = str.length();

        for(int i=0;i < n;i++){
            int value = (int) Math.pow(BASE, i) * CONVERSION.indexOf(str.charAt(n-i-1));
            id += value;
        }
        return id;
    }
}
