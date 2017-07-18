package com.junranhuigu.simpleJson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 验证语句是否只符合条件提取格式
	 * */
	public static boolean matchSelectNoContidion(String executeLanguage){
		return Pattern.matches("^(([A-Z|a-z|0-9|_]+(\\[-?[0-9]+\\])?)\\.)*([A-Z|a-z|0-9|_]+(\\[-?[0-9]+\\])?)$", executeLanguage);
	}
	
	/**
	 * 提取条件格式语句
	 * */
	public static String findSelect(String executeLanguage){
		Pattern pattern = Pattern.compile("(([A-Z|a-z|0-9|_]+(\\[-?[0-9]+\\])?)\\.)*([A-Z|a-z|0-9|_]+(\\[-?[0-9]+\\])?)");
		Matcher matcher = pattern.matcher(executeLanguage);
		if(matcher.find()){
			String exe = matcher.group();
			return exe;
		}
		return null;
	}
}
