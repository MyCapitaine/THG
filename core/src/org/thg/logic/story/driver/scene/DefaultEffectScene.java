package org.thg.logic.story.driver.scene;

import org.thg.logic.story.api.GEffectScene;

public class DefaultEffectScene extends DefaultScene implements GEffectScene {
	private int effectNum;
	private String[] params;
	public DefaultEffectScene() {
		super();
		effectNum = -1;
		params = null;
	}
	
	@Override
	public void setEffectNum(int en) {
		effectNum = en;
	}

	@Override
	public int getEffectNum() {
		return effectNum;
	}

	@Override
	public void setParams(String... o) {
		params = o;
	}

	@Override
	public String[] getParams() {
		if(params == null) return params = new String[0];
		return params;
	}

}
