package org.thg.logic;

import java.util.regex.Pattern;

import org.thg.ui.Config;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class THG {
//====THG============================================================================================
	/**
	 * 本地存储文件名
	 */
	public static final String EXTRNAL_HEAD = "TOUHOUGAL/";
	/**
	 * 编码格式
	 */
	public static final String CHAR_SET = "UTF-8";
//====Game==============================================================================
	public static final void setGame(Game g) { game = g; }
	public static final Game getGame() { return game; }
//====Font==================================================================================
	/**
	 * <p>获取font</p>
	 * <p>需要自己清理font</p>
	 * @param s 需要的font对应的文字 
	 * @return 对应的font
	 */
	public static BitmapFont getFont(String s) {
		return getFont(s, (int)FONT_SIZE, Color.WHITE);
	}
	
	
	public static BitmapFont getFont(String s, int size, Color color) {
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) size;
		if(s != null) parameter.characters = pattern.matcher(s).replaceAll("");
		parameter.color = color;
		BitmapFont font = generator.generateFont(parameter);
		BitmapFontData data = font.getData();
		data.setScale(Config.scaleX, Config.scaleY);
		
		return font;
	}
	public static final float FONT_SIZE = 25;
	
	private static Pattern pattern = Pattern.compile("(.)(?=.*\\1)", Pattern.DOTALL);
	
	private static Game game = null;
	private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/default_font.ttf"));
}
