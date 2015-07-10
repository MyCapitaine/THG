package org.thg.ui;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import org.thg.logic.THG;

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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.PropertiesUtils;

public class GSettingMenu implements Screen {
	private Stage stage;
	private GSettingMenu gSettingMenu;
	private ArrayList<Disposable> disList;
	private Disposable disBuffer1, disBuffer2;
	
	public GSettingMenu() {
		gSettingMenu = this;
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
		disBuffer1 = new Texture(Config.SETTING_MENU_BG_URL);
		disList.add(disBuffer1);
		Image bg = new Image((Texture)disBuffer1);
		stage.addActor(bg);
		
//============返回==================================================================
		disBuffer1 = new Texture(Config.SETTING_MENU_RETURN_BUTTON_URL);
		disList.add(disBuffer1);
		tr = TextureRegion.split((Texture)disBuffer1, Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		ImageButton returnButton = new ImageButton(
				UiUtil.resize(tr[0][0]),
				UiUtil.resize(tr[0][1]));
		returnButton.getStyle().imageOver = UiUtil.resize(tr[0][2]);
		returnButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().setScreen(new GMainMenu());
				gSettingMenu.dispose();
				saveProperties();
				return true;
			}
		});
		stage.addActor(returnButton);
//============音量调节===============================================================
		Image volLabel;
		final Slider volSlider;
		disBuffer1 = new Texture(Config.SETTING_MENU_VOL_LABEL_URL);
		disList.add(disBuffer1);
		volLabel = new Image((Texture)disBuffer1);
		disBuffer1 = new Texture(Config.SETTING_MENU_VOL_SLIDER_BG_URL);
		disBuffer2 = new Texture(Config.SETTING_MENU_VOL_SLIDER_KNOB_URL);
		disList.add(disBuffer1);
		disList.add(disBuffer2);
		SliderStyle volSliderStyle = new SliderStyle(
				UiUtil.resize(new TextureRegion((Texture)disBuffer1)),
				UiUtil.resize(new TextureRegion((Texture)disBuffer2)));
		volSlider = new Slider(Config.VOL_VOICE_MIN, Config.VOL_VOICE_MAX,
				Config.VOL_VOICE_STEP_SIZE, false, volSliderStyle);
		volSlider.setValue(Config.vol_voice);
		volSlider.setSize(Config.SLIDER_WIDTH * Config.scaleX, Config.SLIDER_HEIGHT * Config.scaleY);
		volSlider.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Config.vol_voice = volSlider.getValue();
			}
		});
		stage.addActor(volSlider);
		stage.addActor(volLabel);
//============文速调节===============================================================
//		Image speedLabel;
//		final Slider speedSlider;
//		disBuffer1 = new Texture(Config.SETTING_MENU_SPEED_LABEL_URL);
//		disList.add(disBuffer1);
//		speedLabel = new Image((Texture)disBuffer1);
//		disBuffer1 = new Texture(Config.SETTING_MENU_SPEED_SLIDER_BG_URL);
//		disBuffer2 = new Texture(Config.SETTING_MENU_SPEED_SLIDER_KNOB_URL);
//		disList.add(disBuffer1);
//		disList.add(disBuffer2);
//		SliderStyle speedSliderStyle = new SliderStyle(
//				UiUtil.resize(new TextureRegion((Texture)disBuffer1)),
//				UiUtil.resize(new TextureRegion((Texture)disBuffer2)));
//		speedSlider = new Slider(Config.SHOW_WORDS_SPEED_MIN, Config.SHOW_WORDS_SPEED_MAX,
//				Config.SHOW_WORDS_SPEED_STEP_SIZE, false, speedSliderStyle);
//		speedSlider.setValue(Config.showWordsSpeed);
//		speedSlider.setSize(Config.SLIDER_WIDTH * Config.scaleX, Config.SLIDER_HEIGHT * Config.scaleY);
//		speedSlider.addListener(new InputListener() {
//			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//				return true;
//			}
//			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//				Config.showWordsSpeed = speedSlider.getValue();
//			}
//		});
//		stage.addActor(speedSlider);
//		stage.addActor(speedLabel);
		
//============文间调节===============================================================
		Image intervalLabel;
		final Slider intervalSlider;
		disBuffer1 = new Texture(Config.SETTING_MENU_INTERVAL_LABEL_URL);
		disList.add(disBuffer1);
		intervalLabel = new Image((Texture)disBuffer1);
		disBuffer1 = new Texture(Config.SETTING_MENU_INTERVAL_SLIDER_BG_URL);
		disBuffer2 = new Texture(Config.SETTING_MENU_INTERVAL_SLIDER_KNOB_URL);
		disList.add(disBuffer1);
		disList.add(disBuffer2);
		SliderStyle intervalSliderStyle = new SliderStyle(
				UiUtil.resize(new TextureRegion((Texture)disBuffer1)),
				UiUtil.resize(new TextureRegion((Texture)disBuffer2)));
		intervalSlider = new Slider(Config.DIALOG_INTERVAL_MIN, Config.DIALOG_INTERVAL_MAX,
				Config.DIALOG_INTERVAL_STEP_SIZE, false, intervalSliderStyle);
		intervalSlider.setValue(Config.dialogInterval);
		intervalSlider.setSize(Config.SLIDER_WIDTH * Config.scaleX, Config.SLIDER_HEIGHT * Config.scaleY);
		intervalSlider.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Config.dialogInterval = intervalSlider.getValue();
			}
		});
		stage.addActor(intervalSlider);
		stage.addActor(intervalLabel);
		
//============开关百度娘语音===============================================================
//		CheckBox baiduBox;

//============动画效果===============================================================
		
//============组件位置===============================================================
		float dx = 10 * Config.scaleX,
			dy = 100 * Config.scaleY,
			x = 10 * Config.scaleX,
			y1 = 20 * Config.scaleY,
			dy2 = 15 * Config.scaleY; 
		volLabel.setPosition(x, y1);
		UiUtil.resize(volLabel);
		volSlider.setPosition(volLabel.getX() + volLabel.getWidth() + dx,
				volLabel.getY() - dy2);
//		speedLabel.setPosition(x, y1 + dy);
//		UiUtil.resize(speedLabel);
//		speedSlider.setPosition(speedLabel.getX() + speedLabel.getWidth() + dx,
//				speedLabel.getY() - dy2);
		intervalLabel.setPosition(x, y1 + 2 * dy);
		UiUtil.resize(intervalLabel);
		intervalSlider.setPosition(intervalLabel.getX() + intervalLabel.getWidth() + dx,
				intervalLabel.getY() - dy2);

		UiUtil.resize(bg);
		returnButton.setPosition(600 * Config.scaleX, 30 * Config.scaleY);
		
	}
	
	
	
	
	
	
	
	
	private static ObjectMap<String, String> paraMap;
	/**
	 * 读取设置，启用LoadingMenu时调用
	 */
	static void loadProperties() {
		if(paraMap == null) 
			paraMap = new ObjectMap<String, String>();
		Reader fr = null;
		try {
			fr = Gdx.files.external(THG.EXTRNAL_HEAD + Config.PROPERTIES_URL).reader(THG.CHAR_SET);
		}catch(Exception e) {
			try {
				fr = Gdx.files.internal(Config.PROPERTIES_URL).reader(THG.CHAR_SET);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		try {
			PropertiesUtils.load(paraMap, fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Config.vol_voice = Float.parseFloat(paraMap.get("vol_voice"));
			if(Config.vol_voice < Config.VOL_VOICE_MIN || Config.vol_voice > Config.VOL_VOICE_MAX) Config.vol_voice = Config.VOL_VOICE_MIN;
			Config.vol_bgm = Float.parseFloat(paraMap.get("vol_bgm"));
			if(Config.vol_bgm < Config.VOL_BGM_MIN || Config.vol_bgm > Config.VOL_BGM_MAX) Config.vol_bgm = Config.VOL_BGM_MIN;
			Config.showWordsSpeed = Float.parseFloat(paraMap.get("showWordsSpeed"));
			if(Config.showWordsSpeed < Config.SHOW_WORDS_SPEED_MIN || Config.showWordsSpeed > Config.SHOW_WORDS_SPEED_MAX) Config.showWordsSpeed = Config.SHOW_WORDS_SPEED_MIN;
			Config.dialogInterval = Float.parseFloat(paraMap.get("dialogInterval"));
			if(Config.dialogInterval < Config.DIALOG_INTERVAL_MIN || Config.dialogInterval > Config.DIALOG_INTERVAL_MAX) Config.dialogInterval = Config.DIALOG_INTERVAL_MIN;
			
		}catch(Exception e) {}
	}
	/**
	 * 存储设置，关闭SettingMenu时调用
	 */
	static void saveProperties() {
		paraMap.put("vol_voice", Float.toString(Config.vol_voice));
		paraMap.put("vol_bgm", Float.toString(Config.vol_bgm));
		paraMap.put("showWordsSpeed", Float.toString(Config.showWordsSpeed));
		paraMap.put("dialogInterval", Float.toString(Config.dialogInterval));
		Writer fw = null;
		try {
			fw = Gdx.files.external(THG.EXTRNAL_HEAD + Config.PROPERTIES_URL).writer(false, THG.CHAR_SET);
		}catch(Exception e) {
			System.err.println("GSettingMenu.saveProperties() writer create fail");
			return;
		}
		try {
			PropertiesUtils.store(paraMap, fw, null);
		} catch (IOException e) {
			System.err.println("GSettingMenu.saveProperties() store fail");
		}
		try {
			fw.close();
		} catch (IOException e) {
		}
	}
}
