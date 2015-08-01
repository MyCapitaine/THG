package org.thg.ui.gallery;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

/**
 * 音乐列表
 * @author MyCapitaine
 *
 */
public class MusicList extends Group {
	MusicPart musicPart;
	
	ScrollPane scrollPane;
	List<MusicInfoUnit> musicList;
	
	
	MusicList(MusicPart musicPart) {
		this.musicPart = musicPart;
		
	}
	
	class MusicInfoUnit extends Group {
	
		MusicInfoUnit(MusicInfo info) {
		}
	}
}