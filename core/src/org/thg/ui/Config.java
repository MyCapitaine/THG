package org.thg.ui;

public class Config {
//====Global==================================================================================
	public static float scaleX = 1.0f, scaleY = 1.0f;
	public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
	public static final int 
		UI_BUTTON_WIDHT = 150,
		UI_BUTTON_HEIGHT = 80;
//====GMainMenu==================================================================================	
	public static final String MAIN_MENU_RESOURCE_URL = "images/ui/mainmenu/";
	public static final String MAIN_MENU_BG_URL = MAIN_MENU_RESOURCE_URL + "background.png";
	public static final String MAIN_MENU_BEGIN_BUTTON_URL = MAIN_MENU_RESOURCE_URL + "beginButton.png";
	public static final String MAIN_MENU_LOAD_BUTTON_URL = MAIN_MENU_RESOURCE_URL + "loadButton.png";
	public static final String MAIN_MENU_GALLERY_BUTTON_URL = MAIN_MENU_RESOURCE_URL + "galleryButton.png";
	public static final String MAIN_MENU_SETTING_BUTTON_URL = MAIN_MENU_RESOURCE_URL + "settingButton.png";
	public static final String MAIN_MENU_EXIT_BUTTON_URL = MAIN_MENU_RESOURCE_URL + "exitButton.png";
	
	
//====GLoadingMenu==================================================================================
	public static final String LOADING_MENU_BG_URL = "images/ui/loadingmenu/background.png";
	
//====GSave/LoadMenu==================================================================================
	public static final String SL_MENU_BG_URL = "images/ui/slmenu/background.png";
	public static final String SL_MENU_RETURN_BUTTON_URL = "images/ui/slmenu/returnButton.png";
	public static final String SL_MENU_SHADE_URL = "images/ui/slmenu/shade.png";
	public static final String SL_MENU_NODATA_URL = "images/ui/slmenu/nodata.png";
	public static final String SL_MENU_TP_BUTTON_URL = "images/ui/slmenu/tpButton.png";
	
	
	public static final float SL_PADDING = 10f;
	public static final float SL_PIC_WIDTH = 156f, SL_PIC_HEIGHT = 117f;
	public static final float SL_TP_WIDTH = 75f, SL_TP_HEIGHT = 37f;
	
	
	
//====GGalleryMenu==================================================================================
	public static final float GALLERY_MENU_PADDING = 10f;
	public static final String GALLERY_MENU_RESOURCE_URL = "images/ui/gallerymenu/";
	public static final String GALLERY_MENU_BG_URL = GALLERY_MENU_RESOURCE_URL + "background.png";
	public static final String GALLERY_MENU_RETURN_BUTTON_URL = GALLERY_MENU_RESOURCE_URL + "returnButton.png";
	public static final String GALLERY_MENU_CG_LABEL_URL = GALLERY_MENU_RESOURCE_URL + "cgLabel.png";
	public static final String GALLERY_MENU_MUSIC_LABEL_URL = GALLERY_MENU_RESOURCE_URL + "musicLabel.png";
	public static final String GALLERY_MENU_MUSIC_LIST_SELECTION_URL = GALLERY_MENU_RESOURCE_URL + "selection.png";
	
	public static final float
		GALLERY_MENU_LABEL_LARGER_WIDTH = 150f, GALLERY_MENU_LABEL_LARGER_HEIGHT = 50f,
		GALLERY_MENU_LABEL_SMALLER_WIDTH = 100f, GALLERY_MENU_LABEL_SMALLER_HEIGHT = GALLERY_MENU_LABEL_LARGER_HEIGHT * GALLERY_MENU_LABEL_SMALLER_WIDTH / GALLERY_MENU_LABEL_LARGER_WIDTH;
	public static final float
		GALLERY_MENU_MUSIC_PLAYER_1_WIDTH = 0,
		GALLERY_MENU_MUSIC_PLAYER_1_HEIGHT = GALLERY_MENU_LABEL_SMALLER_HEIGHT + GALLERY_MENU_LABEL_LARGER_HEIGHT + GALLERY_MENU_PADDING / 2,
		GALLERY_NENU_MUSIC_LIST_HEIGHT = SCREEN_HEIGHT - 3 * GALLERY_MENU_PADDING - GALLERY_MENU_MUSIC_PLAYER_1_HEIGHT;
//		GALLERY_NENU_MUSIC_SINGLE_LIST_HEIGHT = 50,
//		GALLERY_NENU_MUSIC_LIST_NUM_WIDTH = 70,
//		GALLERY_NENU_MUSIC_LIST_MUSIC_WIDTH = 200,
//		GALLERY_NENU_MUSIC_LIST_SINGER_WIDTH = 200,
//		GALLERY_NENU_MUSIC_LIST_MUSICIAN_WIDTH = 200,
//		GALLERY_NENU_MUSIC_LIST_TIME_WIDTH = SCREEN_WIDTH - 2 * GALLERY_MENU_PADDING - GALLERY_NENU_MUSIC_LIST_NUM_WIDTH -
//			GALLERY_NENU_MUSIC_LIST_MUSIC_WIDTH - GALLERY_NENU_MUSIC_LIST_SINGER_WIDTH - GALLERY_NENU_MUSIC_LIST_MUSICIAN_WIDTH;
		
	
	
	
	
	
	
	
	
//====GSettingMenu==================================================================================	
	public static final String PROPERTIES_URL = "properties/settings.p";
	
	public static final float VOL_VOICE_MIN = 0f, VOL_VOICE_MAX = 1f, VOL_VOICE_STEP_SIZE = 0.01f;
	public static float vol_voice = VOL_VOICE_MAX;
	public static final float VOL_BGM_MIN = 0f, VOL_BGM_MAX = 1f;
	public static float vol_bgm = VOL_BGM_MAX;
	/** 每秒显示的字数 */
	public static final float SHOW_WORDS_SPEED = 80f;
	public static final float DIALOG_INTERVAL_MIN = 0.8f, DIALOG_INTERVAL_MAX = 1.0f,
			DIALOG_INTERVAL_STEP_SIZE = 0.01f;
	/** 每段对话的默认间隔 */
	public static float dialogInterval = DIALOG_INTERVAL_MIN;
	/** 使用中的皮肤 */
	public static GameSkin using_skin = GameSkin.DEFAULT;
	public enum GameSkin {
		DEFAULT, 博丽灵梦, 射命丸文, 东风谷早苗
	}
	
	public static final String SETTING_MENU_BG_URL = "images/ui/settingmenu/background.png";
	public static final String SETTING_MENU_RETURN_BUTTON_URL = "images/ui/settingmenu/returnButton.png";
	public static final String SETTING_MENU_VOL_LABEL_URL = "images/ui/settingmenu/volLabel.png";	
	public static final String SETTING_MENU_VOL_SLIDER_BG_URL = "images/ui/settingmenu/volSliderBg.png";	
	public static final String SETTING_MENU_VOL_SLIDER_KNOB_URL = "images/ui/settingmenu/volSliderKnob.png";	
	public static final String SETTING_MENU_SPEED_LABEL_URL = "images/ui/settingmenu/speedLabel.png";	
	public static final String SETTING_MENU_SPEED_SLIDER_BG_URL = "images/ui/settingmenu/speedSliderBg.png";	
	public static final String SETTING_MENU_SPEED_SLIDER_KNOB_URL = "images/ui/settingmenu/speedSliderKnob.png";	
	public static final String SETTING_MENU_INTERVAL_LABEL_URL = "images/ui/settingmenu/intervalLabel.png";	
	public static final String SETTING_MENU_INTERVAL_SLIDER_BG_URL = "images/ui/settingmenu/intervalSliderBg.png";	
	public static final String SETTING_MENU_INTERVAL_SLIDER_KNOB_URL = "images/ui/settingmenu/intervalSliderKnob.png";	
	public static final int
		SLIDER_WIDTH = 300, SLIDER_HEIGHT = 100,
		KNOB_WIDTH = 30, KNOB_HEIGHT = 100,
		SLIDER_LABEL_WIDTH = 100, SLIDER_LABEL_HEIGHT = 50;
//====GWordsFrame==================================================================================
	public static final String WORDS_FRAME_URL = "images/defaults/gamestage/wordsFrameBg.png";
	public static final int
		WORDS_FRAME_WIDTH = 800, WORDS_FRAME_HEIGHT = 200,
		WORDS_FRAME_X = 0, WORDS_FRAME_Y = 15;
	
//====GWordsWindow==================================================================================
	public static final String WORDS_WINDOW_URL = "images/defaults/gamestage/wordsWindowBg.png";
	public static final int
		WORDS_WINDOW_WIDTH = 800, WORDS_WINDOW_HEIGHT = 600,
		WORDS_WINDOW_X = 0, WORDS_WINDOW_Y = 0;
//====GGameButtons==================================================================================
	public static final String AUTO_BUTTON_URL = "images/defaults/gamestage/autoButton.png";
	public static final String LOAD_BUTTON_URL = "images/defaults/gamestage/loadButton.png";
	public static final String RETURN_BUTTON_URL = "images/defaults/gamestage/returnButton.png";
	public static final String SAVE_BUTTON_URL = "images/defaults/gamestage/saveButton.png";
	public static final String SKIP_BUTTON_URL = "images/defaults/gamestage/skipButton.png";

	public static final int
		GAME_BUTTON_WIDHT = 110, GAME_BUTTON_HEIGHT = 30;
//====GChoiceWindow==================================================================================
	public static final String CHOICE_WINDOW_BG_URL = "images/defaults/gamestage/choiceWindowBg.png";
	public static final String CHOICE_BUTTON_URL = "images/defaults/gamestage/choiceButton.png";
	
	public static final float CHOICE_WINDOW_BG_WIDTH = 300f;
	public static final float CHOICE_WINDOW_PADDING = 20f;
	public static final float CHOICE_BUTTON_WIDTH = CHOICE_WINDOW_BG_WIDTH - 2 * CHOICE_WINDOW_PADDING;
	public static final float CHOICE_BUTTON_PADDING = 10f;
	public static final float CHOICE_BUTTON_HEIGHT_ONELINE = 35f;
	public static final float CHOICE_FONT_SIZE = 24F;
	
//====Gsave/load==================================================================================
	public static final float TP_BUTTON_WIDTH = 50f, TP_BUTTON_HEIGHT = 35f;
	
	
	
	
	
	
}
