package com.iwami.iwami.app.util;

import java.util.Random;

public class IWamiUtils {
	
    private static final char[] CODE_ARRAY_NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * @desc 获取随机数
	 * @param length
	 *            随机数位数
	 * @return 字符串
	 */
    public static String getRandInt(int length) {
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        sb.append(CODE_ARRAY_NUMBER[random.nextInt(CODE_ARRAY_NUMBER.length)]);
	    }
	    return sb.toString();
    }
    
	/**
	 * @desc 验证手机号码
	 * @param phone
	 *            手机号
	 * @return Boolea
	 */
	public static boolean validatePhone(String phone) {
        if (phone.length() != 11 || !phone.startsWith("1")) return false;
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (c < '0' || c > '9')  return false;
        }
        return true;
    }
}
