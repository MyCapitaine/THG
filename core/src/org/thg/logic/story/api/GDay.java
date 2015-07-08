package org.thg.logic.story.api;

public interface GDay {
	/**
	 * 设置日期字符
	 * @param dayStr 日期字符
	 */
	public void setDayStr(String dayStr);
	/**
	 * 获取日期字符
	 * @return 日期字符
	 */
	public String getDayStr();
	/**
	 * 设置该日场景数组
	 * @param scenes 该日场景
	 */
	public void setScenes(GScene[] scenes);
	/**
	 * 获取下一个场景
	 * @return 下一场景
	 */
	public GScene getNextScene();
	/**
	 * 将日内的场景按序号设置到某处
	 * @param num 序号
	 * @return 对应的场景
	 */
	public GScene setScenePosition(int num);
	/**
	 * 获取进行到的场景序号
	 * @return 此处场景序号
	 */
	public int getPosition();

}
