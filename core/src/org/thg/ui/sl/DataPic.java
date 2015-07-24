package org.thg.ui.sl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
/**
 * 数据的图像
 * @author MyCapitaine
 *
 */
public class DataPic extends Image {
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

