package org.thg.logic.story.driver.stage;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GEffectScene;
import org.thg.logic.story.api.GEffectStage;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.ui.UiUtil;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
/**
 * 一个效果场景的实现
 * @author MyCapitaine
 *
 */
public class DefaultEffectStage extends Stage implements GEffectStage {
	private EffectAction effect;
	private Image bg;
	
	public DefaultEffectStage() {
		super();
		bg = new Image();
		bg.setSize(org.thg.ui.Config.SCREEN_WIDTH * org.thg.ui.Config.scaleX,
				org.thg.ui.Config.SCREEN_HEIGHT * org.thg.ui.Config.scaleY);
		addActor(bg);
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
	
	

	abstract class EffectAction extends Action {
		//act return true时将此标识置于否
		protected boolean isRunning = true;
		public boolean isRunning() { return isRunning; }
	}
	private enum Effect {
		切换{
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return stage.new Effect_change(params);
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
	
	/** 明显的场景切换 */
	private class Effect_change extends EffectAction {
		public Effect_change(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			
			isRunning = true;
			return false;
		}
	}

	/** 晃动 */
	private class Effect_shake extends EffectAction {
		public Effect_shake(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 模糊 */
	private class Effect_misty extends EffectAction {
		public Effect_misty(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 睁眼 */
	private class Effect_open_eyes extends EffectAction {
		public Effect_open_eyes(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 神志不清ing，醉了 */
	private class Effect_drunk extends EffectAction {
		public Effect_drunk(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 狗眼闪瞎，中了闪光弹 */
	private class Effect_blind extends EffectAction {
		public Effect_blind(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 雪花，意识间歇ing */
	private class Effect_snowflake extends EffectAction {
		public Effect_snowflake(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 聚焦 */
	private class Effect_focus extends EffectAction {
		public Effect_focus(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 放大 */
	private class Effect_amplify extends EffectAction {
		public Effect_amplify(String... params) {
		}
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}

}





