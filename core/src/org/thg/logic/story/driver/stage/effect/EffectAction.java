package org.thg.logic.story.driver.stage.effect;

import com.badlogic.gdx.scenes.scene2d.Action;
/**
 * effect效果的基类，提供运行标示符
 * @author MyCapitaine
 *	
 */
public abstract class EffectAction extends Action {
	//act return true时将此标识置于否
	protected boolean isRunning = true;
	public boolean isRunning() { return isRunning; }
}
