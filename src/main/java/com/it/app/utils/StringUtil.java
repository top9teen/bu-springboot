package com.it.app.utils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtil {
	public static boolean isNull(String str) {
		if(null == str || "null".equals(str) || "".equals(str)) {
			return true;
		}
		return false;
	}
	public static boolean isNotNull(String str) {
		if(null != str || !"null".equals(str) || !"".equals(str)) {
			return true;
		}
		return false;
	}

	public static String subStringEmail(String email) {
		if (StringUtil.isNotNull(email)) {
			StringBuffer result = new StringBuffer();
			int count = 0;
			result.append(email.substring(0, 2));
			count = email.substring(2, email.lastIndexOf("@")).length();
			String s = "x";
			result.append(IntStream.range(0, count).mapToObj(i -> s).collect(Collectors.joining("")));
			result.append(email.substring(email.indexOf("@")));
			return result.toString();
		} else {
			return null;
		}
	}
}
