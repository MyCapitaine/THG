package org.thg.logic.story.driver.stage.effect;

import org.thg.logic.story.driver.stage.DefaultEffectStage;

/** 模糊，也可以解模糊 */
public class Effect_misty extends EffectAction {
	private DefaultEffectStage defaultEffectStage;
//	byte[] bl;
//	ByteBuffer bb;
//	Pixmap pixmap;
	/**
	 * @param params 参数说明
	 * <p> 是否回复
	 * 
	 */
	public Effect_misty(DefaultEffectStage stage, String... params) {
		defaultEffectStage = stage;
		
//		pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, 800, 600);
//		bb = pixmap.getPixels();
//		bl = new byte[bb.capacity()];
//		bb.get(bl);
//		bb.clear();
//		bb.put(Effect_misty_func.effect_misty(bl, 800, 600, 4, 0, 0));
//		bb.clear();
//		Texture t = new Texture(pixmap);
//		bg.setDrawable(new TextureRegionDrawable(new TextureRegion(t)));
		
	}
	@Override
	public boolean act(float delta) {
		// TODO Auto-generated method stub
		if(!isRunning) return true;
		
		
		
		return false;
	}
}
