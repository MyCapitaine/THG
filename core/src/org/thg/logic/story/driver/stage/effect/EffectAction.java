package org.thg.logic.story.driver.stage.effect;

import java.nio.ByteBuffer;

import org.thg.logic.THG;
import org.thg.logic.story.api.GGameController;

import com.badlogic.gdx.Screen;
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
	protected float EFFECT_SPEED_NORMAL, EFFECT_SPEED_SKIP, LIMIT_RENDER_COUNT;
	
	protected void iniBytesAndSoOn(Texture texture) {
		width = texture.getWidth();
		height = texture.getHeight();
		depth = texture.getDepth() == 0 ? 4 : texture.getDepth();
		
		TextureData td = texture.getTextureData();
		if(!td.isPrepared()) td.prepare();
		changablePixmap = td.consumePixmap();
		pixmapByte = changablePixmap.getPixels();
		byteData = new byte[pixmapByte.capacity()];
		pixmapByte.get(byteData);
		pixmapByte.clear();
		
		changableTexture = null;
		
	}
	
	protected void resetCount(float speed_normal, float speed_skip, float limit_render_count) {
		EFFECT_SPEED_NORMAL = speed_normal;
		EFFECT_SPEED_SKIP = speed_skip;
		LIMIT_RENDER_COUNT = limit_render_count;
		renderCount = 0f;
	}
	
	
	protected GGameController count() {
		Screen s = THG.getGame().getScreen();
		if(s instanceof GGameController && ((GGameController)s).getSkipFlag())
			renderCount += EFFECT_SPEED_SKIP;
		else renderCount += EFFECT_SPEED_NORMAL;
		if(renderCount > LIMIT_RENDER_COUNT) renderCount = LIMIT_RENDER_COUNT;
		
		return (GGameController)s;
	}
	
	
	//act return true时将此标识置于否
	protected boolean isRunning = true;
	public boolean isRunning() { return isRunning; }
	
	
	
	
	
}
