package org.thg.logic.story.api;
/**
 * 实现该接口的类可以被检查工作是否完成
 * @author MyCapitaine
 *
 */
public interface RunningCheckable {
	/**
	 * 是否仍在进行手头的任务
	 * @return false 任务已完成
	 */
	public boolean isRunning();
}
