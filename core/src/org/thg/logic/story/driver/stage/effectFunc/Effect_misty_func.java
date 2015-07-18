package org.thg.logic.story.driver.stage.effectFunc;

import org.thg.imageUtil.GaussianFilterExtend;
import org.thg.ui.Config;

public class Effect_misty_func {
	
	private static float MAX_RADIUS = 30f * Config.scaleX;
	/**
	 * 模糊
	 */
	public static byte[] effect_misty(final byte[] sceneData, final int width, final int height,
			final float timeCount, final float limitTimeCount) {
		float radius = timeCount / limitTimeCount * MAX_RADIUS;
		byte[] newData = GaussianFilterExtend.filter(sceneData, radius, width, height);
		return newData;
	}
	
	
}
