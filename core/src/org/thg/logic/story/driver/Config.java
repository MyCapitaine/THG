package org.thg.logic.story.driver;

public class Config {
//========GameStage============================================================================================
	/**
	 * 立绘位置
	 */
	public static final int
		CHARACTOR_PIC_POSITION_LEFT = 0,
		CHARACTOR_PIC_POSITION_CENTER = 1,
		CHARACTOR_PIC_POSITION_RIGHT = 2,
		CHARACTOR_PIC_POSITION_FAR = 3,
		CHARACTOR_PIC_POSITION_NEAR = 4,
		CHARACTOR_PIC_POSITION_DEFAULT = CHARACTOR_PIC_POSITION_RIGHT;
	public static final int 
		CHARACTOR_PIC_WIDTH = 690, CHARACTOR_PIC_HEIGHT = 920;
	public static final String NAME_BG_URL = "images/defaults/gamestage/nameBg.png";
	public static final float NAME_FONT_SIZE = 28f;
	public static final int NAME_WIDTH = 350, NAME_HEIGHT = 50, NAME_PADDING = 10;
	
//========TurnPageStage============================================================================================
	public static final String TURN_PAGE_STAGE_BG_URL = "images/defaults/turnpagestage/turnPageBg.png";
	
//====ProgressData================================================================================================
	public static final String DEFAULT_PROGRESSDATA_URL = "progressdatas/default_progressdata.pd";
	
	
	
}
