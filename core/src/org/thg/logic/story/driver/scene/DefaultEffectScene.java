package org.thg.logic.story.driver.scene;

import org.thg.logic.story.api.GEffectScene;

public class DefaultEffectScene extends DefaultScene implements GEffectScene {
	private int effectNum;
	public DefaultEffectScene() {
		super();
		effectNum = -1;
	}
	
	@Override
	public final void setEffectNum(int en) {
		effectNum = en;
	}

	@Override
	public final int getEffectNum() {
		return effectNum;
	}

}
