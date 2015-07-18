package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;

//可参看随机溶解， DissolvFilter
/** 雪花，意识间歇ing，可恢复 */
public class Effect_snowflake extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	/**
	 * @param params 参数说明
	 * <p>
	 * 
	 */
	public Effect_snowflake(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!isRunning) return true;
		
		
		return false;
	}
}	