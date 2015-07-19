package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_blind_func;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 狗眼闪瞎，中了闪光弹，可恢复 */
public class Effect_blind extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	
	private Image blindImage;
	private static final float EFFECT_SPEED_NORMAL = 1f, EFFECT_SPEED_SKIP = 20f,
			LIMIT_RENDER_COUNT = 40f;
	
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
		
		iniBytesAndSoOn(defaultEffectStage.bgTexture);
		
		
		
		
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
//			if(back) {
//				defaultEffectStage.addActor(defaultEffectStage.bg);
//				blindImage.remove();
//			}
			ResourceFactory.putBgBufferTexture(changableTexture);
			changablePixmap.dispose();
			return true;
		}
		
		
		
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