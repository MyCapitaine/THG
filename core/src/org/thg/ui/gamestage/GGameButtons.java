package org.thg.ui.gamestage;

import java.util.ArrayList;

import org.thg.logic.THG;
import org.thg.logic.factorys.ScreenshotFactory;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.stage.DefaultGameStage;
import org.thg.ui.Config;
import org.thg.ui.GMainMenu;
import org.thg.ui.UiUtil;
import org.thg.ui.sl.GLoadMenu;
import org.thg.ui.sl.GSaveMenu;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;

public class GGameButtons implements Disposable {
	private Stage stage;
	private ImageButton save, load, skip, auto, retu/*, backView*/;

	private TextureRegion[][] tr;
	private ArrayList<Disposable> disList;
	
	public GGameButtons(Stage s) {
		stage = s;
		disList = new ArrayList<Disposable>();
		iniSave();
		iniAuto();
		iniLoad();
		iniSkip();
		iniRetu();
		iniPosition();
	}

	private void iniSave() {
		Texture disBuffer = new Texture(Config.SAVE_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, disBuffer.getWidth() / 2, disBuffer.getHeight());
		save = new ImageButton(getButtonStyle(tr));
		save.addListener(new InputListener() { 
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ScreenshotFactory.saveBufferScreenshot((int)Config.SL_PIC_WIDTH,
						(int)Config.SL_PIC_HEIGHT);
				Screen game = THG.getGame().getScreen();
				THG.getGame().setScreen(new GSaveMenu(game));
				
				return true;
			}
		});
		stage.addActor(save);
	}

	private void iniLoad() {
		Texture disBuffer = new Texture(Config.LOAD_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, disBuffer.getWidth() / 2, disBuffer.getHeight());
		load = new ImageButton(getButtonStyle(tr));
		load.addListener(new InputListener() { 
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Screen game = THG.getGame().getScreen();
				THG.getGame().setScreen(new GLoadMenu(game));
				
				return true;
			}
		});
		stage.addActor(load);
	}

	private void iniSkip() {
		Texture disBuffer = new Texture(Config.SKIP_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, disBuffer.getWidth() / 2, disBuffer.getHeight());
		skip = new ImageButton(getButtonStyle(tr));
		skip.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Screen s = THG.getGame().getScreen();
				if(!(s instanceof GGameController)) return false;
				GGameController g = (GGameController)s;
				if(g.getSkipFlag())
					g.setSkip(false);
				else {
					g.setSkip(true);
					g.setAuto(false);
				}
				return true;
			}
		});
		skip.addAction(new Action() {
			@Override
			public boolean act(float delta) {
				Screen s = THG.getGame().getScreen();
				if(!(s instanceof GGameController)) return false;
				if(((GGameController)s).getSkipFlag())
					skip.setChecked(true);
				else 
					skip.setChecked(false);
				return false;
			}
		});
		stage.addActor(skip);
	}

	private void iniAuto() {
		Texture disBuffer = new Texture(Config.AUTO_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, disBuffer.getWidth() / 2, disBuffer.getHeight());
		auto = new ImageButton(getButtonStyle(tr));
		auto.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Screen s = THG.getGame().getScreen();
				if(!(s instanceof GGameController)) return false;
				GGameController g = (GGameController)s;
				if(g.getAutoFlag())
					g.setAuto(false);
				else {
					g.setAuto(true);
					g.setSkip(false);
					skip.setChecked(false);
				}
				return true;
			}
		});
		auto.addAction(new Action() {
			@Override
			public boolean act(float delta) {
				Screen s = THG.getGame().getScreen();
				if(!(s instanceof GGameController)) return false;
				if(((GGameController)s).getAutoFlag())
					auto.setChecked(true);
				else 
					auto.setChecked(false);
				return false;
			}
		});
		stage.addActor(auto);
	}

	private void iniRetu() {
		Texture disBuffer = new Texture(Config.RETURN_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, disBuffer.getWidth() / 2, disBuffer.getHeight());
		retu = new ImageButton(getButtonStyle(tr));
		retu.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().getScreen().dispose();
				THG.getGame().setScreen(new GMainMenu());
				return false;
			}
		});
		stage.addActor(retu);
	}
	private void iniPosition() {
		if (stage instanceof DefaultGameStage) {
			float 
				dx = Config.GAME_BUTTON_WIDHT * Config.scaleX,
				y = (Config.SCREEN_WIDTH - Config.WORDS_FRAME_WIDTH) / 2 * Config.scaleY,
				x = (Config.SCREEN_WIDTH - Config.WORDS_FRAME_WIDTH) / 2 * Config.scaleX +
						Config.WORDS_FRAME_WIDTH * Config.scaleX - 5 * dx;
			save.setPosition(x, y);
			load.setPosition(x + dx, y);
			retu.setPosition(x + 2 * dx, y);
			auto.setPosition(x + 3 * dx, y);
			skip.setPosition(x + 4 * dx, y);	
		}
		
			
	}

	private ImageButtonStyle getButtonStyle(TextureRegion[][] tr) {
		Drawable up = UiUtil.resize(tr[0][0], Config.GAME_BUTTON_WIDHT, Config.GAME_BUTTON_HEIGHT),
				down = UiUtil.resize(tr[0][1], Config.GAME_BUTTON_WIDHT, Config.GAME_BUTTON_HEIGHT);
		ImageButtonStyle ibs = new ImageButtonStyle();
		ibs.imageUp = up;
		ibs.imageDown = down;
		ibs.imageChecked = down;
		ibs.imageOver = down;
		return ibs;
	}

	@Override
	public void dispose() {
		for(Disposable d : disList)
			d.dispose();
	}
	
	
}
