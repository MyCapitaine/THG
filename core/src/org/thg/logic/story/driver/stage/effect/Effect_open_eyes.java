package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;

/** 睁眼，并带上眨眼 */
public class Effect_open_eyes extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	/**
	 * @param params 参数说明
	 * <p>
	 * 
	 */
	public Effect_open_eyes(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!isRunning) return true;
		
		
		return false;
	}
}
