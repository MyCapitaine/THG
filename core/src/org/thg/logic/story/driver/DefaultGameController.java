package org.thg.logic.story.driver;

import java.util.GregorianCalendar;

import org.thg.logic.THG;
import org.thg.logic.factorys.GDayFactory;
import org.thg.logic.factorys.GStageFactory;
import org.thg.logic.factorys.GStoryTreeFactory;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.*;
import org.thg.logic.story.driver.progressdata.ProgressDataUtil;
import org.thg.logic.story.driver.stage.DefaultArticleStage;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.DefaultGameStage;
import org.thg.logic.story.driver.stage.DefaultTurnPageStage;
import org.thg.ui.GMainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DefaultGameController implements GGameController {
	private GStoryTree storyTree;
	private ProgressData progressData;
	private Stage current_stage;
	private GDay current_day;
	private GScene current_scene;
	private GDialog current_dialog;
	private KeyInputProcessor kip;
	private boolean choosing;
	private boolean pausing; //预留的暂停
	private boolean skipping;
	private boolean autoing;
	
	private static Music bgm = null;
	private static int bgmNum = -1;
	
	public DefaultGameController() {
		this(null);
	}
	
	public DefaultGameController(ProgressData pd) {
		storyTree = GStoryTreeFactory.createStoryTree();
		if(pd == null) setProgressDataAndIni(ProgressDataUtil.load());
		else setProgressDataAndIni(pd);
		kip = new KeyInputProcessor();
		pausing = false;
		skipping = false;
		autoing = false;
		choosing = false;
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		if(autoing || skipping)control(false);
		current_stage.act();
		current_stage.draw();
	}

	public void resize(int width, int height) {}
	public void show() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(current_stage);
		multiplexer.addProcessor(kip);
		Gdx.input.setInputProcessor(multiplexer);
	}
	public void hide() {}
	public void pause() { 
		pausing = true;
		autoing = false;
		skipping = false;
	}
	public void resume() { 
		pausing = false;
		show(); 
	}
	
	public boolean getPausingFlag(){ return pausing; }
	public void setSkip(boolean flag) { skipping = flag; }
	public boolean getSkipFlag() { return skipping; }
	public void setAuto(boolean flag) { autoing = flag; }
	public boolean getAutoFlag() { return autoing; }
	public void setIsChoosing(boolean flag) { choosing = flag; }
	public boolean getChoosingFlag() { return choosing; }
	
	@Override
	public void setProgressDataAndIni(ProgressData pd) {
		progressData = pd;
		current_day = GDayFactory.createDay(pd.getDayStr());
		current_scene = current_day.setScenePosition(progressData.getSceneNum());
		current_dialog = current_scene.setDialogPosition(progressData.getDialogNum());
		
		if(current_scene instanceof GEffectScene) current_stage = (DefaultEffectStage)GStageFactory.createEffectStage((GEffectScene) current_scene);
		else if(current_scene instanceof GArticleScene) current_stage = (DefaultArticleStage)GStageFactory.createArticleStage((GArticleScene) current_scene, current_dialog);
		else current_stage = (DefaultGameStage)GStageFactory.createGameStage(current_scene, current_dialog);
		
		
	}
	@Override
	public ProgressData getCurrentProgressData() {
		return progressData;
	}
	@Override
	public void setStoryTree(GStoryTree gst) { storyTree = gst; }
	@Override
	public boolean setNextDay() {
		String nextDayStr = storyTree.getNextDay(current_day.getDayStr());
		if(nextDayStr == null) {
			current_day = null;
			return false;
		}
		current_day = GDayFactory.createDay(nextDayStr);
		return true;
	}
	@Override
	public boolean setNextScene() {
		current_scene = current_day.getNextScene();
		if(current_scene == null) return false;
		return true;
	}
	@Override
	public boolean setNextDialog() {
		current_dialog = current_scene.getNextDialog();
		if(current_dialog == null) return false;
		return true;
	}
	
	@Override
	public void control(boolean byHand) {
		//处于暂停状态则进行空调用
		if(pausing) return;
		//处于选择状态则进行空调用
		if(choosing) return;
		//手动调用直接跳过对现在是否仍进行的判断，打断当前状态
		if(((RunningCheckable) current_stage).isRunning(byHand)) return;
		
		if(current_stage instanceof GGameStage) {
			while(setNextDialog()) {
				((GGameStage)current_stage).setDialog(current_dialog);
				return;
			}
			//死亡场景为gamescene的子类的，当死亡场景的对话进行完毕后，游戏结束
			if(current_scene instanceof GDeadScene) {
				gameOver();
				return;
			}
			
			while(setNextScene()) {
				if(current_scene instanceof GEffectScene) {
					current_stage.dispose();
					current_stage = (DefaultEffectStage) GStageFactory.createEffectStage((GEffectScene)current_scene);
					show();
					return;
				}
				if(current_scene instanceof GArticleScene) {
					current_stage.dispose();
					current_stage = (DefaultArticleStage) GStageFactory.createArticleStage((GArticleScene)current_scene);
					show();
					return;
				}
				while(setNextDialog()) {
					current_stage.dispose();
					current_stage = (DefaultGameStage) GStageFactory.createGameStage(current_scene, current_dialog);
					show();
					return;
				}
			}
			if(!setNextDay()) {
				gameOver();
				return;
			}
			ResourceFactory.clearPool();
			current_stage.dispose();
			current_stage = (DefaultTurnPageStage) GStageFactory.createTurnPageStage(current_day);
			show();
			
		}
		else if(current_stage instanceof GArticleStage) {
			while(setNextDialog()) {
				((GArticleStage)current_stage).setDialog(current_dialog);
				return;
			}

			while(setNextScene()) {
				if(current_scene instanceof GEffectScene) {
					current_stage.dispose();
					current_stage = (DefaultEffectStage) GStageFactory.createEffectStage((GEffectScene)current_scene);
					show();
					return;
				}
				if(current_scene instanceof GArticleScene) {
					current_stage.dispose();
					current_stage = (DefaultArticleStage) GStageFactory.createArticleStage((GArticleScene)current_scene);
					show();
					return;
				}
				while(setNextDialog()) {
					current_stage.dispose();
					current_stage = (DefaultGameStage) GStageFactory.createGameStage(current_scene, current_dialog);
					show();
					return;
				}
			}
			if(!setNextDay()) {
				gameOver();
				return;
			}
			ResourceFactory.clearPool();
			current_stage.dispose();
			current_stage = (DefaultTurnPageStage) GStageFactory.createTurnPageStage(current_day);
			show();
			
		}
		else if(current_stage instanceof GEffectStage ||
				current_stage instanceof GTurnPageStage) {
			while(setNextScene()) {
				if(current_scene instanceof GEffectScene) {
					current_stage.dispose();
					current_stage = (DefaultEffectStage) GStageFactory.createEffectStage((GEffectScene)current_scene);
					show();
					return;
				}
				if(current_scene instanceof GArticleScene) {
					current_stage.dispose();
					current_stage = (DefaultArticleStage) GStageFactory.createArticleStage((GArticleScene)current_scene);
					show();
					return;
				}
				while(setNextDialog()) {
					current_stage.dispose();
					current_stage = (DefaultGameStage) GStageFactory.createGameStage(current_scene, current_dialog);
					show();
					return;
				}
			}
			if(!setNextDay()) {
				gameOver();
				return;
			}
			ResourceFactory.clearPool();
			current_stage.dispose();
			current_stage = (DefaultTurnPageStage) GStageFactory.createTurnPageStage(current_day);
			show();
		}
		else{}
		
		
		
	}
	
	@Override
	public void save(Pixmap screenshot, int progressNum) {
		int scenePosition, dialogPosition;
		if(current_day == null || current_day.getDayStr() == null) return;
		if((scenePosition = current_day.getPosition()) < 0) scenePosition = 0;
		if(current_scene == null || (dialogPosition = current_scene.getPosition()) < 0) dialogPosition = 0;
		
		progressData.putTime(
				current_day.getDayStr(),
				scenePosition,
				dialogPosition);
		progressData.putExtraInfo(Long.toString(new GregorianCalendar().getTimeInMillis()));
		
		ProgressDataUtil.saveData(screenshot, progressData, progressNum);
	}
	
	@Override
	public void dispose() {
		if(bgm != null) bgm.dispose();
	}
	/**
	 * 最终设置背景音乐的方法
	 * @param bNum 背景音乐编号
	 */
	public static void setBgm(int bNum) {
		setBgm(bNum, false);
	}
	/**
	 * 同上
	 * @param bNum 背景音乐编号
	 * @param isBreak true 表明一定打断当前音乐
	 */
	public static void setBgm(int bNum, boolean isBreak) {
		if(bNum == ResourceFactory.PRE_INT) return;
		if(bgm == null) {
			bgm = ResourceFactory.getBgm(bgmNum);
			if(bgm != null) {
				bgm.setVolume(org.thg.ui.Config.vol_bgm);
				bgm.setLooping(true);
				bgm.play();
			}
			return;
		}
		
		if(isBreak || bNum != bgmNum) {
			bgm.dispose();
			bgm = ResourceFactory.getBgm(bgmNum);
			if(bgm != null) {
				bgm.setVolume(org.thg.ui.Config.vol_bgm);
				bgm.setLooping(true);
				bgm.play();
			}
			return;
		}
		
		
		
	}

	private void gameOver() {
		THG.getGame().setScreen(new GMainMenu());
		this.dispose();
	}
	/** 点击间隔计数器 */
	private static long click_count = 0;
	/** 点击最小间隔 */
	private static final long CLICK_INTERVAL = 160L;
	private class KeyInputProcessor extends InputAdapter {

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			if(button != Buttons.LEFT) return false;
			return click();
		}
		@Override
		public boolean keyTyped(char character) {
			switch(character) {
			case 'a':
			case 'A': //a/A     —— auto
				setSkip(false);
				setAuto(getAutoFlag() ? false : true);
				return true;
			case ' ': //space   —— click
				return click();
			case 's' :
			case 'S' : //s/S —— skip
				setAuto(false);
				setSkip(getSkipFlag() ? false : true);
				return true;
			default :
				return false;
			}
		}
		
		@Override
		public boolean scrolled(int amount) {
			if(amount != 1) return false;
			return click();
		}
		
		
		private boolean click() {
			long l = System.currentTimeMillis();
			if(l - click_count < CLICK_INTERVAL) return false;
			
			click_count = l;
			control(true);
			setSkip(false);
			setAuto(false);
			return true;
		}
		
	}

	

}
