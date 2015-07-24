package org.thg.ui.sl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.thg.logic.story.api.ProgressData;
import org.thg.logic.story.driver.progressdata.ProgressDataUtil;
import org.thg.ui.Config;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
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
	public static final int PROGRESS_NUM = MAX_PAGE_NUM * ROW_NUM_PAGE * COL_NUM_PAGE;
	private int current_page_num;
	
	private TurnPageWeight turnPage;
	
	private DataPic[][] datas;
	
	private Texture shadeTexture;
	private BitmapFont timeFont;
	private Texture nodataTexture;
	private Drawable nodataDrawable;
	private Drawable imageMissDrawable;
	
	private SLSpeaker speaker;
	
	GSLWeight(SLSpeaker speaker) {
		this.speaker = speaker;
		current_page_num = 0;
		addActor(turnPage = new TurnPageWeight());
		
//		timeFont = THG.getFont("0123456789/:", size, color); TODO
		
		nodataTexture = new Texture(Config.SL_MENU_NODATA_URL);
		nodataDrawable = new TextureRegionDrawable(new TextureRegion(nodataTexture));
		
		imageMissDrawable = nodataDrawable;
		
		shadeTexture = new Texture(Config.SL_MENU_SHADE_URL);
		Drawable shadeDrawable = new TextureRegionDrawable(new TextureRegion(shadeTexture));

		datas = new DataPic[ROW_NUM_PAGE][COL_NUM_PAGE];
		for(int i = 0; i < ROW_NUM_PAGE; i ++) {
			for(int j = 0; j < COL_NUM_PAGE; j ++) {
				datas[i][j] = new DataPic(shadeDrawable, timeFont);
				datas[i][j].setPosition(
						(Config.SL_PADDING + Config.SL_PIC_WIDTH * (j + 1) - (i % 2 == 0 ? 0 : 4 * Config.SL_PADDING)) * Config.scaleX,
						(Config.SCREEN_HEIGHT - (i + 1) * (Config.SL_PIC_HEIGHT + Config.SL_PADDING)) * Config.scaleY);
				datas[i][j].setSize(Config.SL_PIC_WIDTH * Config.scaleX, Config.SL_PIC_HEIGHT * Config.scaleY);
				addActor(datas[i][j]);
			}
		}
		
		showPage(0);
	}
	
	/**
	 * <p>显示指定页的内容，图片
	 * <p>并添加合适的监听
	 * 
	 */
	private void showPage(int pageNum) {
		current_page_num = pageNum;
		
		boolean[] haveData = ProgressDataUtil.checkDatas();
		Texture texture;
		ProgressData pd;
		for(int i = 0; i < ROW_NUM_PAGE; i ++) {
			for(int j = 0; j < COL_NUM_PAGE; j ++) {
				int num = current_page_num * ROW_NUM_PAGE * COL_NUM_PAGE + j * ROW_NUM_PAGE + i;
				if(haveData[num]) {
					if((texture = ProgressDataUtil.getImageTexture(num)) != null)
						datas[i][j].setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
					else datas[i][j].setDrawable(imageMissDrawable);
					try {
						if((pd = ProgressDataUtil.load(num)) != null)
							datas[i][j].setTime(Long.parseLong(pd.getExtraInfo()));
					}catch(Exception e) { System.err.println(e); }
				}
				else {
					datas[i][j].setDrawable(nodataDrawable);
				}
				
				speaker.setListener(datas[i][j], num, haveData[num]);
			}
		}
		
	}
	@Override
	public void dispose() {
		shadeTexture.dispose();
		if(timeFont != null) timeFont.dispose();
		turnPage.dispose();
		nodataTexture.dispose();
		
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
			
			tpTexture = new Texture(Config.SL_MENU_TP_BUTTON_URL);
			TextureRegion[][] tr = TextureRegion.split(tpTexture, tpTexture.getWidth() / 2, tpTexture.getHeight());
			ImageButtonStyle style = new ImageButtonStyle();
			style.imageUp = UiUtil.resize(tr[0][0], Config.SL_TP_WIDTH, Config.SL_TP_HEIGHT);
			style.imageDown = style.imageOver = style.imageChecked
					= UiUtil.resize(tr[0][1], Config.SL_TP_WIDTH, Config.SL_TP_HEIGHT);
			
			pages = new ImageButton[MAX_PAGE_NUM];
			for(int i = 0; i < MAX_PAGE_NUM; i ++) {
				pages[i] = new ImageButton(style);
				pages[i].setPosition(2 * Config.SL_PADDING * Config.scaleX,
						(Config.SL_PADDING + (10 - i) * (Config.SL_PADDING + Config.SL_TP_HEIGHT)) * Config.scaleY);
				pages[i].setSize(Config.SL_TP_WIDTH * Config.scaleX, Config.SL_TP_HEIGHT * Config.scaleY);
				addActor(pages[i]);
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
			if(bf != null) bf.dispose();
		}
		
		
	}
	
}

/**
 * 数据的图像
 * @author MyCapitaine
 *
 */
class DataPic extends Image {
	/** 是否绘制阴影 */
	boolean drawshade;
	/** 阴影的drawable */
	Drawable shade;
	String timeStr;
	private BitmapFont timeFont;
	
	public DataPic(Drawable shade, BitmapFont timeFont) {
		this.shade = shade;
		this.timeFont = timeFont;
		drawshade = true;
		addListener(new InputListener() {
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				drawshade = false;
			}
			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				drawshade = true;
			}
		});
	}
	/** 设置存档上面显示的时间 */
	public void setTime(long time) {
		GregorianCalendar g = new GregorianCalendar();
		g.setTimeInMillis(time);
		timeStr = (1900 + g.get(Calendar.YEAR)) + "/" + ((g.get(Calendar.MONTH) + 1) < 10 ? ("0" + (g.get(Calendar.MONTH) + 1)) : (g.get(Calendar.MONTH) + 1))
				+ "/" + (g.get(Calendar.DAY_OF_MONTH) < 10 ? ("0" + g.get(Calendar.DAY_OF_MONTH)) : g.get(Calendar.DAY_OF_MONTH))
				+ "\n" + "  " + g.get(Calendar.HOUR_OF_DAY) + ":" + g.get(Calendar.MINUTE)
				+ ":" + g.get(Calendar.SECOND);
		
	}
	
	/**
	 * 清除除构造时产生的以外全部listener
	 */
	public void clearInputListeners() {
		Array<EventListener> lis = getListeners();
		if(lis.size <= 1) return;
		EventListener l = lis.get(0);
		lis.clear();
		lis.add(l);
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if(drawshade && shade != null)
			shade.draw(batch, getX() + getImageX(), getY() + getImageY(),
					getImageWidth() * getScaleX(), getImageHeight() * getScaleY());
//		if(time != null) 		TODO
//			timeFont.draw(batch, str, x, y)
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
	 * @param orderNum 档位序号
	 * @param haveData 该档位是否已经存在数据
	 */
	void setListener(DataPic dataPic, int orderNum, boolean haveData);
}



