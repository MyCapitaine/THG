/**
 * 
 */
package org.thg.logic.story.driver.scene;

import org.thg.logic.story.api.GDialog;
import org.thg.logic.story.api.GScene;
import org.thg.logic.story.driver.DefaultDialog;

/**
 * 一个GScene的实现
 * @author MyCapitaine
 *
 */
public class DefaultScene implements GScene {
	private String conditions;
	protected GDialog[] dialogs;
	private int dialog_position;
	private int bgMusicNum;
	private int bgNum;
	
	public DefaultScene() {
		conditions = null;
		dialogs = null;
		dialog_position = -1;
		bgMusicNum = -1;
		bgNum = -1;
	}
	
	@Override
	public final void setConditions(String cond) {
		conditions = cond;
	}

	@Override
	public boolean triggered() {
		return DefaultDialog.triggered(conditions);
	}

	@Override
	public final void setDialogs(GDialog[] dialogs) {
		this.dialogs = dialogs;
	}

	@Override
	public GDialog getNextDialog() {
		for(; ++ dialog_position < dialogs.length; ) {
			if(dialogs[dialog_position].triggered())
				return dialogs[dialog_position];
		}
		return null;
		
	}

	@Override
	public final void setBgMusic(int mNum) {
		bgMusicNum = mNum;
	}

	@Override
	public final int getBgMusic() {
		return bgMusicNum;
	}

	@Override
	public final void setBackground(int bNum) {
		bgNum = bNum;
	}

	@Override
	public final int getBackground() {
		return bgNum;
	}

	@Override
	public GDialog setDialogPosition(int num) {
		if(dialogs == null) {
			System.err.println("DefaultScene.setDialogPosition(int) dialogs == null");
			return null;
		}
		if(num < 0 || num >= dialogs.length) {
			System.err.println("DefaultScene.setDialogPosition(int) out of index");
			return null;
		}
		dialog_position = num;
		return dialogs[dialog_position];
	}

	@Override
	public int getPosition() {
		if(dialogs == null || dialog_position < 0 || dialog_position >= dialogs.length)
			return 0;
		else 
			return dialog_position;
	}

}
