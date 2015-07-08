package org.thg.logic.story.api;

import org.thg.logic.factorys.GStoryTreeFactory;

/**
 * <p>剧情树</p>
 * <p>用户构建剧情树</p>
 * <p>并且通过剧情树按日来控制剧情走向</p>
 * <p>具体构建方式见{@link GStoryTreeFactory}</p>
 * @author MyCapitaine
 *
 */
public interface GStoryTree {
	/**
	 * 存储剧情内容
	 * @param dayStr 前项日期字符
	 * @param otherPart 后项包含导向下一日的信息
	 */
	public void put(String dayStr, String otherPart);
	/**
	 * <p>在剧情树构建完毕的情况下</p>
	 * <p>根据日期字符来取得下一日的日期字符</p>
	 * <p>用户可按照一定规范来实现这个方法</p>
	 * @param dayStr 上一日字符
	 * @return 下一日字符
	 */
	public String getNextDay(String dayStr);
}
