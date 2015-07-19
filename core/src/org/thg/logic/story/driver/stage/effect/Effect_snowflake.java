package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_snowflake_func;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 雪花，意识间歇ing */
public class Effect_snowflake extends EffectAction {
	private DefaultEffectStage defaultEffectStage;

	private final Image flakeImage;
	
	public Effect_snowflake(DefaultEffectStage  stage, String... params) {
		defaultEffectStage = stage;
		defaultEffectStage.bg.remove();
		
		flakeImage = new Image();
		flakeImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(flakeImage);
		
		resetCount(1f, 8f, 10f);
		iniBytesAndSoOn(defaultEffectStage.bgTexture);
		
		
	}
	@Override
	public boolean act(float delta) {

		GGameController g = count();
		
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			ResourceFactory.putBgBufferTexture(changableTexture);
			changablePixmap.dispose();
			//雪花一闪而过，强行跳过该场景
			g.control(false);
			return true;
		}
		
		byte[] newData = Effect_snowflake_func.effect_snowflake(byteData, width, height);
		pixmapByte.put(newData);
		pixmapByte.clear();
		
		if(changableTexture != null) changableTexture.dispose();
		changableTexture = new Texture(changablePixmap);
		
		flakeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
		
		
		return false;
	}
}	