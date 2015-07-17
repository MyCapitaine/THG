package org.thg.logic.story.driver.stage.effectFunc;

public class Effect_change {
	private static final int DEFAULT_DEPTH = 4;
	/**
	 * 模式1的切换场景的函数支持
	 * @param sceneData 变换前的byte数组
	 * @param width 图片宽
	 * @param height 图片高
	 * @param depth 位深
	 * @param timeCount 当前计数器
	 * @param limitTimeCount 计数器满值
	 * @return 变换后的byte数组
	 */
	public static byte[] effect_change_1(final byte[] sceneData, final int width, final int height,
			int depth, final float timeCount, final float limitTimeCount) {
		depth = depth == 0 ? 4 : DEFAULT_DEPTH;
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
	
	
	
	
}
