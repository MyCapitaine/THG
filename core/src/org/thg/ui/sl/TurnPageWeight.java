package org.thg.ui.sl;

import org.thg.ui.Config;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.utils.Disposable;


/**
 * 专门用来翻页的组件，形如（1,2,3,4,5,6）
 * @author MyCapitaine
 *
 */
public class TurnPageWeight extends Group implements Disposable {
	private GSLWeight gslWeight;
	ImageButton[] pages;
	private Texture tpTexture;
	private BitmapFont bf;
//	private BitmapFont bf_another_color;
	
	TurnPageWeight(GSLWeight gsl) {
		gslWeight = gsl;
//		bf = THG.getFont("0123456789", size, color);
		
		tpTexture = new Texture(Config.SL_MENU_TP_BUTTON_URL);
		TextureRegion[][] tr = TextureRegion.split(tpTexture, tpTexture.getWidth() / 2, tpTexture.getHeight());
		ImageButtonStyle style = new ImageButtonStyle();
		style.imageUp = UiUtil.resize(tr[0][0], Config.SL_TP_WIDTH, Config.SL_TP_HEIGHT);
		style.imageDown = style.imageOver = style.imageChecked
				= UiUtil.resize(tr[0][1], Config.SL_TP_WIDTH, Config.SL_TP_HEIGHT);
		
		pages = new ImageButton[GSLWeight.MAX_PAGE_NUM];
		for(int i = 0; i < GSLWeight.MAX_PAGE_NUM; i ++) {
			pages[i] = new ImageButton(style);
			pages[i].setPosition(2 * Config.SL_PADDING * Config.scaleX,
					(Config.SL_PADDING + (10 - i) * (Config.SL_PADDING + Config.SL_TP_HEIGHT)) * Config.scaleY);
			pages[i].setSize(Config.SL_TP_WIDTH * Config.scaleX, Config.SL_TP_HEIGHT * Config.scaleY);
			addActor(pages[i]);
		}
		
		for(int i = 0; i < GSLWeight.MAX_PAGE_NUM; i ++) {
			final int j = i;
			pages[j].addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x,
						float y, int pointer, int button) {
					gslWeight.showPage(j);
					for(int m = 0; m < GSLWeight.MAX_PAGE_NUM && m != j; m ++) {
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
//		bf.draw(batch, str, x, y)
		super.draw(batch, parentAlpha);
	}

	@Override
	public void dispose() {
		tpTexture.dispose();
		if(bf != null) bf.dispose();
	}
	
	
}