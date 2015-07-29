package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.GPosition;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/** 聚焦，模糊周围 */
public class Effect_focus extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	
	private Image focusImage;
	
	private final boolean back;
	private GPosition position;
	/**
	 * @param params 参数说明
	 * <p> position 0-15/A0-D3  焦点区域 {@link GPosition}
	 * <p> back * 是否为返回，有参即为返回
	 */
	public Effect_focus(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		defaultEffectStage.bg.remove();
		
		try {
			position = GPosition.values()[Integer.parseInt(params[0])];
		}catch(Exception e) {
			try {
				position = GPosition.valueOf(params[0]);
			}catch(Exception e1) {
				position = position.A0;
			}
		}
		
		if(params.length >= 2) back = true;
		else back = false;
		
		
		
		
		
		
		
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!isRunning) return true;
		
		
		return false;
	}
}