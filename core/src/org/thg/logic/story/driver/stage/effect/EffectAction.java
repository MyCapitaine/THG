package org.thg.logic.story.driver.stage.effect;

import java.nio.ByteBuffer;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.scenes.scene2d.Action;
/**
 * effect效果的基类，提供运行标示符
 * @author MyCapitaine
 *	
 */
public abstract class EffectAction extends Action {
	protected Pixmap changablePixmap;
	protected ByteBuffer pixmapByte;
	protected byte[] byteData;
	protected Texture changableTexture;
	protected int width, height, depth;
	
	protected float renderCount;
	
	protected void iniBytesAndSoOn(Texture texture) {
		width = texture.getWidth();
		height = texture.getHeight();
		depth = texture.getDepth() == 0 ? 4 : texture.getDepth();
		
		TextureData td = texture.getTextureData();
		td.prepare();
		changablePixmap = td.consumePixmap();
		pixmapByte = changablePixmap.getPixels();
		byteData = new byte[pixmapByte.capacity()];
		pixmapByte.get(byteData);
		pixmapByte.clear();
		
		changableTexture = null;
		
		renderCount = 0f;
	}
	
	
	
	
	//act return true时将此标识置于否
	protected boolean isRunning = true;
	public boolean isRunning() { return isRunning; }
	
	
	
	
	
}
