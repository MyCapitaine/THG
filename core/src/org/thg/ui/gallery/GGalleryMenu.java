package org.thg.ui.gallery;

import java.util.ArrayList;

import org.thg.logic.THG;
import org.thg.ui.Config;
import org.thg.ui.GMainMenu;
import org.thg.ui.UiUtil;

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

public class GGalleryMenu implements Screen, Disposable {
	private Stage stage;
	private GGalleryMenu gGalleryMenu;
	private ArrayList<Disposable> disList;

	public GGalleryMenu() {
		gGalleryMenu = this;
		disList = new ArrayList<Disposable>();
		createStage();
	}
	
	@Override
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
		Texture disBuffer = new Texture(Config.GALLERY_MENU_BG_URL);
		disList.add(disBuffer);
		Image bg = new Image(disBuffer);
		bg.setSize(Config.SCREEN_WIDTH * Config.scaleX, Config.SCREEN_HEIGHT * Config.scaleY);
		
		stage.addActor(bg);
//============返回==================================================================
		disBuffer = new Texture(Config.GALLERY_MENU_RETURN_BUTTON_URL);
		disList.add(disBuffer);
		tr = TextureRegion.split(disBuffer, disBuffer.getWidth() / 3, disBuffer.getHeight());
		ImageButton returnButton = new ImageButton(
				UiUtil.resize(tr[0][0], Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT),
				UiUtil.resize(tr[0][1], Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT));
		returnButton.getStyle().imageOver = UiUtil.resize(tr[0][2], Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		returnButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().setScreen(new GMainMenu());
				gGalleryMenu.dispose();
				return true;
			}
		});
		stage.addActor(returnButton);
		
//============组件位置大小设置等===============================================================
		returnButton.setPosition(600 * Config.scaleX, 30 * Config.scaleY);
		
	}
}
