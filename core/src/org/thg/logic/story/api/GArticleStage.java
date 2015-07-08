package org.thg.logic.story.api;

public interface GArticleStage extends RunningCheckable {
	
	/**
	 * 设置场景的全部段落
	 * @param scene 场景数据
	 */
	public void setScene(GArticleScene aScene);
	/**
	 * 设置内心场景的当前段落
	 * @param dialog 段落
	 */
	public void setDialog(GDialog dialog);
	
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
