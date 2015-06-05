package com.edwin.common.tools.lang;

import java.text.NumberFormat;

/**
 * @author jinming.wu
 * @date 2015-5-17
 */
public class NumberHelper {
    
    public static void main(String args[]) {
        NumberFormat idFormat = NumberFormat.getInstance();
        idFormat.setGroupingUsed(false);
        idFormat.setMinimumIntegerDigits(4);
        System.out.println(idFormat.format(0.12123));
    }
}
