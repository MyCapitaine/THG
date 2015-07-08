/**
 * 
 */
package org.thg.logic.factorys;

import java.util.HashMap;

import org.thg.logic.THG;
import org.thg.logic.story.api.GDay;
import org.thg.logic.story.api.GDialog;
import org.thg.logic.story.api.GEffectScene;
import org.thg.logic.story.api.GScene;
import org.thg.logic.story.driver.Config;
import org.thg.logic.story.driver.DefaultDay;
import org.thg.logic.story.driver.DefaultDialog;
import org.thg.logic.story.driver.scene.DefaultArticleScene;
import org.thg.logic.story.driver.scene.DefaultDeadScene;
import org.thg.logic.story.driver.scene.DefaultEffectScene;
import org.thg.logic.story.driver.scene.DefaultScene;

import com.badlogic.gdx.Gdx;

/**
 * <p>同步形式读取一日的数据</p>
 * <p>PC测试17W字操作时间29ms，12w字21ms，6w字17ms</p>
 * <p>一日2~3w字的文本量在1帧16ms内大概不会造成阻塞</p>
 * @author MyCapitaine
 *
 */
public class GDayFactory {
	/**
	 * 是否输出信息来检测数据问题
	 */
	private static final boolean DEBUG = false;
	/**
	 * <p>根据形如01-02的日期字符串读取日期文件</p>
	 * <p>以递归形式依次读取日期中的场景和对话</p>
	 * @param dayStr 日期字符串
	 * @return 转换成的GDay对象
	 */
	public static GDay createDay(String dayStr) {
		String dayData = null;
		try {
			dayData = Gdx.files.internal("days/" + dayStr + ".d").readString(THG.CHAR_SET);
		}catch(Exception e) {
			if(DEBUG) System.err.println("day data load fail");
			return null;
		}
		String[] splitedData = dayData.split(SCENES_SPLIT_SIGN);
		if(splitedData.length <= 1) {
			if(DEBUG) System.err.println("day data incomplete");
			return null;
		}

		GDay d = new DefaultDay();
		GScene[] scenes = new DefaultScene[splitedData.length - 1];
		for(int i = 1; i < splitedData.length; i ++) {
			scenes[i - 1] = readScene(splitedData[i].trim());
		}
		d.setScenes(scenes);
		d.setDayStr(dayStr);
		
		return d;
	}
	private static final String SCENES_SPLIT_SIGN = "(<scene)(.*)(>)";
	private static final String DIALOGS_SPLIT_SIGN = "(<d)(.*)(>)";
	private static final String DATAS_SPLIT_SIGN = " ";
	
	private static GScene readScene(String info) {
		String[] splitedData = info.split(DIALOGS_SPLIT_SIGN);
		if(splitedData.length == 0){
			if(DEBUG) System.err.println("scene data has problem");
			return null;
		}
		
		 
		HashMap<String, String> sceneDatasMap = new HashMap<String, String>();
		String bufferStr;
		String[] sceneInfos = splitedData[0].trim().split(DATAS_SPLIT_SIGN);
		for(String s : sceneInfos) {
			try {
				String[] key_value = s.split("=");
				sceneDatasMap.put(key_value[0], delMark(key_value[1]));
			}catch(Exception e) {
				if(DEBUG) System.err.println("scene info writes wrong");
			}
		}
		
		
		GScene scene = null;
		GDialog[] dialogs = new DefaultDialog[splitedData.length - 1];
		
		bufferStr = sceneDatasMap.get(SCENE_TYPE);
		if(bufferStr != null && bufferStr.length() > 0)
			switch(bufferStr.charAt(0)) {
			case '1' :
				scene = new DefaultScene();
				for(int i = 1; i < splitedData.length; i ++) {
					dialogs[i - 1] = readDialog(splitedData[i].trim());
				}
				scene.setDialogs(dialogs);
				break;
			case '2' :
				scene = new DefaultArticleScene();
				for(int i = 1; i < splitedData.length; i ++) {
					dialogs[i - 1] = readDialog(splitedData[i].trim());
				}
				scene.setDialogs(dialogs);
				break;
			case '3' :
				scene = new DefaultEffectScene();
				try { ((GEffectScene)scene).setEffectNum(Integer.parseInt(sceneDatasMap.get(EFFECT_NUM))); }
				catch(Exception e) { if(DEBUG) System.err.println("scene effectNum error");}
				break;
			case '4' :
				scene = new DefaultDeadScene();
				for(int i = 1; i < splitedData.length; i ++) {
					dialogs[i - 1] = readDialog(splitedData[i].trim());
				}
				scene.setDialogs(dialogs);
				break;
			default :
				if(DEBUG) System.err.println("scene type error");
				return null;
			}
		
		bufferStr = sceneDatasMap.get(BACKGROUND);
		if(bufferStr != null) {
			try { scene.setBackground(Integer.parseInt(bufferStr)); }
			catch(Exception e) { if(DEBUG) System.err.println("scene background error");}
		}
		
		bufferStr = sceneDatasMap.get(BACKGROUND_MUSIC);
		if(bufferStr != null) {
			try { scene.setBgMusic(Integer.parseInt(bufferStr)); }
			catch(Exception e) { if(DEBUG) System.err.println("scene backgroundmusic error");}
		}
		
		
		scene.setConditions(sceneDatasMap.get(CONDITION_INFO));

		return scene;
		
	}
	
	
	private static GDialog readDialog(String info) {
		GDialog dialog = new DefaultDialog();
		HashMap<String, String> dialogDatasMap = new HashMap<String, String>();
		String bufferStr;
		String[] dialogInfos = info.split(DATAS_SPLIT_SIGN);
		for(String s : dialogInfos) {
			String[] key_value = s.split("=");
			try {
				dialogDatasMap.put(key_value[0], delMark(key_value[1]));
			}catch(Exception e) {
				if(DEBUG) System.err.println("dialog data error");
			}
		}
		dialog.setName(dialogDatasMap.get(NAME));
		
		bufferStr = dialogDatasMap.get(CHARACTER_PIC);
		if(bufferStr != null) {
			try { dialog.setCharactorPic(Integer.parseInt(bufferStr)); }
			catch(Exception e) { if(DEBUG) System.err.println("dialog character picture num error"); }
		}
		
		bufferStr = dialogDatasMap.get(CHARACTER_PIC_EFFECT);
		if(bufferStr != null) {
			try { dialog.setCharactorPicEffect(Integer.parseInt(bufferStr)); }
			catch(Exception e) { if(DEBUG) System.err.println("dialog character effect num error"); }
		}
		
		
		try {
			switch(dialogDatasMap.get(CHARACTER_PIC_POSITION).charAt(0)) {
			case 'l' : dialog.setCharactorPicPosition(Config.CHARACTOR_PIC_POSITION_LEFT); break;
			case 'r' : dialog.setCharactorPicPosition(Config.CHARACTOR_PIC_POSITION_RIGHT); break;
			case 'c' : dialog.setCharactorPicPosition(Config.CHARACTOR_PIC_POSITION_CENTER); break;
			case 'n' : dialog.setCharactorPicPosition(Config.CHARACTOR_PIC_POSITION_NEAR); break;
			case 'f' : dialog.setCharactorPicPosition(Config.CHARACTOR_PIC_POSITION_FAR); break;
			default :
			}
		}catch(Exception e) {}
		
		bufferStr = dialogDatasMap.get(VIDEO);
		if(bufferStr != null) {
			try { dialog.setVideo(Integer.parseInt(bufferStr)); }
			catch(Exception e) { if(DEBUG) System.err.println("dialog character video num error"); }
		}
		
		dialog.setConditions(dialogDatasMap.get(CONDITION_INFO));
		
		dialog.setWords(dialogDatasMap.get(WORDS));
		
		dialog.setChoice(dialogDatasMap.get(CHOICE));
		
		return dialog;
	}
	
	
	/**
	 * <p>删除字符串两边的引号</p>
	 * <p>参考{@link String#trim()}</p>
	 * @param str 删除前
	 * @return 删除后的字符串
	 */
	private static String delMark(String str) {
		if(str == null) return null;
		int len = str.length();
		int st = 0;
		while((st < len) && str.charAt(st) == '"') 
			st ++;
		while((st < len) && str.charAt(len - 1) == '"')
			len --;
		
		return ((st > 0) || (len < str.length())) ? str.substring(st, len) : str;
	}
	
	private static final String 
		SCENE_TYPE = "st",
		BACKGROUND = "bg",
		BACKGROUND_MUSIC = "bgm",
		CONDITION_INFO = "cond";
	private static final String
		CHARACTER_PIC = "cp",
		CHARACTER_PIC_POSITION = "cpp",
		CHARACTER_PIC_EFFECT = "cpe",
		VIDEO = "vd",
		WORDS = "wd",
		CHOICE = "cho",
		NAME = "nam",
		EFFECT_NUM = "efn";
}
