package org.thg.logic.story.driver.stage.effectFunc;

import org.thg.imageUtil.DissolveFilterExtend;
/**
 * 雪花效果的函数支持
 * @author MyCapitaine
 *
 */
public class Effect_snowflake_func {
	public static byte[] effect_snowflake(final byte[] sceneData, final int width, final int height,
			final float timeCount, final float limitTimeCount) {
		DissolveFilterExtend dfe = new DissolveFilterExtend();
		
		dfe.density = 0.5f;
		dfe.softness = 0f;
		
		return dfe.filter(sceneData, width, height, System.currentTimeMillis());
	}
}
