package org.thg.ui.gallery;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

/**
 * <p>播放器组件
 * <p>显示当前曲信息
 * <p>提供歌曲进度条，暂停/继续，前一首，后一首的按键
 * @author MyCapitaine
 *
 */
public class MusicPlayer extends Group {
	MusicPart musicPart;
	
	Label num, musicName, singerName, musicianName;
	Slider progress;
	ImageButton pause, previous, latter;
	
	MusicPlayer(MusicPart musicPart) {
		this.musicPart = musicPart;
		
	}
}
