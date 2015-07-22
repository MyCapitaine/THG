package org.thg.logic.story.driver.progressdata;

import org.thg.logic.THG;
import org.thg.logic.story.api.ProgressData;
import org.thg.ui.sl.GSLWeight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
/**
 * 包含存读档的一系列操作和查看方法
 * @author MyCapitaine
 *
 */
public class ProgressDataUtil {
	private static final String DATA_URL_HEADER = "savedata/progress_";
	private static final String DATA_URL_TAIL = ".pd";
	private static final String IMAGE_URL_HEADER = DATA_URL_HEADER;
	private static final String IMAGE_URL_TAIL = ".png";
	
	private static FileHandle[] progressDatas = null;
	private static FileHandle[] images = null;
	
	/** 返回所有存档数据是否存在的信息 */
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
	
	public static boolean saveData(ProgressData data, int num) {
		
		
		return true;
	}
	
//	public static Texture
	
	/** 获取对应序号的存档 */
	public static ProgressData load(int num) {
		return new DefaultProgressData(DATA_URL_HEADER + num + DATA_URL_TAIL);
	}
	
	@Deprecated
	public static ProgressData load(String url) {
		return new DefaultProgressData(url);
	}
	/** 获取默认，即一个新的存档 */
	public static ProgressData load() {
		return new DefaultProgressData();
	}
	
	
	
}
