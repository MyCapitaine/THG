package org.thg.ui.sl;

/**
 * 监听器
 * @author MyCapitaine
 *
 */
public interface SLSpeaker {
	/**
	 * @param dataPic 将设置监听的目标
	 * @param orderNum 档位序号
	 * @param haveData 该档位是否已经存在数据
	 */
	void setListener(DataPic dataPic, int orderNum, boolean haveData);
}