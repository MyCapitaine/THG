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
	ScrollPane scrollPane;
	List<MusicInfoUnit> musicList;
	
	
	MusicList() {
		
	}

	class MusicInfoUnit extends Group {
	
		public MusicInfoUnit(MusicInfo info) {
		}
	}
}
