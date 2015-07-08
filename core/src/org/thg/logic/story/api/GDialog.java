package org.thg.logic.story.api;

public interface GDialog {
	/**
	 * 设置触发该对话的条件信息字符串
	 * @param cond 条件信息字符串
	 */
	public void setConditions(String cond);
	/**
	 * 用条件信息字符串来判定该对话是否会被触发
	 * @return true 触发
	 */
	public boolean triggered();
	
	/**
	 * 设置该对话的立绘号码
	 * @param pNum 立绘号码
	 */
	public void setCharactorPic(int pNum);
	/**
	 * 获取该对话的立绘号码
	 * @return 立绘号码
	 */
	public int getCharactorPic();
	/**
	 * 设置该对话的立绘位置
	 * @param position {@link org.thg.logic.story.driver.Config#CHARACTOR_PIC_POSITION_LEFT}
	 */
	public void setCharactorPicPosition(int position);
	/**
	 * 取得该对话的立绘位置
	 * @return {@link org.thg.logic.story.driver.Config#CHARACTOR_PIC_POSITION_LEFT}
	 */
	public int getCharactorPicPosition();
	/**
	 * 设置立绘特效
	 * @param eNum 特效编号
	 */
	public void setCharactorPicEffect(int eNum);
	/**
	 * 获取立绘特效编号
	 * @return 特效编号
	 */
	public int getCharactorPicEffect();
	/**
	 * 设置该对话的音频
	 * @param vNum 音频编号
	 */
	public void setVideo(int vNum);
	/**
	 * 获取该对话的音频
	 * @return 音频编号
	 */
	public int getVideo();
	/**
	 * 设置该对话的文字
	 * @param words 对话文字
	 */
	public void setWords(String words);
	/**
	 * 获取该对话的文字
	 * @return 对话文字
	 */
	public String getWords();
	/**
	 * 设置选项信息字符串
	 * @param choice 选项信息字符串
	 */
	public void setChoice(String choice);
	/**
	 * 获取选项信息字符串
	 * @return 选项信息字符串
	 */
	public String getChoice();
	/**
	 * 设置对话人物名字
	 * @param name 名字
	 */
	public void setName(String name);
	/**
	 * 获取人物名字
	 * @return
	 */
	public String getName();
	
}
