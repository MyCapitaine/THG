package org.thg.logic.story.driver.scene;

import org.thg.logic.story.api.GArticleScene;
import org.thg.logic.story.api.GDialog;

public class DefaultArticleScene extends DefaultScene implements GArticleScene {

	@Override
	public GDialog[] getDialogs() {
		return dialogs;
	}


}
