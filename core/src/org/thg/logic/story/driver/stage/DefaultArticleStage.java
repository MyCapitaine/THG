package org.thg.logic.story.driver.stage;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GArticleScene;
import org.thg.logic.story.api.GArticleStage;
import org.thg.logic.story.api.GDialog;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.ui.gamestage.GGameButtons;
import org.thg.ui.gamestage.GWordsWindow;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DefaultArticleStage extends Stage implements GArticleStage {
	
	private Image bg;
	private GWordsWindow wordsWindow;
	private GGameButtons gameButtons;
	
	public DefaultArticleStage() {
		super();
		
		iniWeight();
	}
	
	@Override
	public boolean isRunning() {
		return wordsWindow != null && wordsWindow.isRunning();
	}
	
	@Override
	public void setScene(GArticleScene aScene) {
		wordsWindow.setDialogs(aScene.getDialogs());
	}
	
	
	@Override
	public void setDialog(GDialog dialog) {
		wordsWindow.setDialog(dialog);
	}
	
	@Override
	public void setBg(int bgNum) {
		bg.setDrawable(new TextureRegionDrawable(new TextureRegion(ResourceFactory.getBg(bgNum))));
	}
	
	@Override
	public void setBgm(int bgmNum) {
		DefaultGameController.setBgm(bgmNum);
	}
	
	private void iniWeight() {
//====Background=================================================================================
		bg = new Image();
		bg.setSize(org.thg.ui.Config.SCREEN_WIDTH * org.thg.ui.Config.scaleX,
				org.thg.ui.Config.SCREEN_HEIGHT * org.thg.ui.Config.scaleY);
		addActor(bg);
		
//====GWordsWindow=================================================================================
		wordsWindow = new GWordsWindow();
		addActor(wordsWindow);
//====GGameButtons=================================================================================
//		gameButtons = new GGameButtons(this); 
//		暂时不在此处设立按钮
		gameButtons = null;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if(wordsWindow != null) wordsWindow.dispose();
		if(gameButtons != null) gameButtons.dispose();
		
	}

}
