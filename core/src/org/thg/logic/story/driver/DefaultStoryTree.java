package org.thg.logic.story.driver;

import java.util.HashMap;

import org.thg.logic.story.api.GStoryTree;

public class DefaultStoryTree implements GStoryTree {
	private HashMap<String, String> datas;
	
	public DefaultStoryTree() {
		datas = new HashMap<String, String>();
	}
	
	public final void put(String dayStr, String otherPart) {
		datas.put(dayStr, otherPart);
	}
	
	
	public String getNextDay(String dayStr) {
//		String value = datas.get(dayStr);
//		if(value == null) return null;
//		if(!value.contains(")")) return value;
//		String[] roads = value.split(")");
//		    for() TODO
//		return null;
		return dayStr;
	}

}
