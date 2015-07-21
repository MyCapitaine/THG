package org.thg.ui.sl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Disposable;

/**
 * <p>一套用于存/读档显示当前档的组件</p>
 * <p>从存储区域读取已有档并显示</p>
 * <p>由存/读档界面实现监听</p>
 * @author MyCapitaine
 *
 */
public class GSLWeight extends Group implements Disposable {
	/**
	 * 最大页数
	 */
	static final int MAX_PAGE_NUM = 9;
	static final int ROW_NUM_PAGE = 4;
	static final int COL_NUM_PAGE = 4;
	/**
	 * 最大存档数
	 */
	static final int PROGRESS_NUM = MAX_PAGE_NUM * ROW_NUM_PAGE * COL_NUM_PAGE;
	private int current_page_num;

	private TurnPageWeight turnPage;
	
	private Image[][] dataImages;
	
	
	private HashMap<Integer, Texture> textures;
	
	GSLWeight() {
		current_page_num = 0;
		turnPage = new TurnPageWeight();
		textures = new HashMap<Integer, Texture>();
		dataImages = new Image[ROW_NUM_PAGE][COL_NUM_PAGE];
		for(int i = 0; i < ROW_NUM_PAGE; i ++) {
			for(int j = 0; j < COL_NUM_PAGE; j ++) {
				dataImages[i][j] = new Image();
//				dataImages[i][j].setPosition(x, y);
//				dataImages[i][j].setSize(width, height);
			}
		}
		
		
	}
	
	/**
	 * <p>显示指定页的内容，图片
	 * <p>并添加合适的监听
	 * @param pageNum 与之前相同时无反应
	 * 
	 */
	private void showPage(int pageNum) {
		
		
		
		
	}
	@Override
	public void dispose() {
		Iterator<Entry<Integer, Texture>> iter = textures.entrySet().iterator();
		while(iter.hasNext())
			iter.next().getValue().dispose();
		textures.clear();
		
		turnPage.dispose();
		
	}
	
	
	
	/**
	 * 专门用来翻页的组件，形如（1,2,3,4,5,6）
	 * @author MyCapitaine
	 *
	 */
	private class TurnPageWeight extends Group implements Disposable {
		ImageButton[] pages;
		private Texture tpTexture;
		
		TurnPageWeight() {
			pages = new ImageButton[MAX_PAGE_NUM];
			
			//监听pages，点击调用showPage
			
		}

		@Override
		public void dispose() {
			tpTexture.dispose();
		}
		
		
	}
	
}


interface SLListener {
	
}



