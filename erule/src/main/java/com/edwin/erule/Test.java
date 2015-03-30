package com.edwin.erule;

import java.io.UnsupportedEncodingException;

/**
 * @author jinming.wu
 * @date 2015-3-26
 */
public class Test {
    
    public static void main(String args[]) {
        try {
         // Convert from Unicode to UTF-8
         String string = "\u0001";
         byte[] utf8 = string.getBytes("UTF-8");
         // Convert from UTF-8 to Unicode
         string = new String(utf8, "UTF-8");
         System.out.println(string);
         } catch (UnsupportedEncodingException e) {
         }
        
    }
}
