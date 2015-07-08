package org.thg.logic.story.api;

public interface GScene {
	/**
	 * 设置触发该场景的条件信息字符串
	 * @param cond 条件信息字符串
	 */
	public void setConditions(String cond);
	/**
	 * 用条件信息字符串来判定该场景是否会被触发
	 * @return true 触发
	 */
	public boolean triggered();
	/**
	 * 设置该场景会发生的对话
	 * @param dialogs 所有对话数据数组(默认按照时间顺序排列)
	 */
	public void setDialogs(GDialog[] dialogs);
	/**
	 * 获取该场景下一条可能的对话(且不论触发条件)
	 * @return 下一条对话对象
	 */
	public GDialog getNextDialog();
	/**
	 * 将场景内的对话按序号设置到某处
	 * @param num 序号
	 * @return 对应的对话
	 */
	public GDialog setDialogPosition(int num);
	/**
	 * 获取当前对话的位置
	 * @return 对话位置
	 */
	public int getPosition();
	
	/**
	 * 设置此场景下的背景音乐号码
	 * @param mNum 背景音乐号码
	 */
	public void setBgMusic(int mNum);
	/**
	 * 获取该场景的背景音乐号码
	 * @return 背景音乐号码
	 */
	public int getBgMusic();
	/**
	 * 设置该场景的背景号码
	 * @param bNum 背景图片号码
	 */
	public void setBackground(int bNum);
	/**
	 * 获取该场景的背景号码
	 * @return 背景图片号码
	 */
	public int getBackground();

}
