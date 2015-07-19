package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_shake_func;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/** 晃动  */
public class Effect_shake extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	private Image shakeImage;
	
	public Effect_shake(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		shakeImage = new Image(defaultEffectStage.bg.getDrawable());
		shakeImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(shakeImage);
		Effect_shake_func.setSeed(System.currentTimeMillis());
		
		resetCount(1f, 8f, 20f);
	}
	@Override
	public boolean act(float delta) {
		if(renderCount >= LIMIT_RENDER_COUNT) {
			shakeImage.remove();
			isRunning = false;
			return true;
		}
		count();
		
		Effect_shake_func.effect_misty(shakeImage, renderCount, LIMIT_RENDER_COUNT, 0, 0);
		
		return false;
	}
}