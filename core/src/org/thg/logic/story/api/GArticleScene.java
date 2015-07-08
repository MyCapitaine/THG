package org.thg.logic.story.api;
/**
 * 用于显示大段文章的场景
 * @author MyCapitaine
 *
 */
public interface GArticleScene extends GScene {
	/**
	 * 获取全部段落，即获取全部文本
	 * @return 段落数组
	 */
	public GDialog[] getDialogs();
	
	
}
