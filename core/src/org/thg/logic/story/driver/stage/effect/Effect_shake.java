package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.THG;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_shake_func;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/** 晃动  */
public class Effect_shake extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	private Image shakeImage;
	
	private static final float 
		EFFECT_SHAKE_SPEED_NORMAL = 1f,
		EFFECT_SHAKE_SPEED_SKIP = 8f;	
	private static final float EFFECT_SHAKE_TIME_LIMIT_COUNT = 20f;
	
	
	private float renderCount = 0;
	public Effect_shake(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		shakeImage = new Image(defaultEffectStage.bg.getDrawable());
		shakeImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(shakeImage);
		Effect_shake_func.setSeed(System.currentTimeMillis());
	}
	@Override
	public boolean act(float delta) {
		if(renderCount >= EFFECT_SHAKE_TIME_LIMIT_COUNT) {
			shakeImage.remove();
			isRunning = false;
			return true;
		}
		Screen s = THG.getGame().getScreen();
		if(s instanceof GGameController && ((GGameController)s).getSkipFlag())
			renderCount += EFFECT_SHAKE_SPEED_SKIP;
		else renderCount += EFFECT_SHAKE_SPEED_NORMAL;
		
		Effect_shake_func.effect_misty(shakeImage, 
				renderCount > EFFECT_SHAKE_TIME_LIMIT_COUNT ? EFFECT_SHAKE_TIME_LIMIT_COUNT : renderCount,
				EFFECT_SHAKE_TIME_LIMIT_COUNT, 0, 0);
		
		return false;
	}
}