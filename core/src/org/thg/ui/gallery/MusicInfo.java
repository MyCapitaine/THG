package org.thg.ui.gallery;
/**
 * 曲子信息
 * @author MyCapitaine
 *
 */
public class MusicInfo {
	/** 编号 */
	final int num;
	/** 曲名 */
	final String musicName;
	/** 歌手名 */
	final String singerName;
	/** 作曲家名 */
	final String musicianName;
	/** 时长 */
	final int time;
	
	public MusicInfo(int num, String musicName, String singerName,
			String musicianName, int time) {
		this.num = num;
		this.musicName = musicName;
		this.singerName = singerName;
		this.musicianName = musicianName;
		this.time = time;
	}
	public MusicInfo(int num, String musicName, String singerName,
			String musicianName, String time) {
		this.num = num;
		this.musicName = musicName;
		this.singerName = singerName;
		this.musicianName = musicianName;
		
		if(time.contains(":")) {
			String[] sl = time.split(":");
			int i = 0;
			if(sl.length > 0) i += 60 * Integer.parseInt(sl[0]);
			if(sl.length > 1) i += 60 * Integer.parseInt(sl[1]);
			this.time = i;
		}
		else
			this.time = Integer.parseInt(time);
	}
	
	/**
	 * <p>获取所有音乐的信息
	 * <p>下标是音乐的编号
	 * @return 所有音乐信息的列表
	 */
	public static MusicInfo[] loadMusicInfo() {
		//TODO
		return new MusicInfo[] {
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100),
				new MusicInfo(000, "aa", "aa", "aa", 100)
		};
	}
}
