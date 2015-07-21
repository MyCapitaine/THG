package org.thg.ui.sl;

import java.util.ArrayList;

import org.thg.logic.THG;
import org.thg.ui.Config;
import org.thg.ui.GMainMenu;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Disposable;
/**
 * <p>存取界面共有的一些无关紧要的组件
 * <p>标签，返回键之类
 * @author MyCapitaine
 *
 */
public class OtherWeight extends Group implements Disposable {
	Image bg;
//	Image slLabel;
	ImageButton returnButton;
//	Image logo;
	
	private ArrayList<Disposable> disList;
	private Disposable disBuffer;
	
	private Screen toBeDisposed, toBeSet;
	
	private boolean isLoad;
	
	/**
	 * 
	 * @param screenToBeDisposed 返回时销毁的screen
	 * @param screenToBeSet 返回时设置的screen，为null时默认为{@link GMainMenu}
	 */
	OtherWeight(Screen screenToBeDisposed, Screen screenToBeSet) {
		if(screenToBeDisposed instanceof GLoadMenu) isLoad = true;
		else isLoad = false;
		toBeDisposed = screenToBeDisposed;
		toBeSet = screenToBeSet;
		
		disList = new ArrayList<Disposable>();
		
//背景
		disBuffer = new Texture(Config.SL_MENU_BG_URL);
		disList.add(disBuffer);
		bg = new Image((Texture)disBuffer);
		bg.setSize(Config.SCREEN_WIDTH * Config.scaleX, Config.SCREEN_HEIGHT * Config.scaleY);
		
		
//返回键
		disBuffer = new Texture(Config.SL_MENU_RETURN_BUTTON_URL);
		disList.add(disBuffer);
		 TextureRegion[][] tr = TextureRegion.split((Texture)disBuffer, Config.UI_BUTTON_WIDHT, Config.UI_BUTTON_HEIGHT);
		returnButton = new ImageButton(
				UiUtil.resize(tr[0][0]),
				UiUtil.resize(tr[0][1]));
		returnButton.getStyle().imageOver = UiUtil.resize(tr[0][2]);
		returnButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				THG.getGame().setScreen(toBeSet == null ? new GMainMenu() : toBeSet);
				toBeDisposed.dispose();
				return true;
			}
		});
		returnButton.setPosition(600 * Config.scaleX, 30 * Config.scaleY);
		
//		slLabel = new Image();
//		TODO
		
		
		
		addActor(bg);
//		addActor(slLabel);
		addActor(returnButton);
	}

	@Override
	public void dispose() {
		for(Disposable d : disList)
			d.dispose();
	}
	
	

}
