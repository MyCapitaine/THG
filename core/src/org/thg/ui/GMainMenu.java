package org.thg.ui;

import java.util.ArrayList;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.ui.gallery.GGalleryMenu;
import org.thg.ui.sl.GLoadMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Disposable;

public class GMainMenu implements Screen, Disposable {
	private Stage stage;
	private GMainMenu gMainMenu;
	private ArrayList<Disposable> disList;
	private Disposable disBuffer;
	public GMainMenu() {
		gMainMenu = this;
		disList = new ArrayList<Disposable>();
		createStage();
	}
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		stage.act();
		stage.draw();
	}
	public void resize(int width, int height) {}
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}
	public void hide() {}
	public void pause() {}
	public void resume() {}
	public void dispose() {
		for(Disposable d : disList)
			d.dispose();
	}
	
	
	private void createStage() {
		stage = new Stage();
		TextureRegion[][] tr;
//============背景==================================================================
		disBuffer = new Texture(Config.MAIN_MENU_BG_URL);
		disList.add(disBuffer);
		Image bg = new Image((Texture)disBuffer);
		UiUtil.resize(bg);
		stage.addActor(bg);
//============开始游戏===============================================================
		disBuffer = new Texture(Config.MAIN_MENU_BEGIN_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		ImageButton beginButton = new ImageButton(
				UiUtil.resize(tr[0][0]),
				UiUtil.resize(tr[0][1]));
		beginButton.getStyle().imageOver = UiUtil.resize(tr[0][2]);
		beginButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().setScreen(new DefaultGameController());
				gMainMenu.dispose();
				return true;
			}
		});
		stage.addActor(beginButton);
//============读档==================================================================
		disBuffer = new Texture(Config.MAIN_MENU_LOAD_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		ImageButton loadButton = new ImageButton(
				UiUtil.resize(tr[0][0]),
				UiUtil.resize(tr[0][1]));
		loadButton.getStyle().imageOver = UiUtil.resize(tr[0][2]);
		loadButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().setScreen(new GLoadMenu());
				gMainMenu.dispose();
				return true;
			}
		});
		stage.addActor(loadButton);
//============Gallery==============================================================
		disBuffer = new Texture(Config.MAIN_MENU_GALLERY_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		ImageButton galleryButton = new ImageButton(
				UiUtil.resize(tr[0][0]),
				UiUtil.resize(tr[0][1]));
		galleryButton.getStyle().imageOver = UiUtil.resize(tr[0][2]);
		galleryButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().setScreen(new GGalleryMenu());
				gMainMenu.dispose();
				return true;
			}
		});
		stage.addActor(galleryButton);
//============设置==================================================================
		disBuffer = new Texture(Config.MAIN_MENU_SETTING_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		ImageButton settingButton = new ImageButton(
				UiUtil.resize(tr[0][0]),
				UiUtil.resize(tr[0][1]));
		settingButton.getStyle().imageOver = UiUtil.resize(tr[0][2]);
		settingButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().setScreen(new GSettingMenu());
				gMainMenu.dispose();
				return true;
			}
		});
		stage.addActor(settingButton);
//============退出==================================================================
		disBuffer = new Texture(Config.MAIN_MENU_EXIT_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split((Texture)disBuffer, Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		ImageButton exitButton = new ImageButton(
				UiUtil.resize(tr[0][0]),
				UiUtil.resize(tr[0][1]));
		exitButton.getStyle().imageOver = UiUtil.resize(tr[0][2]);
		exitButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ResourceFactory.clearPool();
				Gdx.app.exit();
				return true;
			}
		});
		stage.addActor(exitButton);
//============动画效果===============================================================
		
//		RotateToAction r = new RotateToAction();
		
		
		
//============组件位置大小等设置===============================================================
		float x = 600 * Config.scaleX, y = 30 * Config.scaleY, dy = 80 * Config.scaleY;
		beginButton.setPosition(x, y + 4 * dy);
		loadButton.setPosition(x, y + 3 * dy);
		galleryButton.setPosition(x, y + 2 * dy);
		settingButton.setPosition(x, y + dy);
		exitButton.setPosition(x, y);
		
		
		
	}
	
	
}
