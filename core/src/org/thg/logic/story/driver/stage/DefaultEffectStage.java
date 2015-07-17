package org.thg.logic.story.driver.stage;

import java.nio.ByteBuffer;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GEffectScene;
import org.thg.logic.story.api.GEffectStage;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.logic.story.driver.stage.effectFunc.Effect_change_func;
import org.thg.logic.story.driver.stage.effectFunc.Effect_shake_func;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
/**
 * 一个效果场景的实现
 * @author MyCapitaine
 *
 */
public class DefaultEffectStage extends Stage implements GEffectStage {
	private EffectAction effect;
	private Image bg;
	private DefaultEffectStage defaultEffectStage;
	
	public DefaultEffectStage() {
		super();
		bg = new Image();
		bg.setSize(org.thg.ui.Config.SCREEN_WIDTH * org.thg.ui.Config.scaleX,
				org.thg.ui.Config.SCREEN_HEIGHT * org.thg.ui.Config.scaleY);
		addActor(bg);
		defaultEffectStage = this;
	}
	
	@Override
	public boolean isRunning() {
		return effect.isRunning();
	}

	@Override
	public void setScene(GEffectScene effectScene) {
		try {
			effect = Effect.getEffect(effectScene.getEffectNum()).
					createEffect(this, effectScene.getParams());
		}
		catch(Exception e) {
			effect = this.new Effect_unsupport();
			System.out.println("effect error or unsupport");
		}
		addAction(effect);
		
	}
	
	@Override
	public void setBg(int bgNum) {
		bg.setDrawable(UiUtil.resize(new TextureRegion(ResourceFactory.getBg(bgNum))));
	}
	@Override
	public void setBgm(int bgmNum) {
		DefaultGameController.setBgm(bgmNum);
	}
	
	

	private abstract class EffectAction extends Action {
		//act return true时将此标识置于否
		protected boolean isRunning = true;
		public boolean isRunning() { return isRunning; }
	}
	/** 显而易见 */
	private enum Effect {
		切换_1(1){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_change(params);
			}
		},
		晃动(11){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_shake(params);
			}
		},
		模糊(21){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_misty(params);
			}
		}, 
		睁眼(31){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_open_eyes(params);
			}
		}, 
		醉(41){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_drunk(params);
			}
		}, 
		闪瞎(51){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_blind(params);
			}
		}, 
		雪花(61){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_snowflake(params);
			}
		}, 
		聚焦(71){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_focus(params);
			}
		}, 
		放大(81){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_amplify(params);
			}
		};
		private Effect(int index) { this.index = index; }
		int index;
		abstract EffectAction createEffect(DefaultEffectStage stage, String... params);
		static Effect getEffect(int index) {
			Effect[] es = Effect.values();
			for(Effect e : es)
				if(e.index == index)
					return e;
			return null;
		}
		
	}
	/** 不支持的效果 */
	private class Effect_unsupport extends EffectAction {
		@Override
		public boolean act(float delta) { return true; }
		@Override
		public boolean isRunning() { return false; }
	}
	/** 默认位深 */
	private static final int DEFAULT_DEPTH = 4;
	
	
	private static final float EFFECT_CHANGE_TIME_LIMIT_COUNT = 60f;
	private static final float 
		EFFECT_CHANGE_SPEED_NORMAL = 1f,
		EFFECT_CHANGE_SPEED_SKIP = 10f;			
	/** 明显的场景切换，此为场景切换的第一种模式 */
	private class Effect_change extends EffectAction {
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
		public Effect_change(String... params) {
			model = Integer.parseInt(params[0]);
			// 获取新背景，构造Image对象
			afterSceneBgTexture = ResourceFactory.getBg(Integer.parseInt(params[1]));
			
			
			afterSceneBgImage = new Image();
			afterSceneBgImage.setSize(bg.getWidth(), bg.getHeight());
			defaultEffectStage.addActor(afterSceneBgImage);
			//获取参数
			width = afterSceneBgTexture.getWidth();
			height = afterSceneBgTexture.getHeight();
			depth = afterSceneBgTexture.getDepth() == 0 ? DEFAULT_DEPTH : afterSceneBgTexture.getDepth();
			//获取pixmap对象和byte[]数组
			TextureData td = afterSceneBgTexture.getTextureData();
			td.prepare();
			changablePixmap = td.consumePixmap();
			pixmapByteBuffer = changablePixmap.getPixels();
			afterBgData = new byte[pixmapByteBuffer.capacity()];
			pixmapByteBuffer.get(afterBgData);
			pixmapByteBuffer.clear();
			//一个texture缓存对象
			changableTexture = null;
			//计数器
			renderCount = 0f;
			
		}
		@Override
		public boolean act(float delta) {
			if(renderCount >= EFFECT_CHANGE_TIME_LIMIT_COUNT) {
				isRunning = false;
				bg.setDrawable(new TextureRegionDrawable(new TextureRegion(afterSceneBgTexture)));
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
//			case 2 :
//				current = Effect_change.effect_change_2(afterBgData, width, height, depth,
//						renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//				break;
//			case 3 :
//				current = Effect_change.effect_change_3(afterBgData, width, height, depth,
//						renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//				break;
//			case 4 :
//				current = Effect_change.effect_change_4(afterBgData, width, height, depth,
//						renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//				break;
//			case 5 :
//				current = Effect_change.effect_change_5(afterBgData, width, height, depth,
//						renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
//				break;
//				TODO
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

	
	private static final float 
		EFFECT_SHAKE_SPEED_NORMAL = 1f,
		EFFECT_SHAKE_SPEED_SKIP = 8f;	
	private static final float EFFECT_SHAKE_TIME_LIMIT_COUNT = 20f;
	/** 晃动  */
	private class Effect_shake extends EffectAction {
		private float renderCount = 0;
		public Effect_shake(String... params) {
			Effect_shake_func.setSeed(System.currentTimeMillis());
		}
		@Override
		public boolean act(float delta) {
			if(renderCount >= EFFECT_SHAKE_TIME_LIMIT_COUNT) {
				bg.setPosition(0, 0);
				isRunning = false;
				return true;
			}
			Screen s = THG.getGame().getScreen();
			if(s instanceof GGameController && ((GGameController)s).getSkipFlag())
				renderCount += EFFECT_SHAKE_SPEED_SKIP;
			else renderCount += EFFECT_SHAKE_SPEED_NORMAL;
			
			
			//TODO
			
			
			return false;
		}
	}
	
	/** 模糊，也可以解模糊 */
	private class Effect_misty extends EffectAction {
//		byte[] bl;
//		ByteBuffer bb;
//		Pixmap pixmap;
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_misty(String... params) {
			
//			pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, 800, 600);
//			bb = pixmap.getPixels();
//			bl = new byte[bb.capacity()];
//			bb.get(bl);
//			bb.clear();
//			bb.put(org.thg.logic.story.driver.stage.effectFunc.Effect_misty.effect_misty(bl, 800, 600, 4, 0, 0));
//			bb.clear();
//			Texture t = new Texture(pixmap);
//			bg.setDrawable(new TextureRegionDrawable(new TextureRegion(t)));
			
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			
			return false;
		}
	}
	
	
	
	/** 睁眼，并带上眨眼 */
	private class Effect_open_eyes extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_open_eyes(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			return false;
		}
	}
	
	/** 神志不清ing，醉了，视线模糊且晃晃悠悠 */
	private class Effect_drunk extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_drunk(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			return false;
		}
	}
	
	/** 狗眼闪瞎，中了闪光弹 */
	private class Effect_blind extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_blind(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			return false;
		}
	}
	
	/** 雪花，意识间歇ing */
	private class Effect_snowflake extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_snowflake(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			return false;
		}
	}
	
	/** 聚焦，模糊周围 */
	private class Effect_focus extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_focus(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			return false;
		}
	}
	
	/** 放大，也可以再缩小回去 */
	private class Effect_amplify extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_amplify(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			return false;
		}
	}

}





