/**
 * 
 */
package org.thg.logic.story.driver;

import org.thg.logic.story.api.GDay;
import org.thg.logic.story.api.GScene;

/**
 * @author MyCapitaine
 *
 */
public class DefaultDay implements GDay {
	private GScene[] scenes;
	private int scene_position;
	private String dayStr;
	
	
	public DefaultDay() {
		dayStr = null;
		scenes = null;
		scene_position = -1;
	}
	
	@Override
	public final void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	@Override
	public final String getDayStr() {
		return dayStr;
	}

	@Override
	public final void setScenes(GScene[] scenes) {
		this.scenes = scenes;
	}

	
	@Override
	public GScene getNextScene() {
		for(; ++ scene_position < scenes.length; ) {
			if(scenes[scene_position].triggered())
				return scenes[scene_position];
		}
		return null;
	}

	@Override
	public GScene setScenePosition(int num) {
		if(scenes == null) {
			System.err.println("DefaultDay.getScene(int) scenes == null");
			return null;
		}
		if(num < 0 || num >= scenes.length) {
			System.err.println("DefaultDay.getScene(int) out of index");
			return null;
		}
		scene_position = num;
		return scenes[scene_position];
	}

	@Override
	public int getPosition() {
		if(scenes == null || scene_position < 0 || scene_position >= scenes.length)
			return 0;
		else 
			return scene_position;
	}
	
	
	
	
}
