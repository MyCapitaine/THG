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
	 * 通过高斯模糊算法，得到模糊后的byte[]
	 * @param sceneData 原数据
	 * @param width 宽
	 * @param height 高
	 * @param timeCount 见下
	 * @param limitTimeCount 这两项数据用于计算当前的进度
	 * @return 模糊后的数据
	 */
	public static byte[] effect_misty(final byte[] sceneData, final int width, final int height,
			final float timeCount, final float limitTimeCount) {
		double rate = (double)(timeCount / limitTimeCount) * Math.PI / 2;
		
		float radius =  (float)Math.sin(rate)* MAX_RADIUS;
		byte[] newData = GaussianFilterExtend.filter(sceneData, radius, width, height);
		return newData;
	}
	
	/**
	 * 将图片数据缩放
	 * @param sceneData 图片原数据
	 * @param width 图片原宽
	 * @param height 图片原高
	 * @param reduce 缩放系数(宽高均缩放这么多倍)
	 * @return 缩放后的byte[]
	 */
	public static byte[] reduce(byte[] sceneData, int width, int height, int reduce) {
		int newWidth = width / reduce;
		int newHeight = height / reduce;
		byte[] newData = new byte[newWidth * newHeight * 4];
		for(int i = 0; i < newWidth; i ++) {
			for(int j = 0; j < newHeight; j ++) {
				int num1 = (j * width + i) * reduce * 4;
				int num2 = (j * newWidth + i) * 4;
				
				newData[num2] = sceneData[num1];
				newData[num2 + 1] = sceneData[num1 + 1];
				newData[num2 + 2] = sceneData[num1 + 2];
				newData[num2 + 3] = sceneData[num1 + 3];
				
				
			}
		}
		
		return newData;
		
	}
}
