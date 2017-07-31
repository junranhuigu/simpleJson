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
	
	/**
	 * 处理条件格式语句 统一格式
	 * @param isAttr 是否是基础属性
	 * */
	public static String unionExecutingLanguage(String executeLanguage, boolean isAttr){
		if(matchSelectNoContidion(executeLanguage)){//无判断条件
			return executeLanguage;
		}
		//有判断条件
		String aim = findSelect(executeLanguage);
		if(isAttr && !executeLanguage.contains("where")){
			executeLanguage = executeLanguage.replace(aim, aim + " where " + aim);
		}
		String[] infos = executeLanguage.split("where");
		//替换条件中省略的前缀
		if(infos.length > 1 && infos[1].matches("^\\s+\\.[\\s|\\S]+$")){
			String _infos = infos[1].replaceAll("\\s+\\.", " " + aim + (isAttr ? "" : "."));
			executeLanguage = executeLanguage.replace(infos[1], _infos);
		}
		return executeLanguage;
	}
}
