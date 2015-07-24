package org.thg.ui.sl;

import org.thg.logic.story.api.ProgressData;
import org.thg.logic.story.driver.progressdata.ProgressDataUtil;
import org.thg.ui.Config;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
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
		addActor(turnPage = new TurnPageWeight(this));
		
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
	void showPage(int pageNum) {
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
	
	
	
	
}


