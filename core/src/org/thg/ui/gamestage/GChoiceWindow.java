package org.thg.ui.gamestage;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.thg.logic.THG;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.api.ProgressData;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.ui.Config;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.utils.Disposable;

public class GChoiceWindow extends Group implements Disposable {
	private LabelButton[] buttons;
	private Stage gameStage;
	private GChoiceWindow choice;
	private BitmapFont font;
	private Image bg;
	
	private Disposable disBuffer;
	private ArrayList<Disposable> disList;
	
	private int rowWordsNum;
	
	public GChoiceWindow(String choiceStr, Stage s) {
		String[] sl = choiceStr.split(CHOICE_SPLIT_SIGN);
		if(sl.length <= 1) { //第一项是选项标记，最少也得有1个选项
			System.err.println("choice info error");
			return;
		}
		
		
		
		disList = new ArrayList<Disposable>();
		gameStage = s;
		choice = this;
		font = THG.getFont(choiceStr, (int)Config.CHOICE_FONT_SIZE, Color.BLACK);
		rowWordsNum = new BigDecimal((Config.CHOICE_BUTTON_WIDTH - 2 * Config.CHOICE_BUTTON_PADDING) / Config.CHOICE_FONT_SIZE).
				setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		

		disBuffer = new Texture(Config.CHOICE_WINDOW_BG_URL);
		bg = new Image((Texture) disBuffer);
		addActor(bg);
		
		setPositions(iniButtons(sl));
		
		
		Screen screen = THG.getGame().getScreen();
		if(!(screen instanceof GGameController)) return;
		((GGameController)screen).setIsChoosing(true);
		
		
		
	}
	/**
	 * 初始化按钮内容以及长宽（不包括设置位置）
	 * @param sl 包含选项标记和选项信息的字符串数组
	 * @return 按钮高度的数组
	 */
	private float[] iniButtons(String[] sl) {
		int buttonsNum = sl.length - 1;
		float[] heights = new float[buttonsNum];
		buttons = new LabelButton[buttonsNum];
		
		ImageButtonStyle ibs = new ImageButtonStyle();
		disBuffer = new Texture(Config.CHOICE_BUTTON_URL);
		TextureRegion[][] tr = TextureRegion.split((Texture)disBuffer, (int)Config.CHOICE_BUTTON_WIDTH, (int)Config.CHOICE_BUTTON_HEIGHT_ONELINE);
		ibs.imageUp = UiUtil.resize(tr[0][0]);
		ibs.imageOver = UiUtil.resize(tr[0][1]);
		
		for(int i = 0; i < buttonsNum; i ++) {
			String[] extra = sl[i + 1].split(CHOICE_EXTRA_CONDITIONS_SIGN);
			
			char[] charlist = extra[0].toCharArray();
			StringBuffer buffer = new StringBuffer();
			int row = 0, row_count_max = 2 * rowWordsNum;
			int col = 0; //记录行数
			for(char c : charlist) {
				if(row == 0) col ++;
				if(c >= ' ' && c <= 127) row ++;
				else if(c < ' ') row = 0;
				else row += 2;
				if(row < row_count_max) buffer.append(c); 
				else if(row == row_count_max) {
					buffer.append(c);
					buffer.append('\n');
					row = 0;
				}
				else {
					buffer.append('\n');
					buffer.append(c);
					row = 2;
					col ++;
				}
			}
			buttons[i] = new LabelButton(ibs, buffer.toString(), font);
			buttons[i].setSize(Config.CHOICE_BUTTON_WIDTH * Config.scaleX,
					col * Config.CHOICE_BUTTON_HEIGHT_ONELINE * Config.scaleY);
			if(col > 1) {
				ImageButtonStyle specialstyle = new ImageButtonStyle();
				disBuffer = new Texture(Config.CHOICE_BUTTON_URL);
				tr = TextureRegion.split((Texture)disBuffer, (int)Config.CHOICE_BUTTON_WIDTH, (int)Config.CHOICE_BUTTON_HEIGHT_ONELINE);
				specialstyle.imageUp = UiUtil.resize(tr[0][0]);
				specialstyle.imageUp.setMinHeight(specialstyle.imageUp.getMinHeight() * col);
				specialstyle.imageOver = UiUtil.resize(tr[0][1]);
				specialstyle.imageOver.setMinHeight(specialstyle.imageOver.getMinHeight() * col);
				buttons[i].setStyle(specialstyle);
				buttons[i].setLineNum(col);
			}
			
			buttons[i].addListener(new PutProgress(sl[0], i, extra));
			addActor(buttons[i]);
			heights[i] = buttons[i].getHeight();
			
		}
		
		return heights;
	}
	/** 
	 * 设置所有组件的位置，也包括选项本身的位置
	 * @param buttonsHeight 按钮高度的数组
	 */
	private void setPositions(float[] buttonsHeight) {
		buttons[buttons.length - 1].setPosition(Config.CHOICE_WINDOW_PADDING * Config.scaleX,
				Config.CHOICE_WINDOW_PADDING * Config.scaleY);
		if(buttons.length > 1) {
			for(int i = buttons.length - 2; i >= 0; i --) {
				buttons[i].setPosition(Config.CHOICE_WINDOW_PADDING * Config.scaleX,
						buttons[i + 1].getHeight() + buttons[i + 1].getY() + Config.CHOICE_WINDOW_PADDING * Config.scaleY);
			}
		}
		
		bg.setSize(Config.CHOICE_WINDOW_BG_WIDTH * Config.scaleX,
				buttons[0].getHeight() + buttons[0].getY() + Config.CHOICE_WINDOW_PADDING * Config.scaleY);
		
		setSize(bg.getWidth(), bg.getHeight());
		setPosition((Config.SCREEN_WIDTH * Config.scaleX - getWidth()) / 2,
				(Config.SCREEN_HEIGHT * Config.scaleY - getHeight()) / 2);
	}
	

	private static final String CHOICE_SPLIT_SIGN = "(_+)";
	private static final String CHOICE_EXTRA_CONDITIONS_SIGN = "\\+";

	@Override
	public void dispose() {
		if(font != null) font.dispose();
		for(Disposable d : disList) 
			d.dispose();
	}
	
	class PutProgress extends InputListener {
		private String choiceSign;
		private int choiceNum;
		private String[] extra;
		/**
		 * 
		 * @param choiceSign 选项标记
		 * @param choiceNum 该选项的标记，以0为开头
		 * @param extra 第一项为选项字符，后面为附加条件
		 */
		public PutProgress(String choiceSign, int choiceNum, String[] extra) {
			this.choiceNum = choiceNum;
			this.choiceSign = choiceSign;
			this.extra = extra;
		}
		
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer,
				int button) {
			gameStage.getRoot().removeActor(choice);
			Screen screen = THG.getGame().getScreen();
			if(!(screen instanceof DefaultGameController)) return false;
			ProgressData pd = ((DefaultGameController)screen).getCurrentProgressData();
			if(pd == null) return false;
			pd.putFlag(choiceSign, (choiceNum + 1));
			for(int i = 1; i < extra.length; i ++) {
				pd.putValue(extra[i], true);
			}

			((DefaultGameController)screen).setIsChoosing(false);
			((DefaultGameController)screen).control(true);
			return true;
		}
		
	}
	
	
}





class LabelButton extends ImageButton {
	private String text;
	private BitmapFont font;
	private int lineNum;
	public LabelButton(ImageButtonStyle style, String text, BitmapFont font) {
		super(style);
		this.font = font;
		this.text = text;
		lineNum = 1;
	}
	
	public void setLineNum(int l) {
		if(l > 1) lineNum = l;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		float padding = (getHeight() - Config.CHOICE_FONT_SIZE * lineNum * Config.scaleY) * 2 / 3;
		font.draw(batch, text,
				getX() + Config.CHOICE_BUTTON_PADDING, getY() + getHeight() - padding);
	}
	
}