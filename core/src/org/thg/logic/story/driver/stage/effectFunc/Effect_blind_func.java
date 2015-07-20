package org.thg.logic.story.driver.stage.effectFunc;

import org.thg.imageUtil.ExposureFilterExtend;

/**
 * 闪瞎狗眼效果的函数扩展
 * @author MyCapitaine
 *
 */
public class Effect_blind_func {
	
	private static final float MAX_EXPOSURE = 1000f;
	
	private static ExposureFilterExtend efe = new ExposureFilterExtend();
	public static byte[] effect_blind(final byte[] sceneData, final int width, final int height,
			final float timeCount, final float limitTimeCount) {
		double rate = (double)(timeCount / limitTimeCount);
		float exposure = (float)Math.pow(rate, 3) * MAX_EXPOSURE;
		
		if(exposure < 1.3f) return sceneData;
		if(exposure > 800f) return white(width * height);
		
		efe.setExposure(exposure);
		return efe.filter(sceneData, width, height);
		
	}
	
	private static byte[] white(int length) {
		byte[] white = new byte[length * 4];
		for(int i = 0; i < length; i ++) {
			white[4 * i + 3] = (byte)0xff;
			white[4 * i + 2] = (byte)0xff;
			white[4 * i + 1] = (byte)0xff;
			white[4 * i] = (byte)0xff;
		}
		return white;
	}
}
