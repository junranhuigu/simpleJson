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
		String aim = StringUtil.findSelect(executeLanguage);
		Object obj = shell.evaluate("return " + aim);
		boolean isAttr = true;
		executeLanguage = StringUtil.unionExecutingLanguage(executeLanguage, JsonUtil.isAttr(executeLanguage, JSON.toJSONString(root)));
		String replaceLanguage = executeLanguage.split("where")[1].replace(aim, "it");
		executeLanguage = aim + '.findAll{ ' + replaceLanguage + ' }';
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

String json = '{"cardList":[{"id":70045,"number":0,"pieceNumber":0},{"id":70002,"number":1,"pieceNumber":0},{"id":70051,"number":0,"pieceNumber":0},{"id":70058,"number":3,"pieceNumber":0},{"id":70048,"number":0,"pieceNumber":0},{"id":70028,"number":0,"pieceNumber":0},{"id":70010,"number":0,"pieceNumber":0},{"id":70052,"number":0,"pieceNumber":0},{"id":70012,"number":0,"pieceNumber":0},{"id":70053,"number":0,"pieceNumber":0},{"id":70055,"number":0,"pieceNumber":0},{"id":70024,"number":1,"pieceNumber":0}],"charmList":[],"clothNew":80001,"clouthList":[{"id":80001,"isWear":1}],"eightList":[{"level":20,"stage":1,"stageexp":0,"type":1,"valueList":[[1,53]],"valueList2":[[6,18]]},{"level":2,"stage":0,"stageexp":0,"type":2,"valueList":[[3,204]],"valueList2":[]},{"level":20,"stage":1,"stageexp":0,"type":3,"valueList":[[2,77]],"valueList2":[[8,14]]},{"level":2,"stage":0,"stageexp":0,"type":4,"valueList":[[4,11]],"valueList2":[]},{"level":17,"stage":1,"stageexp":0,"type":5,"valueList":[[5,168]],"valueList2":[[0,0]]},{"level":9,"stage":0,"stageexp":0,"type":6,"valueList":[[6,42]],"valueList2":[]},{"level":20,"stage":1,"stageexp":0,"type":7,"valueList":[[7,200]],"valueList2":[[7,22]]},{"level":1,"stage":0,"stageexp":0,"type":8,"valueList":[[8,0]],"valueList2":[]}],"id":"2210095328662901","itemList":[{"itemId":50040,"num":10}],"piece":[{"itemId":91004,"num":2},{"itemId":92006,"num":8},{"itemId":92007,"num":4},{"itemId":91002,"num":5},{"itemId":91003,"num":3}],"soulList":[{"breakLevel":2,"id":21044,"mosaicPosition":0,"skillPosition":1,"strengthLevel":21,"uuid":"yCJOt"},{"breakLevel":2,"id":21035,"mosaicPosition":0,"skillPosition":6,"strengthLevel":21,"uuid":"NDsOk"},{"breakLevel":2,"id":21040,"mosaicPosition":0,"skillPosition":2,"strengthLevel":21,"uuid":"3B7rH"},{"breakLevel":0,"id":21001,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"WNjjG"},{"breakLevel":2,"id":21033,"mosaicPosition":0,"skillPosition":7,"strengthLevel":21,"uuid":"KcMbM"},{"breakLevel":0,"id":21012,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"Hrp7l"},{"breakLevel":2,"id":21003,"mosaicPosition":0,"skillPosition":8,"strengthLevel":21,"uuid":"QXN5Q"},{"breakLevel":0,"id":21038,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"4PU3Q"},{"breakLevel":0,"id":21042,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"VhqW9"},{"breakLevel":0,"id":21001,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"aff2i"},{"breakLevel":0,"id":21012,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"8330z"},{"breakLevel":0,"id":21039,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"Yk7Qc"},{"breakLevel":0,"id":21038,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"T5IXN"},{"breakLevel":2,"id":21014,"mosaicPosition":0,"skillPosition":3,"strengthLevel":21,"uuid":"QQqYr"},{"breakLevel":0,"id":21042,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"1p8Sp"},{"breakLevel":0,"id":21001,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"Jv7JD"},{"breakLevel":0,"id":21012,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"ZqKO6"},{"breakLevel":0,"id":21040,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"ELie1"},{"breakLevel":0,"id":21038,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"1Nsk8"},{"breakLevel":0,"id":21042,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"PTtLO"},{"breakLevel":0,"id":21001,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"24CtR"},{"breakLevel":0,"id":21012,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"Dwm7z"},{"breakLevel":0,"id":21039,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"FCj6Y"},{"breakLevel":0,"id":21038,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"XHXA5"},{"breakLevel":0,"id":21042,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"gstGi"},{"breakLevel":0,"id":21001,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"OIdrC"},{"breakLevel":0,"id":21012,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"Kkfbd"},{"breakLevel":0,"id":21038,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"Fvddd"},{"breakLevel":0,"id":21042,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"NKlKX"},{"breakLevel":0,"id":21013,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"etuxT"},{"breakLevel":0,"id":21001,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"rXtkv"},{"breakLevel":0,"id":21012,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"bK0rB"},{"breakLevel":0,"id":21013,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"rhRsv"},{"breakLevel":0,"id":21038,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"1jH5h"},{"breakLevel":0,"id":21042,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"ehkBX"},{"breakLevel":1,"id":21006,"mosaicPosition":0,"skillPosition":4,"strengthLevel":19,"uuid":"CQDdz"},{"breakLevel":0,"id":21038,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"ZrwAn"},{"breakLevel":0,"id":21029,"mosaicPosition":0,"skillPosition":0,"strengthLevel":0,"uuid":"YX5HZ"}],"weaponList":[{"exp":0,"isOk":-1,"isWear":0,"level":8,"skillId":10358,"valueList":[[1,210],[4,20],[5,26],[6,19]],"weaponId":10001},{"exp":0,"isOk":-1,"isWear":1,"level":8,"skillId":10359,"valueList":[[1,373],[4,34],[5,44],[6,33]],"weaponId":10002}],"weaponNew":10002,"wearCharmList":[]}';
JSON root = JSON.parseObject(json);
println root.cardList.findAll{ it.id < 70040 };

