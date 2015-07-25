package org.thg.logic.story.api;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;

public interface GGameController extends Screen {
	/**
	 * <p>在渲染进程中调用该方法</p>
	 * <p>该方法负责调控计数器，加载和调度新的stage</p>
	 * <p>其行为往往取决于当前stage的类型</p>
	 * @param byHand true 代表通过手动调用
	 */
	public void control(boolean byHand);
	
	/**
	 * <p>设置进度文件</p>
	 * <p>读取进度并初始化游戏数据</p>
	 * @param pd 进度数据文件
	 */
	public void setProgressDataAndIni(ProgressData pd);
	/**
	 * 获取现在的进度数据文件
	 * @return 进度数据文件
	 */
	public ProgressData getCurrentProgressData();
	/**
	 * 设置剧情树
	 * @param gst 要设置的剧情树
	 */
	public void setStoryTree(GStoryTree gst);
	/**
	 * 设置下一日
	 * @return false 游戏结束，回到标题
	 */
	public boolean setNextDay();
	/**
	 * 设置下一场景
	 * @return false 该日结束，无下一场景
	 */
	public boolean setNextScene();
	/**
	 * 设置下一对话
	 * @return false 该场景结束，无下一对话
	 */
	public boolean setNextDialog();
	/**
	 * 确认存档操作以后调用的方法
	 * @param screenshot 该档的截屏
	 * @param progressNum 该档所存或覆盖的编号
	 */
	public void save(Pixmap screenshot, int progressNum);
//	public void backView();
	

}
