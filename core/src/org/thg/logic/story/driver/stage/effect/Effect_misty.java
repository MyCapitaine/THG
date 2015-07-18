package org.thg.logic.story.driver.stage.effect;

import java.nio.ByteBuffer;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_misty_func;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 模糊，也可以解模糊 */
public class Effect_misty extends EffectAction {
	private DefaultEffectStage defaultEffectStage;

	private final Pixmap changablePixmap;
	private final ByteBuffer pixmapByte;
	private final byte[] byteData;
	private Texture changableTexture;
	private final Image mistyImage;
	private final int width, height;
	
	private float renderCount;
	private static final float LIMIT_RENDER_COUNT = 60F;
	private static final float EFFECT_MISTY_SPEED_NORMAL = 5F,
			EFFECT_MISTY_SPEED_SKIP = 60f;
	/** true表明由模糊变清晰 */
	private final boolean back;
	/**
	 * @param params 参数说明
	 * <p> 有参即表明由模糊变清晰，无参默认模糊
	 * TODO
	 */
	public Effect_misty(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		defaultEffectStage.bg.remove();
		//移除原图片
		
		if(params == null || params.length == 0) back = false;
		else back = true;
		
		mistyImage = new Image();
		mistyImage.setSize(defaultEffectStage.bg.getWidth(), defaultEffectStage.bg.getHeight());
		defaultEffectStage.addActor(mistyImage);
		
		width = defaultEffectStage.bgTexture.getWidth();
		height = defaultEffectStage.bgTexture.getHeight();
		
		TextureData td = defaultEffectStage.bgTexture.getTextureData();
		td.prepare();
		changablePixmap = td.consumePixmap();
		pixmapByte = changablePixmap.getPixels();
		byteData = new byte[pixmapByte.capacity()];
		pixmapByte.get(byteData);
		pixmapByte.clear();
		
		changableTexture = null;
		
		renderCount = 0;
		
	}
	@Override
	public boolean act(float delta) {
		long i = System.currentTimeMillis();
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			if(back) {
				defaultEffectStage.addActor(defaultEffectStage.bg);
				mistyImage.remove();
				if(changableTexture != null) changableTexture.dispose();
			}
			else {
				if(changableTexture != null) ResourceFactory.putTrash(changableTexture);
			}
			changablePixmap.dispose();
			return true;
		}
		Screen s = THG.getGame().getScreen();
		if(s instanceof GGameController && ((GGameController)s).getSkipFlag())
			renderCount += EFFECT_MISTY_SPEED_SKIP;
		else renderCount += EFFECT_MISTY_SPEED_NORMAL;
		if(renderCount > LIMIT_RENDER_COUNT) renderCount = LIMIT_RENDER_COUNT;
		byte[] newData = Effect_misty_func.effect_misty(byteData, width, height,
				back ? LIMIT_RENDER_COUNT - renderCount : renderCount, LIMIT_RENDER_COUNT);
		pixmapByte.put(newData);
		pixmapByte.clear();
		if(changableTexture != null) changableTexture.dispose();
		changableTexture = new Texture(changablePixmap);
		mistyImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
		System.out.println(System.currentTimeMillis() - i);
		
		return false;
	}
}
