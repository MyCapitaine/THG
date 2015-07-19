package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_blind_func;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 狗眼闪瞎，中了闪光弹，可恢复 */
public class Effect_blind extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	
	private Image blindImage;
	
	private boolean back;
	
	/**
	 * @param params
	 * 有参即表明从闪光恢复
	 */
	public Effect_blind(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		defaultEffectStage.bg.remove();
		
		if(params == null || params.length == 0) back = false;
		else back = true;
		
		blindImage = new Image();
		blindImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(blindImage);
		
		resetCount(1f, 20f, 40f);
		iniBytesAndSoOn(defaultEffectStage.bgTexture);
		
		
		
		
	}
	@Override
	public boolean act(float delta) {
		
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			if(back) {
				defaultEffectStage.addActor(defaultEffectStage.bg);
				blindImage.remove();
				if(changableTexture != null) changableTexture.dispose();
			}
			else ResourceFactory.putBgBufferTexture(changableTexture);
			changablePixmap.dispose();
			return true;
		}

		count();
		
		
		byte[] newData = Effect_blind_func.effect_blind(byteData, width, height,
				back ? LIMIT_RENDER_COUNT - renderCount : renderCount, LIMIT_RENDER_COUNT);
		pixmapByte.put(newData);
		pixmapByte.clear();
		
		if(changableTexture != null) changableTexture.dispose();
		changableTexture = new Texture(changablePixmap);
		
		blindImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
		
		return false;
	}
}