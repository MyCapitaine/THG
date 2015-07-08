package org.thg.logic.story.driver;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;

import org.thg.logic.THG;
import org.thg.logic.story.api.ProgressData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.PropertiesUtils;
/**
 * <p>进度数据的一个基本实现</p>
 * <p>以下为一个示例</p>
 * <pre>
 * c1=3
 * c2=2
 * c3=0
 * 01-02#1=0
 * 01-05#2=0
 * day=01-03
 * scene=2
 * dialog=13
 * </pre>
 * @author MyCapitaine
 *
 */
public class DefaultProgressData implements ProgressData {
	private ObjectMap<String, String> datas;
	
	public DefaultProgressData() {
		datas = new ObjectMap<String, String>();
		load();
	}
	public DefaultProgressData(String url) {
		if(url == null) {
			System.err.println("defaultprogressData create fail");
			return;
		}
		datas = new ObjectMap<String, String>();
		load(url);
	}
	
	@Override
	public boolean putFlag(String key, int num) {
		String s = datas.get(key);
		if(s != null) {
			datas.put(key, Integer.toString(num));
			return true;
		}
		return false;
	}

	@Override
	public int getFlag(String key) {
		String s = datas.get(key);
		if(s == null) return -1;
		try {
			return Integer.parseInt(s);
		}catch(Exception e) {
			System.err.println("progress get flag error");
		}
		return -1;
	}
	@Override
	public boolean putValue(String key, boolean plus) {
		String s = datas.get(key);
		if(s == null) return false;
		try {
			int i = Integer.parseInt(s);
			if(plus) datas.put(key, (++ i) + "");
			else if(i >= 1) datas.put(key, (-- i) + "");
			else return false;
			
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public int getValue(String key) {
		String s = datas.get(key);
		if(s == null) return -1;
		try {
			return Integer.parseInt(s);
		}
		catch(Exception e) {
			System.err.println("progress get value error");
		}
		return -1;
	}
	
	@Override
	public void load() {
		Reader fr = null;
		try {
			fr = Gdx.files.internal(Config.DEFAULT_PROGRESSDATA_URL).reader(THG.CHAR_SET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			PropertiesUtils.load(datas, fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String url) {
		Reader fr = null;
		try {
			fr = Gdx.files.external(THG.EXTRNAL_HEAD + url).reader(THG.CHAR_SET);
		} catch (Exception e) {
			load();
			return;
		}
		
		try {
			PropertiesUtils.load(datas, fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void save(String url) {
		Writer fw = null;
		try {
			fw = Gdx.files.external(THG.EXTRNAL_HEAD + url).writer(false, THG.CHAR_SET);
		}
		catch(Exception e) {
			System.err.println("DefaultProgressData.save() writer create fail");
			return;
		}
		
		try {
			PropertiesUtils.store(datas, fw, null);
		} catch (IOException e) {
			System.err.println("DefaultProgressData.save() store fail");
		}
		if(fw != null)
			try {
				fw.close();
			} catch (IOException e) {
			}
		
	}

	
	@Override
	public boolean match(ProgressData pd) {
		Iterator<Entry<String, String>> iter = datas.iterator();
		while(iter.hasNext()) {
			if(!pd.containsKey(iter.next().key))
				return false;
		}
		return true;
	}

	
	@Override
	public final boolean containsKey(String key) {
		return datas.containsKey(key);
	}

	@Override
	public String getDayStr() {
		return datas.get(DAY_STRING);
	}

	
	@Override
	public int getSceneNum() {
		String s = datas.get(SCENE_STRING);
		try {
			return Integer.parseInt(s);
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int getDialogNum() {
		String s = datas.get(DIALOG_STRING);
		try {
			return Integer.parseInt(s);
		}catch(Exception e) {
			return 0;
		}
	}
	@Override
	public boolean putTime(String dayStr, int sceneNum, int dialogNum) {
		boolean retu = true;
		if(dayStr != null) retu &= (datas.put(DAY_STRING, dayStr) == null);
		else return false;
		if(sceneNum >= 0) retu &= (datas.put(SCENE_STRING, Integer.toString(sceneNum)) == null);
		else return false;
		if(dialogNum >= 0) retu &= (datas.put(DIALOG_STRING, Integer.toString(dialogNum)) == null);
		else return false;
		return retu;
	}
	
	private static final String 
		DAY_STRING = "day",
		SCENE_STRING = "scene",
		DIALOG_STRING = "dialog";

	@Override
	public void putExtraInfo(String info) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
