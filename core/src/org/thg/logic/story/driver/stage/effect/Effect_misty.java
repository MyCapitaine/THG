package org.thg.logic.story.driver.stage.effect;

import java.nio.ByteBuffer;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.driver.stage.DefaultEffectStage;
import org.thg.logic.story.driver.stage.effectFunc.Effect_misty_func;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/** 模糊，也可以解模糊
 *  <p>关于减小计算量的方案
 *  <p>1.缩小图片后再进行模糊
 *  <p>2.用模糊模板和原图片进行alpha叠加
 *  <p>3.采用boxblurfilter，速度比高斯模糊快一些，具体快多少没有仔细研究
 *  <p>
 *  <p>我采用的是方案1
 */
public class Effect_misty extends EffectAction {
	private DefaultEffectStage defaultEffectStage;

	private final Image mistyImage;
	
	private static final int REDUCE = 3;
	/** true表明由模糊变清晰 */
	private final boolean back;
	/**
	 * @param params 参数说明
	 * <p> 有参即表明由模糊变清晰，无参默认模糊
	 * <p><b> 模糊算法因为计算量会导致阻塞，在不同平台上运行无法预测时间
	 * 
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
		
		//获取图片数据，并进行缩放
		int preWidth = defaultEffectStage.bgTexture.getWidth(),
				preHeight = defaultEffectStage.bgTexture.getHeight();
		width = preWidth / REDUCE;
		height = preHeight / REDUCE;
		
		TextureData td = defaultEffectStage.bgTexture.getTextureData();
		if(!td.isPrepared()) td.prepare();
		Pixmap prePixmap = td.consumePixmap();
		ByteBuffer preByteBuffer = prePixmap.getPixels();
		byte[] prebyteData = new byte[preByteBuffer.capacity()];
		preByteBuffer.get(prebyteData);
		preByteBuffer.clear();
		
		
		changablePixmap = new Pixmap(width, height, Format.RGBA8888);
		byteData = Effect_misty_func.reduce(prebyteData, preWidth, preHeight, REDUCE);
		pixmapByte = changablePixmap.getPixels();
		pixmapByte.put(byteData);
		pixmapByte.clear();
		
		prePixmap.dispose();
		
		changableTexture = null;
		
		resetCount(1.5f, 40f, 60f);
		
	}
	@Override
	public boolean act(float delta) {
		if(renderCount >= LIMIT_RENDER_COUNT) {
			isRunning = false;
			if(back) {
				defaultEffectStage.addActor(defaultEffectStage.bg);
				mistyImage.remove();
				if(changableTexture != null) changableTexture.dispose();
			}
			else {
				if(changableTexture != null) ResourceFactory.putBgBufferTexture(changableTexture);
			}
			changablePixmap.dispose();
			return true;
		}
		
		count();
		
		byte[] newData = Effect_misty_func.effect_misty(byteData, width, height,
				back ? LIMIT_RENDER_COUNT - renderCount : renderCount, LIMIT_RENDER_COUNT);
		pixmapByte.put(newData);
		pixmapByte.clear();
		if(changableTexture != null) changableTexture.dispose();
		changableTexture = new Texture(changablePixmap);
		mistyImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
		
		return false;
	}
}
