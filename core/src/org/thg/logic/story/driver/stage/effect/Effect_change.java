package org.thg.logic.story.driver.stage.effect;

import java.nio.ByteBuffer;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_change_func;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 明显的场景切换效果 */
public class Effect_change extends EffectAction {
	private static final float EFFECT_CHANGE_TIME_LIMIT_COUNT = 48f;
	private static final float 
		EFFECT_CHANGE_SPEED_NORMAL = 1f,
		EFFECT_CHANGE_SPEED_SKIP = 8f;		
	private DefaultEffectStage defaultEffectStage;
	
	/** 切换模式编号 */
	private final int model;
	
	private final Image afterSceneBgImage;
	private final Texture afterSceneBgTexture;
	private final byte[] afterBgData;
	private final Pixmap changablePixmap;
	private Texture changableTexture;
	private final ByteBuffer pixmapByteBuffer;
	private final int width, height, depth;
	private float renderCount;
	/**
	 * @param params 参数说明
	 * <p> 切换模式		 001, 002...
	 * <p> 切换后的背景编号	 001, 002...
	 * 
	 */
	public Effect_change(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		
		model = Integer.parseInt(params[0]);
		// 获取新背景，构造Image对象
		afterSceneBgTexture = ResourceFactory.getBg(Integer.parseInt(params[1]));
		
		
		afterSceneBgImage = new Image();
		afterSceneBgImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(afterSceneBgImage);
		//获取参数
		width = afterSceneBgTexture.getWidth();
		height = afterSceneBgTexture.getHeight();
		depth = afterSceneBgTexture.getDepth() == 0 ? 4 : afterSceneBgTexture.getDepth();
		//获取pixmap对象和byte[]数组
		TextureData td = afterSceneBgTexture.getTextureData();
		td.prepare();
		changablePixmap = td.consumePixmap();
		pixmapByteBuffer = changablePixmap.getPixels();
		afterBgData = new byte[pixmapByteBuffer.capacity()];
		pixmapByteBuffer.get(afterBgData);
		pixmapByteBuffer.clear();
		//一个texture对象
		changableTexture = null;
		//计数器
		renderCount = 0f;
		
	}
	@Override
	public boolean act(float delta) {
		if(renderCount >= EFFECT_CHANGE_TIME_LIMIT_COUNT) {
			isRunning = false;
			defaultEffectStage.bg.setDrawable(new TextureRegionDrawable(new TextureRegion(afterSceneBgTexture)));
			afterSceneBgImage.remove();
			if(changableTexture != null) changableTexture.dispose();
			changablePixmap.dispose();
			return true;
		}
		Screen s = THG.getGame().getScreen();
		if(s instanceof GGameController && ((GGameController)s).getSkipFlag())
			renderCount += EFFECT_CHANGE_SPEED_SKIP;
		else renderCount += EFFECT_CHANGE_SPEED_NORMAL;
		byte[] current;
		switch(model) {
//		case 2 :
//			current = Effect_change.effect_change_2(afterBgData, width, height, depth,
//					renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//			break;
//		case 3 :
//			current = Effect_change.effect_change_3(afterBgData, width, height, depth,
//					renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//			break;
//		case 4 :
//			current = Effect_change.effect_change_4(afterBgData, width, height, depth,
//					renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//			break;
//		case 5 :
//			current = Effect_change.effect_change_5(afterBgData, width, height, depth,
//					renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//			break;
//			TODO
		case 1 :
		default :
			current = Effect_change_func.effect_change_1(afterBgData, width, height, depth,
					renderCount > EFFECT_CHANGE_TIME_LIMIT_COUNT ? EFFECT_CHANGE_TIME_LIMIT_COUNT : renderCount, EFFECT_CHANGE_TIME_LIMIT_COUNT);
		}
		
		pixmapByteBuffer.put(current);
		pixmapByteBuffer.clear();
		if(changableTexture != null) changableTexture.dispose();
		changableTexture = new Texture(changablePixmap);
		afterSceneBgImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
		
		return false;
	}
}
