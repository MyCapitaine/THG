package org.thg.imageUtil;

import com.jhlabs.image.ExposureFilter;
/**
 * 曝光滤镜的扩展
 * <p>{@link ExposureFilter}
 */
public class ExposureFilterExtend extends ExposureFilter  {
	
	/**
	 * 
	 * {@link ExposureFilter#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)}
	 */
	public byte[] filter(byte[] inPixels, int width, int height) {
		if (!initialized)
			initialize();
		
		byte[] outPixels = inPixels.clone();
		for (int y = 0; y < height; y ++) {
			for (int x = 0; x < width; x ++) {
				int aIndex = 4 * (y * width + x);
				outPixels[aIndex] = (byte)rTable[inPixels[aIndex] & 0xff];
				outPixels[aIndex + 1] = (byte)rTable[inPixels[aIndex + 1] & 0xff];
				outPixels[aIndex + 2] = (byte)rTable[inPixels[aIndex + 2] & 0xff];
			}
		}
		return outPixels;
		
		
	}
//	public int filterRGB(int x, int y, int rgb) {
//		int a = rgb & 0xff000000;
//		int r = (rgb >> 16) & 0xff;
//		int g = (rgb >> 8) & 0xff;
//		int b = rgb & 0xff;
//		r = rTable[r];
//		g = gTable[g];
//		b = bTable[b];
//		return a | (r << 16) | (g << 8) | b;
//	}
}
