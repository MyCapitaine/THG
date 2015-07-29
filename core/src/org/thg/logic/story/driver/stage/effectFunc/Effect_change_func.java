package org.thg.logic.story.driver.stage.effectFunc;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * <p>切换场景的函数支持
 * <p>提供多种切换方式
 * <p>1.百叶窗效果(竖)
 * <p>2.～(横)
 * <p>3.渐变
 * <p>4.
 * <p>
 * <p>
 * <p>(翻页式)
 * <p>(侵蚀感)
 * 
 * 
 * @author MyCapitaine
 * 
 */
public class Effect_change_func {
	
	private static final int ROW_PART_NUM = 15;
	/**
	 * <p>模式1的切换场景的函数支持
	 * <p>竖向的百叶窗效果
	 * TODO 暂时没有考虑长度不整除条数的情况
	 * @param sceneData 变换前的byte数组
	 * @param width 图片宽
	 * @param height 图片高
	 * @param depth 位深
	 * @param timeCount 当前计数器
	 * @param limitTimeCount 计数器满值
	 * @return 变换后的byte数组
	 */
	public static byte[] effect_change_1(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		byte[] newData = new byte[sceneData.length];
		if(depth == 4)
			for(int i = 0; i < width * height; i ++)
				newData[4 * i + 3] = (byte)255;
		/** 比例 */
		double rate = (double)timeCount / (double)limitTimeCount;
		if(rate > 0.5f) 
			rate = Math.cos(Math.PI * (1 - rate));
		else
			rate = Math.cos(Math.PI * rate);
		int part_height = height / ROW_PART_NUM;
		
		for(int i = 0; i < ROW_PART_NUM; i ++) {
			for(int j = 0; j < part_height; j ++) {
				for(int k = 0; k < width; k ++) {
					int n = ((i * part_height + j) * width + k) * 4;
					int m = ((i * part_height + (int)((double)j * rate)) * width + k) * 4;		
					newData[m] = sceneData[n];
					for(int d = 1; d < depth; d ++) 
						newData[++m] = sceneData[++n];
				}
			}
		}
		
		
		return newData;
	}
	
	
	
	private static final int COL_PART_NUM = 20;
	/**
	 * <p>模式2的切换场景的函数支持
	 * <p>横向的百叶窗效果
	 * <p><b><i>参数和返回值同1
	 * TODO 暂时没有考虑长度不整除条数的情况
	 */
	public static byte[] effect_change_2(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		byte[] newData = new byte[sceneData.length];
		if(depth == 4)
			for(int i = 0; i < width * height; i ++)
				newData[4 * i + 3] = (byte)255;
		
		/** 比例 */
		double rate = (double)timeCount / (double)limitTimeCount;
		if(rate > 0.5f) 
			rate = Math.cos(Math.PI * (1 - rate));
		else
			rate = Math.cos(Math.PI * rate);
		
		int part_width = width / COL_PART_NUM;
		
		for(int i = 0; i < COL_PART_NUM; i ++) {
			for(int j = 0; j < part_width; j ++) {
				for(int k = 0; k < height; k ++) {
					int n = (k * width + i * part_width + j) * 4;
					int m = (k * width + i * part_width + (int)((double)j * rate)) * 4;
					newData[m] = sceneData[n];
					for(int d = 1; d < depth; d ++) 
						newData[++m] = sceneData[++n];
				}
			}
		}
		
		
		
		return newData;
	}
	/**
	 * <p>模式3的切换场景的函数支持
	 * <p>渐变的效果
	 * @param beforeBg 变前图片
	 * @param afterBg 变后图片
	 */
	public static void effect_change_3(final Actor beforeBg, final Actor afterBg,
			final float timeCount, final float limitTimeCount) {
		double rate = (double)timeCount / (double)limitTimeCount * Math.PI / 2;
//		afterBg.getColor().a = (float)Math.sin(rate);
//		beforeBg.getColor().a = 1 - afterBg.getColor().a;
//		采用余弦函数开头增长缓慢，后面增长比较迅速
		beforeBg.getColor().a = (float)Math.cos(rate);
		afterBg.getColor().a = 1 - beforeBg.getColor().a;
	}
	/**
	 * <p>模式4的切换场景的函数支持
	 * <p>
	 * <p>参数和返回值同1
	 */
	public static byte[] effect_change_4(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		byte[] newData = new byte[sceneData.length];
		
		
		
		
		return newData;
	}
	/**
	 * <p>模式5的切换场景的函数支持
	 * <p>
	 * <p>参数和返回值同1
	 */
	public static byte[] effect_change_5(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		byte[] newData = new byte[sceneData.length];
		
		
		
		
		return newData;
	}
}
