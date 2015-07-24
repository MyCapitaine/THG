package org.thg.ui.sl;
import org.thg.logic.THG;
import org.thg.logic.story.api.ProgressData;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.logic.story.driver.progressdata.ProgressDataUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
/**
 * 读档界面
 * @author MyCapitaine
 *
 */
public class GLoadMenu extends ScreenAdapter {
	private Stage stage;
	private OtherWeight otherWeight;
	private GSLWeight gslWeight;
	private Screen returnScreen;
	
	public GLoadMenu(Screen returnScreen) {
		stage = new Stage();
		this.returnScreen = returnScreen;
		otherWeight = new OtherWeight(this, this.returnScreen);
		stage.addActor(otherWeight);
		
		gslWeight = new GSLWeight(new LoadSpeaker());
		stage.addActor(gslWeight);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		stage.act();
		stage.draw();
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void dispose() {
		otherWeight.dispose();
		gslWeight.dispose();
	}
	
	
	
	
	/** 当界面为读档界面时，档区的监听 */
	private class LoadSpeaker implements SLSpeaker {
		@Override
		public void setListener(DataPic dataPic, int orderNum, boolean haveData) {
			dataPic.clearInputListeners();
			if(!haveData) return;
			
			final int num = orderNum;
			dataPic.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					ProgressData pd = ProgressDataUtil.load(num);
					THG.getGame().getScreen().dispose();
					if(returnScreen != null) returnScreen.dispose();
					THG.getGame().setScreen(new DefaultGameController(pd));
					
					return true;
				}
				
			});
		}
	}

}
