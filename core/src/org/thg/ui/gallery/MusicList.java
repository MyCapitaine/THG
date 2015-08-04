package org.thg.ui.gallery;

import java.util.LinkedList;

import org.thg.logic.factorys.ResourceFactory;
import org.thg.ui.Config;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * 音乐列表
 * @author MyCapitaine
 *
 */
public class MusicList extends Group implements Disposable {
	MusicPart musicPart;
	
	ScrollPane scrollPane;
	List<MusicInfoUnit> musicList;
	Label musicListHead;
	
	private LinkedList<Disposable> disList;
	
	MusicList(MusicPart musicPart) {
		this.musicPart = musicPart;
		disList = new LinkedList<Disposable>();
		
//		musicListHead TODO
		
		
		ListStyle ls = new List.ListStyle();
		ls.font = musicPart.gallery.font;
		Texture selectionTexture = new Texture(Config.GALLERY_MENU_MUSIC_LIST_SELECTION_URL);
		disList.add(selectionTexture);
		ls.selection = new TextureRegionDrawable(new TextureRegion(selectionTexture));
		musicList = new List<MusicInfoUnit>(ls);
		Array<MusicInfoUnit> items = new Array<MusicInfoUnit>();
		for(MusicInfo mi : this.musicPart.gallery.musicInfos)
			items.add(new MusicInfoUnit(mi));
		musicList.setItems(items);
		musicList.setBounds(0, 0,
				(Config.SCREEN_WIDTH - 2 * Config.GALLERY_MENU_PADDING) * Config.scaleX,
				musicList.getItems().size * musicList.getItemHeight());
		scrollPane = new ScrollPane(musicList);
		scrollPane.setBounds(Config.GALLERY_MENU_PADDING * Config.scaleX,
				Config.GALLERY_MENU_PADDING * Config.scaleY,
				musicList.getWidth(), Config.GALLERY_NENU_MUSIC_LIST_HEIGHT);
		addActor(scrollPane);
		musicList.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				int selectedIndex = musicList.getSelectedIndex();
				if(selectedIndex == MusicList.this.musicPart.gallery.currentMusicNum) return false;
				
				MusicList.this.musicPart.gallery.currentMusicNum = selectedIndex;
				final Music music = ResourceFactory.getBgm(selectedIndex);
				if(music != null) {
					music.setLooping(true);
					music.play();
				}
				if(MusicList.this.musicPart.gallery.currentMusic != null)
					MusicList.this.musicPart.gallery.currentMusic.stop();
				MusicList.this.musicPart.gallery.currentMusic = music;
				return true;
			}
		});
	}
	
	
	@Override
	public void dispose() {
		for(Disposable d : disList)
			d.dispose();
	}
	
	/** 选中即播放 */
	class MusicInfoUnit{
		private String infoStr;
		MusicInfoUnit(MusicInfo info) {
			String num = (info.num + 1) + "";
			if(num.length() == 1) num = "0" + num;
			
			infoStr = ""
					+ num
					+ info.musicName
					+ info.singerName
					+ info.musicianName
					+ info.time;
			
//			音乐信息格式化显示 TODO
		}
		
		@Override
		public String toString() {
			return infoStr;
		}
	}



}