package org.thg.logic.factorys;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class ResourceFactory {
	/**
	 * 在进入次日时清空缓存
	 */
	public static void clearPool() {
		Iterator<Entry<Integer, Texture>> iter = cpPool.entrySet().iterator();
		while(iter.hasNext())
			iter.next().getValue().dispose();
		cpPool.clear();
		//TODO
	}
	
	
	public static Music getBgm(int num) {
		
		//0返回null
		//TODO
		return null;
	}
	
	public static Texture getBg(int num) {
		String s = Integer.toString(10000 + num);
		try {
			return new Texture("images/resources/bg/" + s + ".png");
		}catch(Exception e) {
			System.err.println("ResourceFactory.getBg() error");
			return null;
		}
	}
	
	
	private static HashMap<Integer, Texture> cpPool = new HashMap<Integer, Texture>();
	
	public static Texture getCharactorPic(int num) {
		if(num == 0) return null;
		if(cpPool.get(num) != null) return cpPool.get(num);
		
		try {
			Texture t = new Texture("images/resources/charactorpic/" + Integer.toString(num + 10000) + ".png");
			cpPool.put(num, t);
			return t;
			
		}catch(Exception e) { 
			System.err.println("ResourceFactory.getCharactorPic() error");
			return null;
		}
	}
	public static Music getVoice(int num) {
//		TODO
		
		return null;
	}
	
	public static final int PRE_INT = -1;
}
