package org.thg.logic.story.driver.stage.effectFunc;

import org.thg.imageUtil.GaussianFilterExtend;
import org.thg.ui.Config;

public class Effect_misty_func {
	
	private static float MAX_RADIUS = 30f * Config.scaleX;
	/**
	 * 非回复的模糊
	 */
	public static byte[] effect_misty_withoutback(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		float radius = timeCount / limitTimeCount * MAX_RADIUS;
		byte[] newData = GaussianFilterExtend.filter(sceneData, radius, width, height);
		return newData;
	}
	/**
	 * 回复的模糊
	 */
	public static byte[] effect_misty_withback(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		float rate = timeCount / limitTimeCount;
		float radius;
		if(rate > 0.5) radius = (1 - rate) * MAX_RADIUS;
		else radius = rate * MAX_RADIUS;
		//线性变化
		byte[] newData = GaussianFilterExtend.filter(sceneData, radius, width, height);
		return newData;
	}
	
	
	
}
