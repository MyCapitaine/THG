package org.thg.logic.story.driver.stage.effectFunc;
/**
 * <p>切换场景的函数支持
 * <p>提供多种切换方式
 * <p>1.像拉窗帘一样(竖)
 * <p>2.～(横)
 * <p>3.渐变
 * <p>4.
 * <p>
 * <p>(翻页式)
 * <p>(侵蚀感)
 * 
 * 
 * @author MyCapitaine
 * 
 */
public class Effect_change_func {
	/**
	 * <p>模式1的切换场景的函数支持
	 * <p>自右上向左下，平滑的覆盖与切换
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
		float rate = timeCount / limitTimeCount;
		int pointNum = width * height;
		
		//TODO  遍历了数组 可以优化
		
		if(rate > 0.5f) {
			int q = (int)(2 * (1 - rate) * width), p = (int)(2 * (1 - rate) * height);
			int qp = q * p;
			for(int i = 0; i < pointNum; i ++) {
				int buffer = i / width;
				int y = height - buffer;
				int x = i - buffer * width;
				if(!(y <= p && p * x + q * y <= qp)) {
					int tail = i * depth;
					for(int j = 0; j < depth; j ++) {
						newData[tail + j] = sceneData[tail + j];
					}
				}
				
			}
		}
		else {
			int q = (int)(2 * rate * width), p = (int)(2 * rate * height);
			int qp = q * p;
			for(int i = 0; i < pointNum; i ++) {
				int y = i / width;
				int x = (y + 1) * width - i;
				if(y <= p && p * x + q * y <= qp) {
					int tail = i * depth;
					for(int j = 0; j < depth; j ++) {
						newData[tail + j] = sceneData[tail + j];
					}
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
	 */
	public static byte[] effect_change_2(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		byte[] newData = new byte[sceneData.length];
		
		double rate = (double)timeCount / (double)limitTimeCount;
		if(rate > 0.5f) 
			rate = Math.cos(Math.PI * (1 - rate));
		else
			rate = Math.cos(Math.PI * rate);
		
		int part_width = width / COL_PART_NUM;
		
		
		
		
		return newData;
	}
	/**
	 * <p>模式3的切换场景的函数支持
	 * <p>
	 * <p>参数和返回值同1
	 */
	public static byte[] effect_change_3(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		byte[] newData = new byte[sceneData.length];
		
		
		
		
		return newData;
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
