package org.thg.logic.story.driver.stage.effectFunc;

import org.thg.imageUtil.GaussianFilterExtend;
import org.thg.ui.Config;
/**
 * 模糊效果的函数支持
 * @author MyCapitaine
 *
 */
public class Effect_misty_func {
	
	private static float MAX_RADIUS = 30f * Config.scaleX;
	/**
	 * 模糊
	 */
	public static byte[] effect_misty(final byte[] sceneData, final int width, final int height,
			final float timeCount, final float limitTimeCount) {
		double rate = (double)(timeCount / limitTimeCount) * Math.PI / 2;
		
		
//		float radius =  (1 - (float)Math.cos(rate))* MAX_RADIUS;
		float radius =  (float)Math.sin(rate)* MAX_RADIUS;
		byte[] newData = GaussianFilterExtend.filter(sceneData, radius, width, height);
		return newData;
	}
	
	
}
