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
		Iterator<Entry<Integer, Texture>> cpIter = cpPool.entrySet().iterator();
		while(cpIter.hasNext())
			cpIter.next().getValue().dispose();
		cpPool.clear();
		Iterator<Entry<Integer, Texture>> bgIter = bgPool.entrySet().iterator();
		while(bgIter.hasNext())
			bgIter.next().getValue().dispose();
		bgPool.clear();
		//TODO
	}
	
	private static Texture bgBufferTexture = null;
	/** <p>将texture对象压入缓存顶，之后无需关系其销毁 <p><b>背景专用 */
	public static void putBgBufferTexture(Texture bufferTexture) {
		if(bgBufferTexture != null) bgBufferTexture.dispose();
		bgBufferTexture = bufferTexture;
	}
	/** <p>从缓存取用顶上的texture对象，但是不能取出 <p><b>背景专用 */
	public static Texture getBgBufferTexture() {
		return bgBufferTexture;
	}
	
	
	public static Music getBgm(int num) {
		
		//0返回null
		//TODO
		return null;
	}
	
	private static HashMap<Integer, Texture> bgPool = new HashMap<Integer, Texture>();
	/**
	 * <p>向仓库获取背景资源
	 * <p>若不是普通的资源，则自动向缓存索取
	 */
	public static Texture getBg(int num) {
		if(bgPool.get(num) != null) return bgPool.get(num);
		try {
			Texture t = new Texture("images/resources/bg/" + Integer.toString(10000 + num) + ".png");
			bgPool.put(num, t);
			return t;
		}catch(Exception e) {
			return getBgBufferTexture();
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
