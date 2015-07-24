package org.thg.ui.sl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * 存档界面
 * @author MyCapitaine
 *
 */
public class GSaveMenu extends ScreenAdapter {
	private Stage stage;
	private OtherWeight otherWeight;
	private GSLWeight gslWeight;
	private Screen returnScreen;
	
	public GSaveMenu(Screen returnScreen) {
		stage = new Stage();
		this.returnScreen = returnScreen;
		otherWeight = new OtherWeight(this, this.returnScreen);
		stage.addActor(otherWeight);
		
		gslWeight = new GSLWeight(new SaveSpeaker());
		stage.addActor(gslWeight);
	}
	
	@Override
	public void show() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		stage.act();
		stage.draw();
	}

	@Override
	public void render(float delta) {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		otherWeight.dispose();
		gslWeight.dispose();
	}
	
	
	/** 当界面为存档界面时，档区的监听 */
	private class SaveSpeaker implements SLSpeaker {
		
		@Override
		public void setListener(DataPic dataPic, int orderNum, boolean haveData) {
			dataPic.clearInputListeners();
			if(!haveData) return;
			
			final DataPic dp = dataPic;
			final int num = orderNum;
			dp.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
//					TODO 图片哪来
					
					
					
					return true;
				}
			});
		}
		
	}
}