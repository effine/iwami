package cn.effine;

import org.springframework.beans.BeanUtils;

public class Test {

	public static void main(String[] args) {
		String s1 = "helloword";
		String s2 = new String("helloword");
		String s3 = "hello" + "word";
		String s4 = new String("hello") + "word";

		System.out.println(s1 == s2); // false
		System.out.println(s1 == s3); // true
		System.out.println(s1 == s4); // false
		System.out.println(s2 == s4); // false
		
		String.valueOf(s1);
		s1.toString();
		BeanUtils.copyProperties("", "");
	}
}
