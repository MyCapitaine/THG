package org.thg.logic.factorys;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class ResourceFactory {
	/**
	 * 在进入次日时清空缓存
	 */
	public static void clearPool() {
		Iterator<Entry<Integer, Texture>> cpIter = cpPool.entrySet().iterator();
		while(cpIter.hasNext())
			cpIter.next().getValue().dispose();
		cpPool.clear();
		Iterator<Entry<Integer, Texture>> bgIter = bgPool.entrySet().iterator();
		while(bgIter.hasNext())
			bgIter.next().getValue().dispose();
		bgPool.clear();
		
		Iterator<Disposable> iter = trashList.iterator();
		while(iter.hasNext()) {
			iter.next().dispose();
		}
		trashList.clear();
		//TODO
	}
	
	private static LinkedList<Disposable> trashList = new LinkedList<Disposable>();
	public static void putTrash(Disposable trash) {
		trashList.add(trash);
	}
	
	
	public static Music getBgm(int num) {
		
		//0返回null
		//TODO
		return null;
	}
	
	private static HashMap<Integer, Texture> bgPool = new HashMap<Integer, Texture>();
	public static Texture getBg(int num) {
		if(num == 0) return null;
		if(bgPool.get(num) != null) return bgPool.get(num);
		try {
			Texture t = new Texture("images/resources/bg/" + Integer.toString(10000 + num) + ".png");
			bgPool.put(num, t);
			return t;
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
