package org.thg.imageUtil;

import java.awt.image.Kernel;

import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.PixelUtils;

/**
 * 高斯模糊的扩展
 * 
 * <p>{@link GaussianFilter}
 */
public class GaussianFilterExtend extends GaussianFilter {
	/**
	 * 
	 * <p>{@link GaussianFilter#filter(java.awt.image.BufferedImage, java.awt.image.BufferedImage)}
	 */
	public static byte[] filter(byte[] bytes, float radius, int width, int height) {
		byte[] buffer = new byte[bytes.length];
		byte[] newData = new byte[bytes.length];
		Kernel kernel = makeKernel(radius);
		if(radius > 0) {
			convolveAndTranspose(kernel, bytes, buffer, width, height, false, false, false, CLAMP_EDGES);
			convolveAndTranspose(kernel, buffer, newData, height, width, false, false, false, CLAMP_EDGES);
		}
		
		return newData;
	}
	
	
	
	
	
	/**<p>一个高斯模糊在数组级上转换的重载版本 
	 * <p>适应byte[]类型,RGBA
	 * <p>见:
	 * <p>{@link GaussianFilter#convolveAndTranspose(Kernel, int[], int[], int, int, boolean, boolean, boolean, int)}*/
	public static void convolveAndTranspose(Kernel kernel, byte[] inBytes, byte[] outBytes, int width, int height, boolean alpha, boolean premultiply, boolean unpremultiply, int edgeAction) {
		float[] matrix = kernel.getKernelData( null );
		int cols = kernel.getWidth();
		int cols2 = cols/2;

		for (int y = 0; y < height; y++) {
			int index = y;
			int ioffset = y*width;
			for (int x = 0; x < width; x++) {
				float r = 0, g = 0, b = 0, a = 0;
				int moffset = cols2;
				for (int col = -cols2; col <= cols2; col++) {
					float f = matrix[moffset+col];

					if (f != 0) {
						int ix = x+col;
						if ( ix < 0 ) {
							if ( edgeAction == CLAMP_EDGES )
								ix = 0;
							else if ( edgeAction == WRAP_EDGES )
								ix = (x+width) % width;
						} else if ( ix >= width) {
							if ( edgeAction == CLAMP_EDGES )
								ix = width-1;
							else if ( edgeAction == WRAP_EDGES )
								ix = (x+width) % width;
						}
//						int rgb = inPixels[ioffset+ix];
//						int pa = (rgb >> 24) & 0xff;
//						int pr = (rgb >> 16) & 0xff;
//						int pg = (rgb >> 8) & 0xff;
//						int pb = rgb & 0xff;
						int h = (ioffset + ix) * 4;
						int pa = inBytes[h + 3] & 0xff;
						int pr = inBytes[h] & 0xff;
						int pg = inBytes[h + 1] & 0xff;
						int pb = inBytes[h + 2] & 0xff;
//						
//						
						
						if ( premultiply ) {
							float a255 = pa * (1.0f / 255.0f);
							pr *= a255;
							pg *= a255;
							pb *= a255;
						}
						a += f * pa;
						r += f * pr;
						g += f * pg;
						b += f * pb;
					}
				}
				if ( unpremultiply && a != 0 && a != 255 ) {
					float f = 255.0f / a;
					r *= f;
					g *= f;
					b *= f;
				}
				int ia = alpha ? PixelUtils.clamp((int)(a+0.5)) : 0xff;
				int ir = PixelUtils.clamp((int)(r+0.5));
				int ig = PixelUtils.clamp((int)(g+0.5));
				int ib = PixelUtils.clamp((int)(b+0.5));
//				outPixels[index] = (ia << 24) | (ir << 16) | (ig << 8) | ib;
				outBytes[4 * index + 3] = (byte)ia;
				outBytes[4 * index] = (byte)ir;
				outBytes[4 * index + 1] = (byte)ig;
				outBytes[4 * index + 2] = (byte)ib;
//
//				
				
                index += height;
			}
		}
	}
}
