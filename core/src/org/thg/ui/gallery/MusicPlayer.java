package org.thg.ui.gallery;

import java.util.LinkedList;

import org.thg.logic.factorys.ResourceFactory;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.Disposable;

/**
 * <p>播放器组件
 * <p>显示当前曲信息
 * <p>提供歌曲进度条，暂停/继续，前一首，后一首的按键
 * @author MyCapitaine
 *
 */
public class MusicPlayer extends Group implements Disposable {
	MusicPart musicPart;
	
	Label num, musicName, singerName, musicianName;
	Slider progress;
	ImageButton pause, previous, post, stop;
	
	private LinkedList<Disposable> disList;
	
	MusicPlayer(MusicPart musicPart) {
		this.musicPart = musicPart;
		disList = new LinkedList<Disposable>();
		
		
//		TODO
//		
		
		pause.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				pauseOrPlay();
				return true;
			}
		});
		previous.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				pre();
				return true;
			}
		});
		post.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				post();
				return true;
			}
		});
		stop.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				stop();
				return true;
			}
		});
		
		
	}
	

	/** 暂停或播放当前歌曲 */
	private void pauseOrPlay() {
		final Music music = musicPart.gallery.currentMusic;
		if(music == null) return;
		if(music.isPlaying()) music.pause();
		else music.pause();
	}
	/** 停止当前歌曲 */
	private void stop() {
		final Music music = musicPart.gallery.currentMusic;
		if(music != null) music.stop();
	}
	/** 切到前一首歌，若为首位则切到最后一首 */
	private void pre() {
		final Music music = musicPart.gallery.currentMusic;
		if(music != null) music.stop();
		if(--musicPart.gallery.currentMusicNum < 0) 
			musicPart.gallery.currentMusicNum = musicPart.gallery.musicInfos.length - 1;
		musicPart.gallery.currentMusic = ResourceFactory.getBgm(musicPart.gallery.currentMusicNum);
		musicPart.gallery.currentMusic.setLooping(true);
		musicPart.gallery.currentMusic.play();
		
	}
	/** 切到后一首歌，若为末位则切到第一首 */
	private void post() {
		final Music music = musicPart.gallery.currentMusic;
		if(music != null) music.stop();
		if(++musicPart.gallery.currentMusicNum >= musicPart.gallery.musicInfos.length) 
			musicPart.gallery.currentMusicNum = 0;
		musicPart.gallery.currentMusic = ResourceFactory.getBgm(musicPart.gallery.currentMusicNum);
		musicPart.gallery.currentMusic.setLooping(true);
		musicPart.gallery.currentMusic.play();
	}
	

	@Override
	public void dispose() {
		for(Disposable d : disList)
			d.dispose();
	}
}
