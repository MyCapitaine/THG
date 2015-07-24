package org.thg.logic.story.driver.progressdata;

import org.thg.logic.THG;
import org.thg.logic.story.api.ProgressData;
import org.thg.ui.sl.GSLWeight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
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
	
	private static FileHandle[] progressDataHandles = null;
	private static ProgressData[] progressDatas = new ProgressData[GSLWeight.PROGRESS_NUM];
	
	private static FileHandle[] imageHandles = null;
	/** 不销毁 */
	private static Texture[] imagesTexture = null;
	
	/** 返回所有存档数据是否存在的信息 */
	public static boolean[] checkDatas() {
		if(progressDataHandles == null) {
			progressDataHandles = new FileHandle[GSLWeight.PROGRESS_NUM];
			for(int i = 0; i < GSLWeight.PROGRESS_NUM; i ++) {
				progressDataHandles[i] = Gdx.files.external(THG.EXTRNAL_HEAD + 
						DATA_URL_HEADER + i + DATA_URL_TAIL);
			}
		}
		boolean[] checkResult = new boolean[GSLWeight.PROGRESS_NUM];
		for(int i = 0; i < GSLWeight.PROGRESS_NUM; i ++) {
			checkResult[i] = progressDataHandles[i].exists() && !progressDataHandles[i].isDirectory();
		}
		return checkResult;
	}
	
	public static boolean saveData(Pixmap screenshot, ProgressData data, int num) {
		data.save(DATA_URL_HEADER + num + DATA_URL_TAIL);
		
		PixmapIO.writePNG(imageHandles[num], screenshot);
		//覆盖图片
		if(imagesTexture[num] != null) imagesTexture[num].dispose();
		imagesTexture[num] = new Texture(imageHandles[num]);
		return true;
	}
	
	public static Texture getImageTexture(int num) {
		if(num < 0 || num >= GSLWeight.PROGRESS_NUM) return null;
		if(imageHandles == null) {
			imageHandles = new FileHandle[GSLWeight.PROGRESS_NUM];
			for(int i = 0; i < GSLWeight.PROGRESS_NUM; i ++) {
				imageHandles[i] = Gdx.files.external(THG.EXTRNAL_HEAD + 
						IMAGE_URL_HEADER + i + IMAGE_URL_TAIL);
			}
		}
		if(imageHandles[num].exists()) {
			if(imagesTexture[num] != null) return imagesTexture[num];
			return imagesTexture[num] = new Texture(imageHandles[num]);
		}
		else return null;
		
	}
	
	/** 获取对应序号的存档 */
	public static ProgressData load(int num) {
		if(progressDatas[num] == null) 
			progressDatas[num] = load(DATA_URL_HEADER + num + DATA_URL_TAIL);
		if(!progressDatas[num].match(defaultProgressData)) return null;
		
		return progressDatas[num].copy();
	}
	
	private static ProgressData load(String url) {
		return new DefaultProgressData(url);
	}
	private static ProgressData defaultProgressData = new DefaultProgressData();
	/** 获取默认，即一个新的存档 */
	public static ProgressData load() {
		return defaultProgressData.copy();
	}
	
	
	
}
