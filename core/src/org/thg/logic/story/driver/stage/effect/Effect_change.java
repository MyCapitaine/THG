package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_change_func;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 明显的场景切换效果 */
public class Effect_change extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	
	/** 切换模式编号 */
	private final int model;
	
	private final Image afterSceneBgImage;
	private final Texture afterSceneBgTexture;
	
	private boolean lock;
	/**
	 * @param params 参数说明
	 * <p> 切换后的背景编号	 001, 002...
	 * <p> 切换模式		 001, 002...
	 * 
	 */
	public Effect_change(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		// 获取新背景，构造Image对象
		afterSceneBgTexture = ResourceFactory.getBg(Integer.parseInt(params[0]));
		//解析模式
		model = Integer.parseInt(params[1]);
//		TODO
		
		lock = false;
		
		afterSceneBgImage = new Image();
		afterSceneBgImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(afterSceneBgImage);
		
		
		
		switch(model) {
		case 1 :
		case 2 :
			resetCount(1f, 8f, 48f);
			defaultEffectStage.bg.setVisible(false);
			iniBytesAndSoOn(defaultEffectStage.bgTexture);
			break;
		case 3 :
			resetCount(1f, 20f, 80f);
			afterSceneBgImage.setDrawable(new TextureRegionDrawable(new TextureRegion(afterSceneBgTexture)));
			break;
		default :
		}
		
	}
	@Override
	public boolean act(float delta) {
		switch(model) {
		case 1 :
		case 2 :
			return act_complex();
		case 3 :
		default :
			return act_simple();
		}
	}
	/**
	 * 不涉及底层图片操作的行为
	 */
	private boolean act_simple() {
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			defaultEffectStage.bg.remove();
			afterSceneBgImage.setVisible(true);
			afterSceneBgImage.getColor().a = 1f;
			return true;
		}
		count();
		
		switch (model) {
		case 3:
			Effect_change_func.effect_change_3(defaultEffectStage.bg, afterSceneBgImage,
					renderCount, LIMIT_RENDER_COUNT);
			break;
		default:
		}
		
//		TODO
		return false;
	}
	/**
	 * 涉及底层图片操作的行为
	 */
	private boolean act_complex() {
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			defaultEffectStage.bg.setDrawable(new TextureRegionDrawable(new TextureRegion(afterSceneBgTexture)));
			defaultEffectStage.bg.setVisible(true);
			afterSceneBgImage.remove();
			if(changableTexture != null) changableTexture.dispose();
			changablePixmap.dispose();
			return true;
		}
		count();
		
		byte[] current;
		switch(model) {
		case 1 :
			if(!lock && renderCount / LIMIT_RENDER_COUNT >= 0.5) {
				lock = true;
				if(changableTexture != null) changableTexture.dispose();
				changablePixmap.dispose();
				iniBytesAndSoOn(afterSceneBgTexture);
			}
			current = Effect_change_func.effect_change_1(byteData, width, height, depth,
					renderCount, LIMIT_RENDER_COUNT);
			break;
		case 2 :
			if(!lock && renderCount / LIMIT_RENDER_COUNT >= 0.5) {
				lock = true;
				if(changableTexture != null) changableTexture.dispose();
				changablePixmap.dispose();
				iniBytesAndSoOn(afterSceneBgTexture);
			}
			
			current = Effect_change_func.effect_change_2(byteData, width, height, depth,
					renderCount, LIMIT_RENDER_COUNT);
			break;
		default :
			current = new byte[0];
		}
		
		pixmapByte.put(current);
		pixmapByte.clear();
		if(changableTexture != null) changableTexture.dispose();
		changableTexture = new Texture(changablePixmap);
		afterSceneBgImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
		
		return false;
	}
	
	
	@Override
	public boolean isRunning(boolean byHand) {
		if(byHand) {
			if(renderCount >= LIMIT_RENDER_COUNT) return false;
			renderCount = LIMIT_RENDER_COUNT;
			return true;
		}
		
		return super.isRunning(byHand);
	}
	
	
}
