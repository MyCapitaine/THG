package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;

/** 狗眼闪瞎，中了闪光弹，可恢复 */
public class Effect_blind extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	/**
	 * @param params 参数说明
	 * <p>
	 * 
	 */
	public Effect_blind(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!isRunning) return true;
		
		
		return false;
	}
}