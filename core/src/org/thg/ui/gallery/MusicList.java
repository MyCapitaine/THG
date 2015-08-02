package org.thg.ui.gallery;

import org.thg.ui.Config;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

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
		
		ListStyle ls = new List.ListStyle();
		ls.font = musicPart.gallery.font;
		ls.selection = new TextureRegionDrawable(new TextureRegion(new Texture(Config.GALLERY_MENU_RETURN_BUTTON_URL)));
		musicList = new List<MusicInfoUnit>(ls);
		Array<MusicInfoUnit> items = new Array<MusicInfoUnit>();
		//表头
		items.add(new MusicInfoUnit(null));
		for(MusicInfo mi : this.musicPart.gallery.musicInfos)
			items.add(new MusicInfoUnit(mi));
		musicList.setItems(items);
		musicList.setBounds(0, 0,
				(Config.SCREEN_WIDTH - 2 * Config.GALLERY_MENU_PADDING) * Config.scaleX,
				(musicList.getItems().size + 1) * Config.GALLERY_NENU_MUSIC_SINGLE_LIST_HEIGHT);
		scrollPane = new ScrollPane(musicList);
		scrollPane.setBounds(Config.GALLERY_MENU_PADDING * Config.scaleX,
				Config.GALLERY_MENU_PADDING * Config.scaleY,
				musicList.getWidth(), Config.GALLERY_NENU_MUSIC_LIST_HEIGHT);
		addActor(scrollPane);
		
//		musicList.
	}
	
	/** 选中即播放 */
	class MusicInfoUnit{
		private String infoStr;
		MusicInfoUnit(MusicInfo info) {
//			this.info = info;
//			setPosition(0, 0);
//			setSize((Config.SCREEN_WIDTH - 2 * Config.GALLERY_MENU_PADDING) * Config.scaleX,
//					Config.GALLERY_NENU_MUSIC_SINGLE_LIST_HEIGHT * Config.scaleY);
//			num = new Label(info == null ? "序号" : info.num + "", labelStyle);
//			musicName = new Label(info == null ? "曲名" : info.musicName, labelStyle);
//			singerName = new Label(info == null ? "歌" : info.singerName, labelStyle);
//			musicianName = new Label(info == null ? "作曲" : info.musicianName, labelStyle);
//			time = new Label(info == null ? "时长" : (info.time / 60 + "" + info.time % 60), labelStyle);
//			
		}
		
		@Override
		public String toString() {
			return infoStr;
		}
	}
}