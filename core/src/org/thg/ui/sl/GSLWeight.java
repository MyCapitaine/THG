package org.thg.ui.sl;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 * <p>一套用于存/读档显示当前档的组件</p>
 * <p>从存储区域读取已有档并显示</p>
 * <p>由存/读档界面实现监听</p>
 * @author MyCapitaine
 *
 */
public class GSLWeight extends Group {
	/**
	 * 最大页数
	 */
	private static final int MAX_PAGE_NUM = 9;
	private static final int ROW_NUM_PAGE = 4;
	private static final int COL_NUM_PAGE = 4;
	/**
	 * 最大存档数
	 */
	static final int PROGRESS_NUM = MAX_PAGE_NUM * ROW_NUM_PAGE * COL_NUM_PAGE;
	private int current_page_num;

	private TurnPageWeight turnPage;
	/**
	 * 可点击的档
	 */
	private Image[][] pds;
//	/**
//	 * 档下面的背景，大概可以看到一个框
//	 */
//	private ImageButton[][] pd_bg;
	
	private SLListener listener;
	
	GSLWeight(SLListener sll) {
		current_page_num = 0;
		listener = sll;
		
		
		listener.resetListeners(showCurrentPage(), current_page_num);
	}
	
	/**
	 * <p>搜索目录下特定的存档文件</p>
	 * <p></p>
	 * 
	 * @return boolean数组对应当页的存档数组，为true表明此处有存档
	 */
	private boolean[][] showCurrentPage() {
		return null;
	}
	
	
	/**
	 * 专门用来翻页的组件，形如（1,2,3,4,5,6）
	 * @author MyCapitaine
	 *
	 */
	private class TurnPageWeight extends Group {
		ImageButton[] pages;
		
		public TurnPageWeight() {
			pages = new ImageButton[MAX_PAGE_NUM];
			
			
			
		}
		
		
	}
	private static final float TP_BUTTON_DELTA_Y = 5f;
	private static final float TP_BOTTOM_BUTTON_X = 20f, TP_BOTTOM_BUTTON_Y = 40;
	
	
	public interface SLListener {
		
//		void setSpeakers();
		void resetListeners(boolean[][] bv, int pageNum);
		
	}
	
	
//	class 
}
