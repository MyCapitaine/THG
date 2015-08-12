package org.thg.ui.gallery;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
/**
 * 鉴赏部分内的音乐部分
 * @author MyCapitaine
 *
 */
public class MusicPart extends Group implements Disposable {
	GGalleryMenu gallery;
	
	MusicList musicList;
	MusicPlayer musicPlayer;
	
	MusicPart(GGalleryMenu gallery) {
		this.gallery = gallery;
		musicList = new MusicList(this);
//		musicPlayer = new MusicPlayer(this);
		addActor(musicList);
//		addActor(musicPlayer);
		
	}

	@Override
	public void dispose() {
		musicList.dispose();
//		musicPlayer.dispose();
	}
	
	
	
	
	
}
