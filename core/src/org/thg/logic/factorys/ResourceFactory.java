package org.thg.logic.factorys;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class ResourceFactory {
	
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
	
	public static Texture getCharactorPic(int num) {
		if(num == 0) return null;
		try {
			return new Texture("images/resources/charactorpic/" + Integer.toString(num + 10000) + ".png");
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
