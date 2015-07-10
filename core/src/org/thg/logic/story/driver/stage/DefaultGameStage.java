package org.thg.logic.story.driver.stage;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GDialog;
import org.thg.logic.story.api.GGameStage;
import org.thg.logic.story.api.GSceneChangeModel;
import org.thg.logic.story.api.GShowWordsModel;
import org.thg.logic.story.driver.Config;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.ui.GChoiceWindow;
import org.thg.ui.GGameButtons;
import org.thg.ui.GWordsFrame;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class DefaultGameStage extends Stage implements GGameStage {
	private Music voice;
	
	private int charactorPicNum;
	private int charactorPicPosition;
	private int charactorPicEffect;
	
	private Image charactorPic;
	private Image bg;
	private GWordsFrame wordsFrame;
	private Texture nameBg;
	private BitmapFont nameFont;
	private String name;
	private GGameButtons gameButtons;
	private GChoiceWindow choice;
	
	
	public DefaultGameStage() {
		super();
		voice = null;
		charactorPicNum = -1;
		charactorPicPosition = -1;
		charactorPicEffect = -1;
		iniWeight();
	}
	
	@Override
	public void setVideo(int num) {
		if(num == -1 || num == 0) return;
		if(voice != null) voice.dispose();
		voice = ResourceFactory.getVoice(num);
		if(voice != null) {
			voice.setVolume(org.thg.ui.Config.vol_voice);
			voice.play();
		}
	}

	@Override
	public void setCharatorPic(int cpNum) {
		if(cpNum == ResourceFactory.PRE_INT || cpNum == charactorPicNum) return;
		charactorPicNum = cpNum;
		Texture t = ResourceFactory.getCharactorPic(cpNum);
		if(t == null) charactorPic.setDrawable(null);
		else charactorPic.setDrawable(UiUtil.resize(new TextureRegion(t)));
	}
	@Override
	public void setCharactorPicPosition(int position) {
		if(position == ResourceFactory.PRE_INT || position == charactorPicPosition) return;
		charactorPicPosition = position;
		switch(position) {
		case Config.CHARACTOR_PIC_POSITION_LEFT :
			charactorPic.setBounds(
					- Config.CHARACTOR_PIC_WIDTH * org.thg.ui.Config.scaleX / 4,
					- Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY / 3,
					Config.CHARACTOR_PIC_WIDTH * org.thg.ui.Config.scaleX,
					Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY);
			break;
		case Config.CHARACTOR_PIC_POSITION_RIGHT :
			charactorPic.setBounds(
					(org.thg.ui.Config.SCREEN_WIDTH - Config.CHARACTOR_PIC_WIDTH * 3 / 4) * org.thg.ui.Config.scaleX,
					- Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY / 3,
					Config.CHARACTOR_PIC_WIDTH * org.thg.ui.Config.scaleX,
					Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY);
			break;
		case Config.CHARACTOR_PIC_POSITION_CENTER :
			charactorPic.setBounds(
					(org.thg.ui.Config.SCREEN_WIDTH - Config.CHARACTOR_PIC_WIDTH) * org.thg.ui.Config.scaleX / 2,
					- Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY / 3,
					Config.CHARACTOR_PIC_WIDTH * org.thg.ui.Config.scaleX,
					Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY);
			break;
		case Config.CHARACTOR_PIC_POSITION_FAR :
			charactorPic.setBounds(
					(org.thg.ui.Config.SCREEN_WIDTH - Config.CHARACTOR_PIC_WIDTH) * org.thg.ui.Config.scaleX / 2,
					0,
					Config.CHARACTOR_PIC_WIDTH * org.thg.ui.Config.scaleX * 2 / 3,
					Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY * 2 / 3);
			break;
		case Config.CHARACTOR_PIC_POSITION_NEAR :
			float width = Config.CHARACTOR_PIC_WIDTH * org.thg.ui.Config.scaleX * 8 / 5;
			float height = Config.CHARACTOR_PIC_HEIGHT * org.thg.ui.Config.scaleY * 8 / 5;
			charactorPic.setBounds(
					org.thg.ui.Config.SCREEN_WIDTH * org.thg.ui.Config.scaleX / 2 - width / 2,
					- height * 7 / 15,
					width,
					height);
			break;
		default :
			setCharactorPicPosition(Config.CHARACTOR_PIC_POSITION_DEFAULT);
		}
		
	}

	@Override
	public void setBgm(int bgmNum) {
		DefaultGameController.setBgm(bgmNum);
	}
	
	private Texture bgBuffer;
	@Override
	public void setBg(int bgNum) {
		if(bgBuffer != null) bgBuffer.dispose();
		bgBuffer = ResourceFactory.getBg(bgNum);
		bg.setDrawable(UiUtil.resize(new TextureRegion(bgBuffer)));
	}
	@Override
	public void setCharactorPicEffect(int eNum) {
		//TODO createEffect
		
		// 大概是个action
		charactorPicEffect = eNum;
	}
	@Override
	public void setSceneChangeModel(GSceneChangeModel scm) { }
	@Override
	public void setShowWordsModel(GShowWordsModel swm) { }
	@Override
	public void dispose() {
		super.dispose();
		if(voice != null) voice.dispose();
		if(bgBuffer != null) bgBuffer.dispose();
		if(wordsFrame != null) wordsFrame.dispose();
		if(gameButtons != null) gameButtons.dispose();
		if(nameBg != null) nameBg.dispose();
		if(nameFont != null) nameFont.dispose();
	}
	
	@Override
	public boolean isRunning() {
		return (wordsFrame != null && wordsFrame.isRunning()) || (voice != null && voice.isPlaying());
	}
	
	@Override
	public void setChoice(String choiceStr) {
		if(choiceStr == null || choiceStr.equals("")) return;
		choice = new GChoiceWindow(choiceStr, this);
		
		//当对话结束时出现选项
		addAction(new Action() {
			@Override
			public boolean act(float delta) {
				if(isRunning()) return false;
				addActor(choice);
				return true;
			}
		});		
	}
	
	private void iniWeight() {
//====Background=================================================================================
		bg = new Image();
		bg.setSize(org.thg.ui.Config.SCREEN_WIDTH * org.thg.ui.Config.scaleX,
				org.thg.ui.Config.SCREEN_HEIGHT * org.thg.ui.Config.scaleY);
		addActor(bg);
//====CharactorPic=================================================================================
		charactorPic = new Image();
		addActor(charactorPic);	
//====WordsFrame=================================================================================
		wordsFrame = new GWordsFrame();
		addActor(wordsFrame);
//====Name=================================================================================
		name = null;
		nameFont = null;
		nameBg = new Texture(Config.NAME_BG_URL);
		name_width = Config.NAME_WIDTH * org.thg.ui.Config.scaleX;
		name_height = Config.NAME_HEIGHT * org.thg.ui.Config.scaleY;
		name_x = wordsFrame.getX();
		name_y = wordsFrame.getY() + wordsFrame.getHeight() + name_height - Config.NAME_HEIGHT * org.thg.ui.Config.scaleY;
		name_padding = (name_height - Config.NAME_FONT_SIZE * org.thg.ui.Config.scaleY) / 2;
//====GGameButtons==============================================================================
		gameButtons = new GGameButtons(this);
		
	}
	
	@Override
	public void draw() {
		super.draw();
		drawName();
	}
	
	private void drawName() {
		Batch batch = getBatch();
		if(batch == null) return;
		batch.begin();
		
		if(nameBg != null && !name.equals("")) batch.draw(nameBg, name_x, name_y, name_width, name_height);
		if(nameFont != null) nameFont.draw(batch, name,
				name_x + name_padding, name_y + name_height - name_padding);
		batch.end();
	}
	
	@Override
	public void setDialog(GDialog dialog) {
		setVideo(dialog.getVideo());
		setCharatorPic(dialog.getCharactorPic());
		setCharactorPicPosition(dialog.getCharactorPicPosition());
		setCharactorPicEffect(dialog.getCharactorPicEffect());
		setChoice(dialog.getChoice());
		wordsFrame.setText(dialog.getWords());
		setNameText(dialog.getName());
	}

	
	private void setNameText(String name) {
		if(name == null || name.equals(this.name)) return;
		this.name = name;
		if(nameFont != null) nameFont.dispose();
		nameFont = THG.getFont(name, (int)Config.NAME_FONT_SIZE, Color.BLACK);
	}
	private float name_width, name_height, name_x, name_y, name_padding;
}
