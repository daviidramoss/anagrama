package com.freeagent.anagram.util;

import java.util.ArrayList;
import java.util.Arrays;

public class UtilString {


    public static ArrayList<String> arregloDeString(String str) {
        if (str.length() == 0) {
            ArrayList<String> baseResult = new ArrayList<>();
            baseResult.add("");
            return baseResult;
        }
        char ch = str.charAt(0);
        String rest = str.substring(1);
        ArrayList<String> recResult = arregloDeString(rest);
        ArrayList<String> myResult = new ArrayList<>();
        for (int i = 0; i < recResult.size(); i++) {
            String s = recResult.get(i);
            for (int j = 0; j <= s.length(); j++) {
                String newString = s.substring(0, j) + ch + s.substring(j);
                myResult.add(newString.toUpperCase());
            }
        }
        return myResult;
    }
    public static  String  devolverOrdenado(String palabra){
        char charArray[] = palabra.toUpperCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
