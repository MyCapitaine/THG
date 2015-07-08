package org.thg.ui;

import java.math.BigDecimal;

import org.thg.logic.THG;
import org.thg.logic.story.api.GDialog;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.api.RunningCheckable;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
/**
 * <p>大屏的文字显示框</p>
 * <p>实现与{@link GWordsFrame}类似</p>
 * @author MyCapitaine
 *
 */
public class GWordsWindow extends Actor implements RunningCheckable, Disposable {
	private BitmapFont font;
	private String text;
	private GDialog[] dialogs;
	private Texture bg;
	private float font_size;
	@SuppressWarnings("unused")
	private int rowWordsNum, colWordsNum;
	/**
	 * 对应至每段话为止段落字数显示的界限
	 */
	private float[] dialog_lengths;
	/**
	 * 此刻段落字数显示的界限
	 */
	private float current_dialog_length_limit;
	/**
	 * 此刻显示的字数
	 */
	private float current_dialogs_length_count;
	
	private float speed_show_words;
	
	/**
	 * 对话暂停的间隔渲染次数
	 */
	private float 
		interval_render_num_skipping = 5 * Config.dialogInterval,
		interval_render_num_normal = 60 * Config.dialogInterval;
	private float interval_render_count;
	
	/**
	 * 你不比为此设置宽高
	 */
	public GWordsWindow() {
		font_size = 22f;
		bg = new Texture(Config.WORDS_WINDOW_URL);
		setBounds((Config.SCREEN_WIDTH - Config.WORDS_WINDOW_WIDTH) / 2 * Config.scaleX,
				(Config.SCREEN_HEIGHT - Config.WORDS_WINDOW_HEIGHT) / 2 * Config.scaleY,
				Config.WORDS_WINDOW_WIDTH * Config.scaleX,
				Config.WORDS_WINDOW_HEIGHT * Config.scaleY);
	}
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(bg, getX(), getY(), getWidth(), getHeight());
		
		font.draw(batch, text.substring(0, (int)current_dialogs_length_count),
				getX() + WINDOW_PADDING * org.thg.ui.Config.scaleX,
				getY() + getHeight() - WINDOW_PADDING * org.thg.ui.Config.scaleY);
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		Screen s = THG.getGame().getScreen();
		if(!(s instanceof GGameController)) return;
		if(((GGameController)s).getSkipFlag()) {
			current_dialogs_length_count = current_dialog_length_limit;
			if(interval_render_count < interval_render_num_skipping) interval_render_count ++;
		}
		else {
			if(current_dialogs_length_count >= current_dialog_length_limit)
				interval_render_count ++;
			else
				current_dialogs_length_count += speed_show_words;
			
			
		}
	}
	
	
	@Override
	public boolean isRunning() {
		Screen s = THG.getGame().getScreen();
		if(!(s instanceof GGameController)) return false;
		return current_dialogs_length_count < current_dialog_length_limit ||
				interval_render_count < 
				(((GGameController)s).getSkipFlag() ? 
						interval_render_num_skipping : interval_render_num_normal);
	}
	
	private static final float WINDOW_PADDING = 20f;
	
	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		float f = (height - 2 * WINDOW_PADDING * org.thg.ui.Config.scaleY) / 
				(font_size * org.thg.ui.Config.scaleY);
		colWordsNum = new BigDecimal(f).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		float f = ((width - 2 * WINDOW_PADDING * org.thg.ui.Config.scaleX) /
				(font_size * org.thg.ui.Config.scaleX));
		rowWordsNum = new BigDecimal(f).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
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
	public void dispose() {
		if(bg != null) bg.dispose();
		if(font != null) font.dispose();
	}
	
	/**
	 * <p>初始化{@link this#text this#font this#dialog_lengths} </p>
	 * <p>在适当的地方加入换行</p>
	 * @param dialogs 设置的对话对象
	 */
	public void setDialogs(GDialog[] dialogs) {
		if(dialogs == null || dialogs.length == 0) return;
		this.dialogs = dialogs;
		dialog_lengths = new float[dialogs.length];
		
		StringBuffer textBuffer = new StringBuffer('\n');
		int i = 0, row_count_max = 2 * rowWordsNum;
		
		
		for(int m = 0; m < dialogs.length; m ++) {
			char[] charList = dialogs[m].getWords().toCharArray();
			i = 0;

			for(char c : charList) {
				//字符占半格，汉字占整格（，换行则重新计数）
				if(c >= ' ' && c <= 127) i ++;
				else if(c < ' ') i = 0;
				else i += 2;
				//对于读到一行的底端有3种情况讨论
				if(i < row_count_max) textBuffer.append(c); 
				else if(i == row_count_max) {
					textBuffer.append(c);
					textBuffer.append('\n');
					i = 0;
				}
				else {
					textBuffer.append('\n');
					textBuffer.append(c);
					i = 2;
				}
			}
			textBuffer.append('\n');
			textBuffer.append('\n');
			dialog_lengths[m] = textBuffer.length();
			
		}
		text = textBuffer.toString();
		font = THG.getFont(text, (int)font_size, Color.WHITE);
		
		speed_show_words = Config.showWordsSpeed / 60f;
	}
	/**
	 * <p><b>请传入之前用于初始化的同一对象的dialog</b></p>
	 * <p><b>请传入之前用于初始化的同一对象的dialog，很重要所以说两遍</b></p>
	 * <p>设置当前读到的对话</p>
	 * <p>并适当的初始化计数器</p>
	 * @param dialog
	 */
	public void setDialog(GDialog dialog) {
		int n = 0;
		for(int i = 0; i < dialogs.length; i ++) {
			if(dialogs[i] == dialog) {
				n = i;
				break;
			}
		}
		if(n == 0)
			current_dialogs_length_count = 0;
		else 
			current_dialogs_length_count = dialog_lengths[n - 1];
		
		current_dialog_length_limit = dialog_lengths[n];
		
		interval_render_count = 0;
		
		
		
	}
	
}
