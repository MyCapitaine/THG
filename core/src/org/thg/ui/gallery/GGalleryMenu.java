package org.thg.ui.gallery;

import java.util.ArrayList;

import org.thg.logic.THG;
import org.thg.ui.Config;
import org.thg.ui.GMainMenu;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Disposable;
/**
 * cg/音乐鉴赏部分
 * @author MyCapitaine
 *
 */
public class GGalleryMenu extends ScreenAdapter {
	
	Music currentMusic;
	MusicInfo[] musicInfos;

	MusicPart musicPart;
	CGPart cgPart;
	
	private Stage stage;
	private ArrayList<Disposable> disList;
	private Image CGLabel, MusicLabel;
	
	public GGalleryMenu() {
		disList = new ArrayList<Disposable>();
		stage = new Stage();
		musicInfos = MusicInfo.loadMusicInfo();
		
		iniBackground();
		iniReturnButton();
		iniLabels();
		
		
		cgPart = new CGPart();
		musicPart = null;
		
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
		for(Disposable d : disList)
			d.dispose();
	}
	/** 背景 */
	private void iniBackground() {
		Texture disBuffer = new Texture(Config.GALLERY_MENU_BG_URL);
		disList.add(disBuffer);
		Image bg = new Image(disBuffer);
		bg.setSize(Config.SCREEN_WIDTH * Config.scaleX, Config.SCREEN_HEIGHT * Config.scaleY);
		stage.addActor(bg);
	}
	/** 返回键 */
	private void iniReturnButton() {
		Texture disBuffer = new Texture(Config.GALLERY_MENU_RETURN_BUTTON_URL);
		disList.add(disBuffer);
		TextureRegion[][] tr = TextureRegion.split(disBuffer, disBuffer.getWidth() / 3, disBuffer.getHeight());
		ImageButton returnButton = new ImageButton(
				UiUtil.resize(tr[0][0], Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT),
				UiUtil.resize(tr[0][1], Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT));
		returnButton.getStyle().imageOver = UiUtil.resize(tr[0][2], Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		returnButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().getScreen().dispose();
				THG.getGame().setScreen(new GMainMenu());
				return true;
			}
		});
		stage.addActor(returnButton);
		returnButton.setPosition(600 * Config.scaleX, 30 * Config.scaleY);
	}
	
	/** 
	 * <p>cg和music的label
	 * <p>切换时，标签动态交换位置和大小，组件渐隐/现
	 * 
	 */
	private void iniLabels() {
		showingCG = true;
		
		Texture texture = new Texture(Config.GALLERY_MENU_CG_LABEL_URL);
		disList.add(texture);
		CGLabel = new Image(texture);
		CGLabel.setBounds(Config.GALLERY_MENU_PADDING * Config.scaleX, 
				(Config.SCREEN_HEIGHT - Config.GALLERY_MENU_PADDING - Config.GALLERY_MENU_LABEL_LARGER_HEIGHT) * Config.scaleY,
				Config.GALLERY_MENU_LABEL_LARGER_WIDTH * Config.scaleX,
				Config.GALLERY_MENU_LABEL_LARGER_HEIGHT * Config.scaleY);
		
		texture = new Texture(Config.GALLERY_MENU_MUSIC_LABEL_URL);
		disList.add(texture);
		MusicLabel = new Image(texture);
		MusicLabel.setBounds(Config.GALLERY_MENU_PADDING * Config.scaleX,
				(Config.SCREEN_HEIGHT - Config.GALLERY_MENU_PADDING - Config.GALLERY_MENU_LABEL_LARGER_HEIGHT - Config.GALLERY_MENU_LABEL_SMALLER_HEIGHT) * Config.scaleY,
				Config.GALLERY_MENU_LABEL_SMALLER_WIDTH * Config.scaleX,
				Config.GALLERY_MENU_LABEL_SMALLER_HEIGHT * Config.scaleY);
		
		isDoingAction = NOT_DOING_ACTION;
		InputListener listener = new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if(isDoingAction != NOT_DOING_ACTION) return false;
				labelDoAction();
				changePart();
				showingCG = !showingCG;
				return true;
			}
		};
		
		CGLabel.addListener(listener);
		MusicLabel.addListener(listener);
	}
	private static int isDoingAction;
	private static final int NOT_DOING_ACTION = 0;
	/** 标签做交换的运动 */
	private void labelDoAction() {
		
//		isDoingAction ++;
//		isDoingAction --;
//		TODO
		
		
	}
	private static boolean showingCG;
	/** CG和music的部分交换 */
	private void changePart() {
//		TODO
	}
	
}
