package org.thg.logic.story.driver;

import org.thg.logic.THG;
import org.thg.logic.story.api.GDialog;
import org.thg.logic.story.api.ProgressData;

public class DefaultDialog implements GDialog {

	private String conditions;
	private int charactorPicNum;
	private int charactorPicPosition;
	private int charactorPicEffect;
	private int videoNum;
	private String words;
	private String name;
	private String choice;
	
	public DefaultDialog() {
		conditions = null;
		charactorPicNum = -1;
		charactorPicPosition = Config.CHARACTOR_PIC_POSITION_DEFAULT;
		charactorPicEffect = -1;
		videoNum = -1;
		words = null;
		choice = null;
	}
	
	@Override
	public final void setConditions(String cond) {
		conditions = cond;
	}

	@Override
	public boolean triggered() {
		return triggered(conditions);
	}

	@Override
	public final void setCharactorPic(int pNum) {
		charactorPicNum = pNum;
	}

	@Override
	public final int getCharactorPic() {
		return charactorPicNum;
	}

	@Override
	public final void setVideo(int vNum) {
		videoNum = vNum;
	}

	@Override
	public final int getVideo() {
		return videoNum;
	}

	@Override
	public final void setWords(String words) {
		this.words = words;
	}

	@Override
	public final String getWords() {
		return words;
	}

	@Override
	public final void setChoice(String choice) {
		this.choice = choice;
	}

	@Override
	public final String getChoice() {
		return choice;
	}

	@Override
	public final void setCharactorPicPosition(int position) {
		this.charactorPicPosition = position;
	}

	@Override
	public final int getCharactorPicPosition() {
		return charactorPicPosition;
	}


	@Override
	public void setCharactorPicEffect(int eNum) {
		charactorPicEffect = eNum;
	}

	@Override
	public int getCharactorPicEffect() {
		return charactorPicEffect;
	}
	@Override
	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public static boolean triggered(String condition) {
		if(condition == null || condition.equals("")) return true;
		ProgressData pd = ((DefaultGameController)THG.getGame().getScreen()).getCurrentProgressData();
		String[] conds = condition.split(CONDITIONS_SPLIT_SIGN);
		for(String cond : conds) {
			if(cond.equals("")) continue;
			String[] cond_parts = cond.split(COND_SPLIT_SIGN);
			if(cond_parts.length != 2) {
				System.err.println("condition error");
				return false;
			}
			int i,j;
			if((i = pd.getFlag(cond_parts[0])) != -1) { //flag条件
				try {
					j = Integer.parseInt(cond_parts[1]);
				}catch(Exception e){ 
					System.err.println("condition error");
					return false;
				}
				if(i == j) continue;
				else return false;
			}
			else { //印象值条件
				i = pd.getValue(cond_parts[0]);
				if(i == -1) { //左值情况
					System.err.println("condition error");
					return false;
				}
				
				if((j = pd.getValue(cond_parts[1])) == -1) { //右值情况
					try {
						j = Integer.parseInt(cond_parts[1]);
						if(i > j) continue;
						else return false;
					}catch(Exception e) {
						System.err.println("condition error");
						return false;
					}
				}
				else {
					if(i > j) continue;
					else return false;
				}
				
			}
			
			
		}
		
		
		return true;
	}
	private static final String CONDITIONS_SPLIT_SIGN = "(_+)";
	private static final String COND_SPLIT_SIGN = "@";
}
