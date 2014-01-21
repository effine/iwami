package com.iwami.iwami.app.util;

import java.util.Random;

public class IWamiUtils {
	
    private static final char[] CODE_ARRAY_NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public static String getRandInt(int length) {
	    Random rand = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        sb.append(CODE_ARRAY_NUMBER[rand.nextInt(CODE_ARRAY_NUMBER.length)]);
	    }
	    return sb.toString();
    }
    
	public static boolean validatePhone(String phone) {
        if (phone.length() != 11) {
            return false;
        }
        if (!phone.startsWith("1")) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
