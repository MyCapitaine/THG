package org.thg.logic.story.driver.stage;

import java.nio.ByteBuffer;

import org.thg.logic.THG;
import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GEffectScene;
import org.thg.logic.story.api.GEffectStage;
import org.thg.logic.story.api.GGameController;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.logic.story.driver.stage.effectFunc.Effect_change;
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
			effect = Effect.values()[effectScene.getEffectNum()].
					createEffect(this, effectScene.getParams());
		}
		catch(Exception e) {
			effect = this.new Effect_unsupport();
			System.out.println("effect error or unsupport");
		}
		
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
		切换_1{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_change_1(params);
			}
		},
		晃动{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_shake(params);
			}
		},
		模糊{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_misty(params);
			}
		}, 
		睁眼{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_open_eyes(params);
			}
		}, 
		醉{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_drunk(params);
			}
		}, 
		闪瞎{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_blind(params);
			}
		}, 
		雪花{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_snowflake(params);
			}
		}, 
		聚焦{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_focus(params);
			}
		}, 
		放大{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_amplify(params);
			}
		};
		public abstract EffectAction createEffect(DefaultEffectStage stage, String... params);
	}
	/** 不支持的效果 */
	private class Effect_unsupport extends EffectAction {
		@Override
		public boolean act(float delta) { return true; }
		@Override
		public boolean isRunning() { return false; }
	}
	
	
	private static final float TIME_LIMIT_COUNT = 60f;
	private static final float 
		EFFECT_CHANGE_SPEED_NORMAL = 1f,
		EFFECT_CHANGE_SPEED_SKIP = 10f;			
	/** 明显的场景切换，此为场景切换的第一种模式 */
	private class Effect_change_1 extends EffectAction {
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
		 * <p> 切换后的背景编号	 001, 002...
		 * 
		 */
		public Effect_change_1(String... params) {
			// 获取新背景，构造Image对象
			afterSceneBgTexture = ResourceFactory.getBg(Integer.parseInt(params[0]));
			afterSceneBgImage = new Image();
			afterSceneBgImage.setSize(bg.getWidth(), bg.getHeight());
			defaultEffectStage.addActor(afterSceneBgImage);
			//获取参数
			width = afterSceneBgTexture.getWidth();
			height = afterSceneBgTexture.getHeight();
			depth = afterSceneBgTexture.getDepth();
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
			if(renderCount >= TIME_LIMIT_COUNT) {
				isRunning = true;
				afterSceneBgImage.remove();
				if(changableTexture != null) changableTexture.dispose();
				changablePixmap.dispose();
				return true;
			}
			Screen s = THG.getGame().getScreen();
			if(s instanceof GGameController && ((GGameController)s).getSkipFlag())
				renderCount += EFFECT_CHANGE_SPEED_SKIP;
			else renderCount += EFFECT_CHANGE_SPEED_NORMAL;
			
			byte[] current = Effect_change.effect_change_1(afterBgData, width, height, depth,
					renderCount > TIME_LIMIT_COUNT ? TIME_LIMIT_COUNT : renderCount, TIME_LIMIT_COUNT);
			pixmapByteBuffer.put(current);
			pixmapByteBuffer.clear();
			if(changableTexture != null) changableTexture.dispose();
			changableTexture = new Texture(changablePixmap);
			afterSceneBgImage.setDrawable(new TextureRegionDrawable(new TextureRegion(changableTexture)));
			
			return false;
		}
	}

	/** 晃动，地震来了的感觉 */
	private class Effect_shake extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_shake(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			if(!isRunning) return true;
			
			
			return false;
		}
	}
	
	/** 模糊，也可以解模糊 */
	private class Effect_misty extends EffectAction {
		/**
		 * @param params 参数说明
		 * <p>
		 * 
		 */
		public Effect_misty(String... params) {
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





