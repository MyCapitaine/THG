package org.thg.ui.gamestage;

import java.math.BigDecimal;

import org.thg.logic.THG;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.api.RunningCheckable;
import org.thg.ui.Config;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
/**
 * 文字显示框
 * @author MyCapitaine
 *
 */
public class GWordsFrame extends Actor implements RunningCheckable, Disposable {
	private BitmapFont font;
	private String text;
	private Texture bg;
	private float font_size;
	/**
	 * 横纵最多容纳的字数，随长宽的设定而改变
	 */
	@SuppressWarnings("unused")
	private float rowWordsNum, colWordsNum;
	/**
	 * 对话暂停的间隔渲染次数
	 */
	private float 
		interval_render_num_skipping = 2 * Config.dialogInterval,
		interval_render_num_normal = 60 * Config.dialogInterval;
	private float interval_render_count;
	
	/**
	 * 此刻显示的字数
	 */
	private float show_words_count;
	/**
	 * 总长
	 */
	private float text_length;
	/**
	 * 每帧增加的字数
	 */
	private float speed_show_words;
	
	/**
	 * 你不必为此设置宽高
	 */
	public GWordsFrame() {
		bg = new Texture(Config.WORDS_FRAME_URL);
		font_size = THG.FONT_SIZE;
		setBounds(
				Config.WORDS_FRAME_X * Config.scaleX,
				Config.WORDS_FRAME_Y * Config.scaleY,
				Config.WORDS_FRAME_WIDTH * Config.scaleX,
				Config.WORDS_FRAME_HEIGHT * Config.scaleY);
		
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		
//		计数器行为
		Screen s = THG.getGame().getScreen();
		if(!(s instanceof GGameController)) return;
		if(((GGameController)s).getSkipFlag()) {
			show_words_count = text_length - 1;
			if(interval_render_count < interval_render_num_skipping) interval_render_count ++;
		}
		else {
			if(show_words_count >= text_length - 1)
				interval_render_count ++;
			else 
				show_words_count += speed_show_words;
		}
	}
	
	public static final float FRAME_PADDING = 20f;
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, getX(), getY(), getWidth(), getHeight());
		
		font.draw(batch, text.substring(0, (int)show_words_count),
				getX() + FRAME_PADDING * org.thg.ui.Config.scaleX,
				getY() + getHeight() - FRAME_PADDING * org.thg.ui.Config.scaleY);
		
		
		
	};
	
	
	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		colWordsNum = (height - 2 * FRAME_PADDING * org.thg.ui.Config.scaleY) / 
				(font_size * org.thg.ui.Config.scaleY);
	}
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		rowWordsNum = ((width - 2 * FRAME_PADDING * org.thg.ui.Config.scaleX) /
				(font_size * org.thg.ui.Config.scaleX));
	}
	
	@Override
	public void setBounds(float x, float y, float width, float height) {
		super.setPosition(x, y);
		setSize(width, height);
	};
	
	@Override
	public void setSize(float width, float height) {
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public boolean isRunning() {
		Screen s = THG.getGame().getScreen();
		if(!(s instanceof GGameController)) return false;
		return show_words_count < text_length - 1 ||
				interval_render_count < 
				(((GGameController)s).getSkipFlag() ? 
						interval_render_num_skipping : interval_render_num_normal);
	}
	
	public void setText(String text) {
		if(font != null)font.dispose();
		if(text == null) text = "";
		else text = "「" + text + "」 ";
		font = THG.getFont(text, (int)font_size, Color.WHITE);
		char[] charlist = text.toCharArray();
		int i = 0, row_count_max = new BigDecimal(2 * rowWordsNum).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		StringBuffer buffer = new StringBuffer(row_count_max);
		for(char c : charlist) {
			//字符占半格，汉字占整格（，换行则重新计数）
			if(c >= ' ' && c <= 127) i ++;
			else if(c < ' ') i = 0;
			else i += 2;
			//对于读到一行的底端有2种情况讨论
			if(i < row_count_max) buffer.append(c); 
			else if(i == row_count_max) {
				buffer.append(c);
				buffer.append('\n');
				i = 0;
			}
			else {
				buffer.append('\n');
				buffer.append(c);
				i = 2;
			}
			
		}
		this.text = buffer.toString();
		
		show_words_count = 0f;
		text_length = this.text.length();
		speed_show_words = Config.showWordsSpeed / 60f;
		
		interval_render_count = 0f;
	}

	@Override
	public void dispose() {
		if(font != null) font.dispose();
		if(bg != null) bg.dispose();
	}
	
}
