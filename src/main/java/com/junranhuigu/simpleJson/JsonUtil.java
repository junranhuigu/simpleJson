package com.junranhuigu.simpleJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	/**
	 * json数据结构的初步解析结果
	 * @param detail 显示详细信息
	 * */
	public static String jsonStructure(String json) throws Exception{
		Object obj = JSON.parse(json);
		Set<String> keys = new HashSet<>();
		jsonStructure(obj, null, "", keys , true);
		StringBuilder builder = new StringBuilder();
		List<String> list = new ArrayList<>(keys);
		Collections.sort(list);
		for(int i = 0; i < list.size(); ++ i){
			builder.append(list.get(i)).append("\n");
		}
		return builder.toString();
	}
	
	private static void jsonStructure(Object obj, String name, String keyPath, Set<String> keys, boolean isRoot){
		if(isRoot){//root
			name = "root";
		}
		if(obj instanceof JSONObject){
			JSONObject jsonObject = (JSONObject) obj;
			if(name == null || "".equals(name)){
				keyPath = structureStringObject(keyPath, null);
			} else {
				keyPath = structureStringObject(keyPath, name);
			}
			keys.add(keyPath);
			for(String key : jsonObject.keySet()){
				jsonStructure(jsonObject.get(key), key, keyPath, keys, false);
			}
		} else if(obj instanceof JSONArray){
			JSONArray jsonArray = (JSONArray) obj;
			if(name == null || "".equals(name)){
				keyPath = structureStringArray(keyPath, null);
			} else {
				keyPath = structureStringArray(keyPath, name);
			}
			keys.add(keyPath);
			for(Object _o : jsonArray){
				jsonStructure(_o, null, keyPath, keys, false);
			}
		} else {
			keyPath = jsonStructureAttr(keyPath, name);
			keys.add(keyPath);
		}
	}
	
	/**
	 * 获取结构字符串
	 * @param structure 用于存放结构字符串的容器
	 * @param parentStructureString 父类字符串
	 * */
	private static String jsonStructureAttr(String parentStructureString, String name){
		if(name != null && !"".equals(name)){
			parentStructureString += "." + name;
		}
		return parentStructureString;
	}
	
	/**
	 * 获取结构字符串
	 * @param structure 用于存放结构字符串的容器
	 * @param parentStructureString 父类字符串
	 * */
	private static String structureStringArray(String parentStructureString, String name){
		if(name != null && !"".equals(name)){
			parentStructureString += "." + name;
		}
		parentStructureString += "{Array}";
		return parentStructureString;
	}
	
	/**
	 * 获取结构字符串
	 * @param structure 用于存放结构字符串的容器
	 * @param parentStructureString 父类字符串
	 * */
	private static String structureStringObject(String parentStructureString, String name){
		if(name != null && !"".equals(name)){
			parentStructureString += "." + name;
		} 
		parentStructureString += "{Object}";
		return parentStructureString;
	}
	
	/**
	 * 使用语句获取json中的数据
	 * @param executeLanguage 执行语句
	 * */
	public static List<Object> select(String executeLanguage, String json){
		return GroovyService.execute4One(executeLanguage, json);
	}
	
	/**
	 * 使用语句获取json中的数据
	 * @param executeLanguage 多个执行语句
	 * @return key 执行语句 value 执行结果
	 * */
	public static Map<String, List<Object>> select(List<String> executeLanguage, String json){
		return GroovyService.execute4Some(executeLanguage, json);
	}
}
