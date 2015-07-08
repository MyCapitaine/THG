package org.thg.logic.story.driver.stage;

import java.util.ArrayList;

import org.thg.logic.THG;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.api.GTurnPageStage;
import org.thg.logic.story.driver.Config;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
/**
 * 翻页专用舞台
 * @author MyCapitaine
 *
 */
public class DefaultTurnPageStage extends Stage implements GTurnPageStage {
	private Disposable disBuffer;
	private ArrayList<Disposable> disList;
	
	private Image bg;
	private Label dayStrLabel;
	
	private static final int INTERVAL_RENDER_NUM_NORMAL = (int)(60 * 1.5);
	private static final int INTERVAL_RENDER_NUM_SKIP = (int)(60 * 0.1);
	private int interval_render_count;
	
	public DefaultTurnPageStage(String dayStr) {
		dayStrLabel = null;
		disList = new ArrayList<Disposable>();
		
		disBuffer = new Texture(Config.TURN_PAGE_STAGE_BG_URL);
		disList.add(disBuffer);
		bg = new Image((Texture)disBuffer);
		bg.setSize(org.thg.ui.Config.SCREEN_WIDTH * org.thg.ui.Config.scaleX,
				org.thg.ui.Config.SCREEN_HEIGHT * org.thg.ui.Config.scaleY);
		addActor(bg);
		
		setNextDayStr(dayStr);
		
		interval_render_count = 0;
		
		
	}
	
	@Override
	public void act() {
		super.act();
		interval_render_count ++;
	}
	

	@Override
	public boolean isRunning() {
		Screen s = THG.getGame().getScreen();
		if(!(s instanceof GGameController)) return false;
		if(((GGameController)s).getSkipFlag() &&
				interval_render_count >= INTERVAL_RENDER_NUM_SKIP)
			return false;
		else if(interval_render_count >= INTERVAL_RENDER_NUM_NORMAL)
			return false;
		else
			return true;
	}


	@Override
	public void setNextDayStr(String dayStr) {
		disBuffer = THG.getFont(dayStr, 40, Color.BLACK);
		
		if(dayStrLabel == null) {
			dayStrLabel = new Label(dayStr, new Label.LabelStyle((BitmapFont) disBuffer, Color.BLACK));
			dayStrLabel.setSize(300 * org.thg.ui.Config.scaleX, 100 * org.thg.ui.Config.scaleY);
			addActor(dayStrLabel);
		}
		else {
			dayStrLabel.setText(dayStr);
			dayStrLabel.getStyle().font = (BitmapFont) disBuffer;
		}
		
	}

}
