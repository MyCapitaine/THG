package org.thg.logic.story.driver.progressdata;

import org.thg.logic.THG;
import org.thg.logic.story.api.ProgressData;
import org.thg.ui.sl.GSLWeight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ProgressDataUtil {
	private static final String DATA_URL_HEADER = "savedata/progress_";
	private static final String DATA_URL_TAIL = ".pd";
	
	private static FileHandle[] progressDatas = null;
	
	public static boolean[] checkDatas() {
		if(progressDatas == null) {
			progressDatas = new FileHandle[GSLWeight.PROGRESS_NUM];
			for(int i = 0; i < GSLWeight.PROGRESS_NUM; i ++) {
				progressDatas[i] = Gdx.files.external(THG.EXTRNAL_HEAD + 
						DATA_URL_HEADER + i + DATA_URL_TAIL);
			}
		}
		boolean[] checkResult = new boolean[GSLWeight.PROGRESS_NUM];
		for(int i = 0; i < GSLWeight.PROGRESS_NUM; i ++) {
			checkResult[i] = progressDatas[i].exists() && !progressDatas[i].isDirectory();
		}
		return checkResult;
	}
	
	
	public static ProgressData load(int num) {
		return new DefaultProgressData(DATA_URL_HEADER + num + DATA_URL_TAIL);
	}
	
	@Deprecated
	public static ProgressData load(String url) {
		return new DefaultProgressData(url);
	}
	
	public static ProgressData load() {
		return new DefaultProgressData();
	}
	
	
	
}
