package org.thg.ui.sl;

import org.thg.logic.factorys.ScreenshotFactory;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.progressdata.ProgressDataUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		otherWeight.dispose();
		gslWeight.dispose();
	}
	
	
	/** 当界面为存档界面时，档区的监听 */
	private class SaveSpeaker implements SLSpeaker {
		
		@Override
		public void setListener(final DataPic dataPic, final int orderNum, final boolean haveData) {
			dataPic.clearInputListeners();
			
			dataPic.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					
					if(haveData) {
//						确认是否覆盖
					}
					
					if(!(returnScreen instanceof GGameController)) return false;
					((GGameController)returnScreen).save(ScreenshotFactory.getBufferScreenshot(),
							orderNum);
					//save结束后  texture更新
					
					dataPic.setDrawable(new TextureRegionDrawable(new TextureRegion(
							ProgressDataUtil.getImageTexture(orderNum))));
					
					return true;
				}
			});
		}
		
	}
}