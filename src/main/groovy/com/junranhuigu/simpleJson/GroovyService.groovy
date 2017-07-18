package com.junranhuigu.simpleJson

import com.alibaba.fastjson.JSON

/**
 * 使用语句获取json中的数据
 * @param executeLanguage 执行语句
 * */
private static List<Object> executeJSON(String executeLanguage, root){
	//执行
	Binding binding = new Binding();
	binding.setVariable("root", root);
	GroovyShell shell = new GroovyShell(binding);
	if(!StringUtil.matchSelectNoContidion(executeLanguage)){
		String exe = StringUtil.findSelect(executeLanguage);
		String replaceLanguage = executeLanguage.replace(exe, '')
		executeLanguage = exe + '.findAll{ it ' + replaceLanguage + ' }'
	}
	Object obj = shell.evaluate("return " + executeLanguage);
	if(List.class.isAssignableFrom(obj.getClass())){
		List<Object> list = obj;
		return list;
	} else {
		List<Object> list = new ArrayList<>();
		list.add(obj);
		return list;
	}
}

/**
 * 使用语句获取json中的数据
 * @param executeLanguage 执行语句
 * */
static Map<String, List<Object>> execute4Some(List<String> executeLanguage, String json){
	def root = JSON.parse(json);
	Map<String, List<Object>> result = new HashMap<>();
	for(String l : executeLanguage){
		if(!result.containsKey(l)){
			result.put(l, executeJSON(l, root));
		}
	}
	return result;
}

/**
 * 使用语句获取json中的数据
 * @param executeLanguage 执行语句
 * */
static <T> List<T> execute4One(String executeLanguage, String json, Class<T> cls){
	List<String> list = [executeLanguage];
	return execute4Some(list, json)[executeLanguage];
}


