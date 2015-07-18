package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;

/** 神志不清ing，醉了，视线模糊且晃晃悠悠 */
public class Effect_drunk extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	/**
	 * @param params 参数说明
	 * <p>
	 * 
	 */
	public Effect_drunk(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!isRunning) return true;
		
		
		return false;
	}
}
