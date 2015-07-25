package org.thg.logic.factorys;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenshotFactory {
//	 private static int counter = 1;
//	 public static void saveScreenshot(){
//		 try{
//			 FileHandle fh;
//			 do{
//				 fh = new FileHandle("screenshot" + counter++ + ".png");
//	         }while (fh.exists());
//	         Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
//	         PixmapIO.writePNG(fh, pixmap);
//	         pixmap.dispose();
//		 }catch (Exception e){           
//	     }
//	 }
	
//	public static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown){
//        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);
//        
//        if (yDown) {
//            // Flip the pixmap upside down
//            ByteBuffer pixels = pixmap.getPixels();
//            int numBytes = w * h * 4;
//            byte[] lines = new byte[numBytes];
//            int numBytesPerLine = w * 4;
//            for (int i = 0; i < h; i++) {
//                pixels.position((h - i - 1) * numBytesPerLine);
//                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
//            }
//            pixels.clear();
//            pixels.put(lines);
//        }
//
//        return pixmap;
//    }
	
	/** 
	 * 将截屏缩小并返回
	 * @param width 缩小后宽
	 * @param height 缩小后高
	 */
	public static Pixmap getScreenshot(int width, int height) {
		int oWidth = Gdx.graphics.getWidth(),
			oHeight = Gdx.graphics.getHeight();
		float rateHeight = (float)oHeight / (float)height,
			  rateWidth = (float)oWidth / (float)width;
		byte[] oBytes = ScreenUtils.getFrameBufferPixels(false);
		byte[] nBytes = new byte[width * height * 4];
		
		for(int i = 0; i < width; i ++) {
			for(int j = 0; j < height; j ++) {
				int m = (width * (height - j) - i - 1) * 4;
				int n = ((int)(j * rateHeight  * oWidth) + (int)(i * rateWidth)) * 4;
				nBytes[m] = oBytes[n];
				nBytes[++m] = oBytes[++n];
				nBytes[++m] = oBytes[++n];
				nBytes[++m] = oBytes[++n];
			}
		}
		
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		ByteBuffer b = pixmap.getPixels();
		b.put(nBytes);
		b.clear();
		
		return pixmap;
	}
	
	/** 公立缓存区 
	 * {@link ScreenshotFactory#getBufferScreenshot()}*/
	public static void saveBufferScreenshot(int width, int height) {
		try {
			bufferScreenshot.dispose();
		}catch(Exception e) {}
		bufferScreenshot = getScreenshot(width, height);
	}
	/** {@link ScreenshotFactory#saveBufferScreenshot(int, int)}*/
	public static Pixmap getBufferScreenshot() {
		return bufferScreenshot;
	}
	private static Pixmap bufferScreenshot = null;
}
