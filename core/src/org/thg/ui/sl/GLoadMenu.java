package org.thg.ui.sl;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GLoadMenu extends ScreenAdapter {
	private Stage stage;
	private OtherWeight otherWeight;
	private GSLWeight gslWeight;
	
	public GLoadMenu(Screen returnScreen) {
		stage = new Stage();
		otherWeight = new OtherWeight(this, returnScreen);
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
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	public void dispose() {
		otherWeight.dispose();
		gslWeight.dispose();
	}
	
	
	
	
	
	class LoadSpeaker implements SLSpeaker {
		@Override
		public void setListener(DataPic dataPic, int OrderNum, boolean haveData) {
			dataPic.getListeners().clear();
			
			if(!haveData)
			
			dataPic.addListener(new InputListener() {
				
			});
			
			
			
		}
	}

}
