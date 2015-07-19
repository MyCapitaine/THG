package org.thg.imageUtil;

import java.util.Random;

import com.jhlabs.image.DissolveFilter;
import com.jhlabs.image.ImageMath;

/**
 * 溶解滤镜的扩展
 * {@link DissolveFilter}
 */
public class DissolveFilterExtend extends DissolveFilter {
	public float density = 1;
	public float softness = 0;
	private float minDensity, maxDensity;
	public Random randomNumbers;
	
	public int partice_size;
	
	public byte[] filter(byte[] inPixels, int width, int height, long seed ) {
		float d = (1-density) * (1+softness);
		minDensity = d-softness;
		maxDensity = d;
		randomNumbers = new Random( seed );
		
		
		byte[] outPixels = inPixels.clone();
		for (int y = 0; y < height; y ++) {
			for (int x = 0; x < width; x ++) {
				int aIndex = 4 * (y * width + x) + 3;
				outPixels[aIndex] = filterRGB(x, y, inPixels[aIndex]);
			}
		}
		return outPixels;
		
		
	}
	
	/**
	 * 
	 * {@link DissolveFilter#filterRGB(int, int, int)}
	 */
	public byte filterRGB(int x, int y, byte a) {
		float v = randomNumbers.nextFloat();
		float f = ImageMath.smoothStep( minDensity, maxDensity, v );
		int a_int = a & 0xff;
		return (byte)((int)(a_int * f));
		
	}
}
