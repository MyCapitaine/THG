package org.thg.logic.story.api;

import org.thg.logic.story.driver.Config;

/**
 * <p>占据游戏大部分时间的游戏舞台</p>
 * <p>可以设置游戏该状态下的参数</p>
 * 
 * @author MyCapitaine
 *
 */
public interface GGameStage extends RunningCheckable {
	/**
	 * 游戏舞台的渲染
	 */
	public void act();
	/**
	 * 
	 * @param dialog
	 */
	public void setDialog(GDialog dialog);
	/**
	 * 设置选项的信息字符串
	 * @param choiceStr 信息字符串
	 */
	public void setChoice(String choiceStr);
	/**
	 * 设置舞台播放的语音音频
	 * @param vNum 语音音频编号
	 */
	public void setVideo(int vNum);
	/**
	 * 设置舞台立绘
	 * @param cpNum 立绘号码
	 */
	public void setCharatorPic(int cpNum);
	/**
	 * <p>设置立绘位置</p>
	 * 
	 * @param position {@link Config}
	 */
	public void setCharactorPicPosition(int position);
	/**
	 * 设置立绘效果
	 * @param eNum 立绘效果编号
	 */
	public void setCharactorPicEffect(int eNum);
	/**
	 * 设置舞台背景音乐
	 * @param bgmNum 背景音乐号码
	 */
	public void setBgm(int bgmNum);
	/**
	 * 设置舞台背景
	 * @param bgNum 背景图片号码
	 */
	public void setBg(int bgNum);
	/**
	 * 设置舞台场景(背景图片)切换模式
	 * @param scm 切换模式
	 */
	public void setSceneChangeModel(GSceneChangeModel scm);
	/**
	 * 设置舞台文字显示模式
	 * @param swm 显示模式
	 */
	public void setShowWordsModel(GShowWordsModel swm);

}
