package org.thg.logic.story.driver.stage.effectFunc;

public class Effect_misty_func {
	
	
	
	public static byte[] effect_misty(final byte[] sceneData, final int width, final int height,
			final int depth, final float timeCount, final float limitTimeCount) {
		byte[] newData = new byte[sceneData.length];
		for(int i = 1; i < width - 1; i ++) {
			for(int j = 1; j < height - 1; j ++) {
				int p = (j * width + i) * depth;
				for(int m = 0; m < depth; m ++) {
					int n = p + m;
					newData[n] = (byte)((sceneData[n + 1] + sceneData[n - 1] + sceneData[n + width] + sceneData[n - width]
							+ sceneData[n + width + 1] + sceneData[n + width - 1] + sceneData[n - width + 1] + sceneData[n - width - 1]) / 8);
				}
				
				
				
				
			}
		}
//		for(int i = 0; i < width)
		
		
		return newData;
	}
	
	
	
	
	
}
