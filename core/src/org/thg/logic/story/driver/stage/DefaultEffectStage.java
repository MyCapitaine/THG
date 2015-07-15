package org.thg.logic.story.driver.stage;

import org.thg.logic.story.api.GEffectStage;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DefaultEffectStage extends Stage implements GEffectStage {
	
	public DefaultEffectStage() {
		super();
	}
	
	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void dispose() {
		
	}
	
	enum Effect {
		晃动, 模糊, 睁眼, 醉, 闪瞎, 雪花, 聚焦, 放大 
	}
	

	/** 晃动 */
	class Effect_shake extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 模糊 */
	class Effect_misty extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 睁眼 */
	class Effect_open_eyes extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 神志不清ing，醉了 */
	class Effect_drunk extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 狗眼闪瞎，中了闪光弹 */
	class Effect_blind extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 雪花，意识间歇ing */
	class Effect_snowflake extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 聚焦 */
	class Effect_focus extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	/** 放大 */
	class Effect_amplify extends Action {
		@Override
		public boolean act(float delta) {
			// TODO Auto-generated method stub
			return false;
		}
	}
}





