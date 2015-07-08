package org.thg.logic.factorys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.thg.logic.THG;
import org.thg.logic.story.api.GStoryTree;
import org.thg.logic.story.driver.DefaultStoryTree;

import com.badlogic.gdx.Gdx;
/**
 * <p>用于导入剧情结构 ， 以下为一个范例</p>
 * <p>{@code // 注释 }</p>
 * <p>{@code 03-04 >> 03-05}</p>
 * <p>{@code 03-05 >> 03-06}</p>
 * <p>{@code 03-06 >> 03-07#1 (02-03#2)}</p>
 * <pre>        03-07#2</pre>
 * <p>{@code 03-07#1 >> 03-08#1 (c1 > c2 & 01-02 & c3 = c4)}</p>
 * <pre>          03-08#2</pre>
 *         
 *
 * @author MyCapitaine
 * 
 */
public class GStoryTreeFactory {
	
	public static GStoryTree createStoryTree() {
		DefaultStoryTree storyTree = new DefaultStoryTree();
		Reader fr = null;
		BufferedReader br = null;
		fr = Gdx.files.internal(STORY_DATA_URL).reader(THG.CHAR_SET);
		br = new BufferedReader(fr);
		
		String s = null;
		int count = 0;
		if(br != null) {
			do {
				try {
					s = br.readLine();
					//跳过空行并计数
					if(s == null ) {
						count ++;
						continue;
					}
					count = 0;
					s = s.trim();
					//
					if(s.equals(END_SIGN)) break;
					
					//跳过注释
					if(s != null && s.length() >= 2 && s.substring(0, 2).equals("//")) continue;
					
					
					//以反括号为结尾时加上下一行
					while(s.length() != 0 && s.charAt(s.length() - 1) == ')') {
						s += br.readLine().trim();
					}
					String[] stringList = s.split(INTERVAL_1);
					try {
						storyTree.put(stringList[0].trim(), stringList[1].trim());
					}catch(Exception e) {}
				} catch (IOException e) {
					s = null;
					e.printStackTrace();
				}
			}while(count != 10);
			//当没有结束标记时，如果连续读取10行没有数据则自动停止
		}
		
		
		
		if(fr != null)
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(br != null)
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		return storyTree;
	}

	private static final String STORY_DATA_URL = "storydatas/storyline.sd";
	private static final String END_SIGN = "<end>";
	private static final String INTERVAL_1 = ">>";
	
}
