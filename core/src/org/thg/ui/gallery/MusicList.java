package org.thg.ui.gallery;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * 音乐列表
 * @author MyCapitaine
 *
 */
public class MusicList extends Group {
	MusicPart musicPart;
	
	ScrollPane scrollPane;
	List<MusicInfoUnit> musicList;
	
	private LabelStyle labelStyle;
	
	MusicList(MusicPart musicPart) {
		this.musicPart = musicPart;
		labelStyle = new LabelStyle(musicPart.gallery.font, Color.BLACK);
		
		musicList = new List<MusicInfoUnit>(new List.ListStyle());
		scrollPane = new ScrollPane(musicList);
		//TODO size & position
		addActor(scrollPane);
		
	}
	
	class MusicInfoUnit extends Group {
		Label num, musicName, singerName, musicianName, time;
		ImageButton play;
		MusicInfoUnit(MusicInfo info) {
			num = new Label(info.num + "", labelStyle);
			musicName = new Label(info.musicName, labelStyle);
			singerName = new Label(info.singerName, labelStyle);
			musicianName = new Label(info.musicianName, labelStyle);
			time = new Label(info.time / 60 + "" + info.time % 60, labelStyle);
//			play = new ImageButton(style);
			
//			TODO size & position
			
			addActor(num);
			addActor(musicName);
			addActor(singerName);
			addActor(musicianName);
			addActor(time);
			addActor(play);
		}
	}
}