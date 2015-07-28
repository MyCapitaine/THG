package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_change_func;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.xml.internal.bind.v2.TODO;

/** 明显的场景切换效果 */
public class Effect_change extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
	
	/** 切换模式编号 */
	private final int model;
	/** 操作层级是否简单 */
	private final boolean simpleOperation;
	
	private final Image afterSceneBgImage;
	private final Texture afterSceneBgTexture;
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
		switch(model) {
		case 1 :
			simpleOperation = false;
			break;
		case 2 :
		default :
			simpleOperation = true;
		}
//		TODO
		
		afterSceneBgImage = new Image();
		afterSceneBgImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(afterSceneBgImage);
		
		resetCount(1f, 8f, 48f);
		/*
		 * 复杂的操作需要初始化一系列texture，byte[]等，
		 * 简单的操作只需要前后两张image即可
		 */
		if(!simpleOperation)
			iniBytesAndSoOn(afterSceneBgTexture);
		else 
			afterSceneBgImage.setDrawable(new TextureRegionDrawable(new TextureRegion(afterSceneBgTexture)));
		
	}
	@Override
	public boolean act(float delta) {
		if(simpleOperation) return act_simple();
		else return act_complex();
	}
	
	private boolean act_simple() {
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			
			
			return true;
		}
		count();
		switch (model) {
		case 3:
			break;
		default:
			break;
		}
		
//		TODO
		return false;
	}
	private boolean act_complex() {
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			defaultEffectStage.bg.setDrawable(new TextureRegionDrawable(new TextureRegion(afterSceneBgTexture)));
			afterSceneBgImage.remove();
			if(changableTexture != null) changableTexture.dispose();
			changablePixmap.dispose();
			return true;
		}
		count();
		
		byte[] current;
		switch(model) {
//		case 2 :
//			current = Effect_change.effect_change_2(afterBgData, width, height, depth,
//					renderCount, TIME_LIMIT_COUNT);
//			break;
//		case 3 :
//			current = Effect_change.effect_change_3(afterBgData, width, height, depth,
//					renderCount, TIME_LIMIT_COUNT);
//			break;
//		case 4 :
//			current = Effect_change.effect_change_4(afterBgData, width, height, depth,
//					renderCount, TIME_LIMIT_COUNT);
//			break;
//		case 5 :
//			current = Effect_change.effect_change_5(afterBgData, width, height, depth,
//					renderCount, TIME_LIMIT_COUNT);
//			break;
//			TODO
		case 1 :
		default :
			current = Effect_change_func.effect_change_1(byteData, width, height, depth,
					renderCount, LIMIT_RENDER_COUNT);
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
