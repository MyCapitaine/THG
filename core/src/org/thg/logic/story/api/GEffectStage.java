package org.thg.logic.story.api;

public interface GEffectStage extends RunningCheckable {
	/**
	 * 设置舞台效果场景
	 * @param effectScene 效果场景
	 */
	public void setScene(GEffectScene effectScene);
	

	/**
	 * 设置背景图片编号
	 * @param bgNum 背景图片编号
	 */
	public void setBg(int bgNum);
	/**
	 * 设置背景音乐编号
	 * @param bgmNum 背景音乐编号
	 */
	public void setBgm(int bgmNum);
}
