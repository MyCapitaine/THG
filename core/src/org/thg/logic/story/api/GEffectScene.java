/**
 * 
 */
package org.thg.logic.story.api;

/**
 * 用于表示画面特效的特殊场景
 * @author MyCapitaine
 *
 */
public interface GEffectScene extends GScene {
	/**
	 * 设置特殊场景效果号码
	 * @param en 效果号码
	 */
	public void setEffectNum(int en);
	/**
	 * 获取特殊场景效果号码
	 * @return 效果号码
	 */
	public int getEffectNum();
	/**
	 * 设置场景效果参数
	 * @param params 效果参数
	 */
	public void setParams(String... params);
	/**
	 * 获取场景效果参数
	 * @return 效果参数
	 */
	public String[] getParams();
	
	
	@Deprecated
	@Override
	public GDialog setDialogPosition(int num);
	@Deprecated
	@Override
	public int getPosition();
	@Deprecated
	@Override
	public void setDialogs(GDialog[] dialogs);
	@Deprecated
	@Override
	public GDialog getNextDialog();
	
	
}
