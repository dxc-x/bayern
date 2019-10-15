package com.qhc.bayern.util;

public class StrToDouble {
	
	public static Double test(String aa) {
		if(aa.contains("-")) {
			String bb = "-"+aa.substring(0,aa.length()-1);
			return Double.valueOf(bb);
		}else {
			return Double.valueOf(aa);
		}
	}

}
