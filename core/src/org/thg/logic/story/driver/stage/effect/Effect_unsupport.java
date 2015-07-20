package org.thg.logic.story.driver.stage.effect;

/** 不支持的效果 */
public class Effect_unsupport extends EffectAction {
	@Override
	public boolean act(float delta) { return true; }
	@Override
	public boolean isRunning(boolean byHand) { return false; }
}
