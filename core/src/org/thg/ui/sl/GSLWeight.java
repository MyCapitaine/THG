package org.thg.ui.sl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
	
	private DataPic[][] datas;
	private boolean[][] haveData;
	
	private HashMap<Integer, Texture> textures;
	private Texture lightTexture;
	
	private SLSpeaker speaker;
	
	GSLWeight(SLSpeaker speaker) {
		this.speaker = speaker;
		current_page_num = 0;
		turnPage = new TurnPageWeight();
		textures = new HashMap<Integer, Texture>();
		datas = new DataPic[ROW_NUM_PAGE][COL_NUM_PAGE];
		
//		lightTexture = new Texture();
		Drawable lightDrawable = new TextureRegionDrawable(new TextureRegion(lightTexture));
		
		
		for(int i = 0; i < ROW_NUM_PAGE; i ++) {
			for(int j = 0; j < COL_NUM_PAGE; j ++) {
				datas[i][j] = new DataPic(lightDrawable);
//				datas[i][j].setPosition(x, y);
//				datas[i][j].setSize(width, height);
			}
		}
		
		haveData = new boolean[ROW_NUM_PAGE][COL_NUM_PAGE];
		
	}
	
	/**
	 * <p>显示指定页的内容，图片
	 * <p>并添加合适的监听
	 * 
	 */
	private void showPage(int pageNum) {
		
		//设置图片
		
		//set haveData[][]
		
		for(int i = 0; i < ROW_NUM_PAGE; i ++) {
			for(int j = 0; j < COL_NUM_PAGE; j ++) {
				speaker.setListener(datas[i][j], i * COL_NUM_PAGE + j, haveData[i][j]);
			}
		}
		
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
		private BitmapFont bf;
//		private BitmapFont bf_another_color;
		
		TurnPageWeight() {
//			bf = THG.getFont("0123456789", size, color);
			
			
			pages = new ImageButton[MAX_PAGE_NUM];
			for(int i = 0; i < MAX_PAGE_NUM; i ++) {
//				pages[i] = new ImageButton(null);
//				pages[i].setPosition(x, y);
//				pages[i].setSize(width, height);
			}
			
			for(int i = 0; i < MAX_PAGE_NUM; i ++) {
				final int j = i;
				pages[j].addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						showPage(j);
						for(int m = 0; m < MAX_PAGE_NUM && m != j; m ++) {
							pages[m].setChecked(false);
						}
						//TODO 自己的checkover情况
						
						return true;
					}
				});
			}
			
			
			
		}
		
		@Override
		public void draw(Batch batch, float parentAlpha) {
//			bf.draw(batch, str, x, y)
			super.draw(batch, parentAlpha);
		}

		@Override
		public void dispose() {
			tpTexture.dispose();
			bf.dispose();
		}
		
		
	}
	
}

/**
 * 数据的图像
 * @author MyCapitaine
 *
 */
class DataPic extends Image {
	/** 是否绘制光泽 */
	boolean drawLight;
	/** 光泽的drawable */
	Drawable light;
	public DataPic(Drawable light) {
		this.light = light;
		drawLight = false;
		addListener(new InputListener() {
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				drawLight = true;
			}
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				drawLight = false;
			}
		});
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(drawLight && light != null)
			light.draw(batch, getX() + getImageX(), getY() + getImageY(),
					getImageWidth() * getScaleX(), getImageHeight() * getScaleY());
	}
}



/**
 * 监听器
 * @author MyCapitaine
 *
 */
interface SLSpeaker {
	/**
	 * @param dataPic 将设置监听的目标
	 * @param OrderNum 档位序号
	 * @param haveData 该档位是否已经存在数据
	 */
	void setListener(DataPic dataPic, int OrderNum, boolean haveData);
}



