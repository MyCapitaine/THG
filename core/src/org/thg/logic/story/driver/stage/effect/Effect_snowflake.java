package org.thg.logic.story.driver.stage.effect;

import java.nio.ByteBuffer;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_snowflake_func;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 雪花，意识间歇ing */
public class Effect_snowflake extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	
	private final Pixmap changablePixmap;
	private final ByteBuffer pixmapByte;
	private byte[] byteData;
	private Texture changableTexture;
	private final Image flakeImage;
	private final int width, height;
	
	private float renderCount;
	
	private static final float LIMIT_RENDER_COUNT = 8f;
	private static final float EFFECT_SPEED_NORMAL = 1f,
			EFFECT_SPEED_SKIP = 10f;
	
	public Effect_snowflake(DefaultEffectStage  stage, String... params) {
		defaultEffectStage = stage;
		defaultEffectStage.bg.remove();
		
		flakeImage = new Image();
		flakeImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(flakeImage);
		
		width = defaultEffectStage.bgTexture.getWidth();
		height = defaultEffectStage.bgTexture.getHeight();
		
		TextureData td = defaultEffectStage.bgTexture.getTextureData();
		td.prepare();
		changablePixmap = td.consumePixmap();
		pixmapByte = changablePixmap.getPixels();
		byteData = new byte[pixmapByte.capacity()];
		pixmapByte.get(byteData);
		pixmapByte.clear();
		
		changableTexture = null;
		
		renderCount = 0;
		
		
		
	}
	@Override
	public boolean act(float delta) {

		Screen s = THG.getGame().getScreen();
		if(s instanceof GGameController && ((GGameController)s).getSkipFlag())
			renderCount += EFFECT_SPEED_SKIP;
		else renderCount += EFFECT_SPEED_NORMAL;
		if(renderCount > LIMIT_RENDER_COUNT) renderCount = LIMIT_RENDER_COUNT;
		
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			ResourceFactory.putBgBufferTexture(changableTexture);
			changablePixmap.dispose();
			//雪花一闪而过，强行跳过该场景
			((GGameController)s).control(false);
			return true;
		}
		
		byte[] newData = Effect_snowflake_func.effect_snowflake(byteData, width, height, renderCount, LIMIT_RENDER_COUNT);
		pixmapByte.put(newData);
		pixmapByte.clear();
		
		if(changableTexture != null) changableTexture.dispose();
		changableTexture = new Texture(changablePixmap);
		
		flakeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
		
		
		return false;
	}
}	