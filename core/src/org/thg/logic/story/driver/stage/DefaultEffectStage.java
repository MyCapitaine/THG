package org.thg.logic.story.driver.stage;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.logic.story.api.GEffectScene;
import org.thg.logic.story.api.GEffectStage;
import org.thg.logic.story.driver.DefaultGameController;
import org.thg.logic.story.driver.stage.effect.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
/**
 * 一个效果场景的实现
 * @author MyCapitaine
 *
 */
public class DefaultEffectStage extends Stage implements GEffectStage {
	public EffectAction effect;
	public Image bg;
	public Texture bgTexture;
	
	public DefaultEffectStage() {
		super();
		bg = new Image();
		bg.setSize(org.thg.ui.Config.SCREEN_WIDTH * org.thg.ui.Config.scaleX,
				org.thg.ui.Config.SCREEN_HEIGHT * org.thg.ui.Config.scaleY);
		addActor(bg);
	}
	
	@Override
	public boolean isRunning(boolean byHand) {
		return effect.isRunning(byHand);
	}

	@Override
	public void setScene(GEffectScene effectScene) {
//		bgm
		DefaultGameController.setBgm(effectScene.getBgMusic());
//		bg
		bgTexture = ResourceFactory.getBg(effectScene.getBackground());
		bg.setDrawable(new TextureRegionDrawable(new TextureRegion(bgTexture)));
//		effect实体
		try {
			effect = Effect.getEffect(effectScene.getEffectNum()).
					createEffect(this, effectScene.getParams());
		}
		catch(Exception e) {
			effect = new Effect_unsupport();
			System.out.println(e);
			System.out.println("effect error or unsupport");
		}
		addAction(effect);
		
	}
	@Deprecated
	@Override
	public void setBg(int bgNum) {
	}
	@Deprecated
	@Override
	public void setBgm(int bgmNum) {
	}
	
	

	/** 显而易见 */
	private enum Effect {
		空格(00) {
			EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_blank(stage, params);
			}
		},
		切换(01){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_change(stage, params);
			}
		},
		晃动(11){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_shake(stage, params);
			}
		},
		模糊(21){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_misty(stage, params);
			}
		}, 
		睁眼(31){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_open_eyes(stage, params);
			}
		}, 
		醉了(41){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_drunk(stage, params);
			}
		}, 
		闪瞎(51){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_blind(stage, params);
			}
		}, 
		雪花(61){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_snowflake(stage, params);
			}
		}, 
		聚焦(71){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_focus(stage, params);
			}
		}, 
		放大(81){
			public EffectAction createEffect(DefaultEffectStage stage, String... params) {
				return new Effect_amplify(stage, params);
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
	
	
	
		
	

	

	
	
	
	

	

	
	
	
	
	
	

}





