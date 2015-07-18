package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;


/** 放大，拉进和某物的距离 */
public class Effect_amplify extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	/**
	 * @param params 参数说明
	 * <p> 放大方位 1-9
	 * <p> 虚实 0,1 默认为实（1）
	 * 
	 */
	public Effect_amplify(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!isRunning) return true;
		
		
		return false;
	}
}