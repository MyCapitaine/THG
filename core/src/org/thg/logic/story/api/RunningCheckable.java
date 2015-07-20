package org.thg.logic.story.api;
/**
 * 实现该接口的类可以被检查工作是否完成
 * @author MyCapitaine
 *
 */
public interface RunningCheckable {
	/**
	 * 检查是否仍在进行手头的任务
	 * @param byHand true 表明非自动状态下检查
	 * @return false 任务已完成
	 */
	public boolean isRunning(boolean byHand);
	
	
}
