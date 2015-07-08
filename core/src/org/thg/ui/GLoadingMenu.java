package org.thg.ui;

import java.util.ArrayList;

import org.thg.logic.THG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;

public class GLoadingMenu implements Screen, Disposable {
	private AssetManager manager;
	private Stage stage;
	private ArrayList<Disposable> disList;
	private Disposable disBuffer;
	public GLoadingMenu() {
		disList = new ArrayList<Disposable>();
		manager = new AssetManager();
		GSettingMenu.loadProperties();
		createStage();
		setManagerResolver();
		fillManager();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		stage.act();
//		stage.draw();
		Batch b = stage.getBatch();
		b.begin();
		float aph = manager.getProgress();
		stage.getRoot().draw(b, aph);
		b.end();
		
		if(manager.update()) {
			THG.getGame().setScreen(new GMainMenu());
			dispose();
		}
	}
	
	public void resize(int width, int height) {}
	public void show() {}
	public void hide() {}
	public void pause() {}
	public void resume() {}
	public void dispose() {
		for(Disposable d : disList)
			d.dispose();
	}
	
	private void createStage() {
		stage = new Stage();
		disBuffer = new Texture(Config.LOADING_MENU_BG_URL);
		disList.add(disBuffer);
		Image bg = new Image((Texture)disBuffer);
		UiUtil.resize(bg);
		stage.addActor(bg);
		
		//TODO 动画
		
	}
	
	private void setManagerResolver() {

		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
	}
	
	private void fillManager() {
		FileHandle[]
			assets_image,
			assets_font;
		assets_image = Gdx.files.internal("images").list();
		for(FileHandle f : assets_image) {
			System.out.println("load");
			load(f, Texture.class);
		}
		
		assets_font = Gdx.files.internal("fonts").list();
		for(FileHandle f : assets_font) 
			manager.load(f.path(), FreeTypeFontGenerator.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void load(FileHandle fh, Class type) {
		if (!fh.isDirectory()) {
			manager.load(fh.path(), type);
		}
		else {
			FileHandle[] fhlist = fh.list();
			for(FileHandle f : fhlist) load(f, type);
		}
	}

}
