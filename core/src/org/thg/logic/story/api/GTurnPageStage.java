package org.thg.logic.story.api;
/**
 * 切换日时显示的页面
 * @author MyCapitaine
 *
 */
public interface GTurnPageStage extends RunningCheckable {
	
//	public void setBg(int bNum);
	
	/**
	 * 设置下一日的日期字符
	 * @param dayStr 日期字符
	 */
	public void setNextDayStr(String dayStr);

}
