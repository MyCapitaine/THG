package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;
/**
 * 时间可定的空档
 * @author MyCapitaine
 * 
 */
public class Effect_blank extends EffectAction {
	
	public Effect_blank(DefaultEffectStage stage, String... params) {
		resetCount(1f, 20f, 0f);
		try {
			LIMIT_RENDER_COUNT = 60f * (float)Double.parseDouble(params[0]);
		}catch(Exception e) {
			LIMIT_RENDER_COUNT = 60f;
		}
		
		
	}
	@Override
	public boolean act(float delta) {
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			return true;
		}
		count();
		
		return false;
	}
	
	@Override
	public boolean isRunning(boolean byHand) {
		if(byHand) {
			if(renderCount >= LIMIT_RENDER_COUNT) return false;
			renderCount = LIMIT_RENDER_COUNT;
			return true;
		}
		
		return super.isRunning(byHand);
	}
}
