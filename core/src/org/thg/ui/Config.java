package org.thg.ui;

public class Config {
//====Global==================================================================================
	public static float scaleX = 1.0f, scaleY = 1.0f;
	public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
	static final int 
		UI_BUTTON_WIDHT = 150,
		UI_BUTTON_HEIGHT = 80;
//====GMainMenu==================================================================================	
	static final String MAIN_MENU_BG_URL = "images/ui/mainmenu/background.png";
	static final String MAIN_MENU_BEGIN_BUTTON_URL = "images/ui/mainmenu/beginButton.png";
	static final String MAIN_MENU_LOAD_BUTTON_URL = "images/ui/mainmenu/loadButton.png";
	static final String MAIN_MENU_GALLERY_BUTTON_URL = "images/ui/mainmenu/galleryButton.png";
	static final String MAIN_MENU_SETTING_BUTTON_URL = "images/ui/mainmenu/settingButton.png";
	static final String MAIN_MENU_EXIT_BUTTON_URL = "images/ui/mainmenu/exitButton.png";
	
	
//====GLoadingMenu==================================================================================
	static final String LOADING_MENU_BG_URL = "images/ui/loadingmenu/background.png";
	
//====GLoadingMenu==================================================================================
	static final String LOAD_MENU_BG_URL = "images/ui/loadmenu/background.png";
	static final String LOAD_MENU_RETURN_BUTTON_URL = "images/ui/loadmenu/returnButton.png";
	
//====GGalleryMenu==================================================================================
	static final String GALLERY_MENU_BG_URL = "images/ui/gallerymenu/background.png";
	static final String GALLERY_MENU_RETURN_BUTTON_URL = "images/ui/gallerymenu/returnButton.png";

//====GSettingMenu==================================================================================	
	static final String PROPERTIES_URL = "properties/settings.p";
	static final float VOL_VOICE_MIN = 0f, VOL_VOICE_MAX = 1f, VOL_VOICE_STEP_SIZE = 0.01f;
	public static float vol_voice = VOL_VOICE_MAX;
	static final float VOL_BGM_MIN = 0f, VOL_BGM_MAX = 1f;
	public static float vol_bgm = VOL_BGM_MAX;
	static final float SHOW_WORDS_SPEED_MIN = 80f, SHOW_WORDS_SPEED_MAX = 90f,
			SHOW_WORDS_SPEED_STEP_SIZE = 0.1f;
	/**
	 * 每秒显示的字数
	 */
	public static float showWordsSpeed = SHOW_WORDS_SPEED_MIN;
	
	static final float DIALOG_INTERVAL_MIN = 0.8f, DIALOG_INTERVAL_MAX = 1.0f,
			DIALOG_INTERVAL_STEP_SIZE = 0.01f;
	/**
	 * 每段对话的默认间隔
	 */
	static float dialogInterval = DIALOG_INTERVAL_MIN;
	static final String SETTING_MENU_BG_URL = "images/ui/settingmenu/background.png";
	static final String SETTING_MENU_RETURN_BUTTON_URL = "images/ui/settingmenu/returnButton.png";
	static final String SETTING_MENU_VOL_LABEL_URL = "images/ui/settingmenu/volLabel.png";	
	static final String SETTING_MENU_VOL_SLIDER_BG_URL = "images/ui/settingmenu/volSliderBg.png";	
	static final String SETTING_MENU_VOL_SLIDER_KNOB_URL = "images/ui/settingmenu/volSliderKnob.png";	
	static final String SETTING_MENU_SPEED_LABEL_URL = "images/ui/settingmenu/speedLabel.png";	
	static final String SETTING_MENU_SPEED_SLIDER_BG_URL = "images/ui/settingmenu/speedSliderBg.png";	
	static final String SETTING_MENU_SPEED_SLIDER_KNOB_URL = "images/ui/settingmenu/speedSliderKnob.png";	
	static final String SETTING_MENU_INTERVAL_LABEL_URL = "images/ui/settingmenu/intervalLabel.png";	
	static final String SETTING_MENU_INTERVAL_SLIDER_BG_URL = "images/ui/settingmenu/intervalSliderBg.png";	
	static final String SETTING_MENU_INTERVAL_SLIDER_KNOB_URL = "images/ui/settingmenu/intervalSliderKnob.png";	
	static final int
		SLIDER_WIDTH = 300, SLIDER_HEIGHT = 100;
//====GWordsFrame==================================================================================
	static final String WORDS_FRAME_URL = "images/defaults/gamestage/wordsFrameBg.png";
	static final int
		WORDS_FRAME_WIDTH = 800, WORDS_FRAME_HEIGHT = 200,
		WORDS_FRAME_X = 0, WORDS_FRAME_Y = 15;
	
//====GWordsWindow==================================================================================
	static final String WORDS_WINDOW_URL = "images/defaults/gamestage/wordsWindowBg.png";
	static final int
		WORDS_WINDOW_WIDTH = 760, WORDS_WINDOW_HEIGHT = 560;
//====GGameButtons==================================================================================
	static final String AUTO_BUTTON_URL = "images/defaults/gamestage/autoButton.png";
	static final String LOAD_BUTTON_URL = "images/defaults/gamestage/loadButton.png";
	static final String RETURN_BUTTON_URL = "images/defaults/gamestage/returnButton.png";
	static final String SAVE_BUTTON_URL = "images/defaults/gamestage/saveButton.png";
	static final String SKIP_BUTTON_URL = "images/defaults/gamestage/skipButton.png";

	static final int
		BUTTON_WIDHT = 100, BUTTON_HEIGHT = 40;
//====GChoiceWindow==================================================================================
	static final String CHOICE_WINDOW_BG_URL = "images/defaults/gamestage/choiceWindowBg.png";
	static final String CHOICE_BUTTON_URL = "images/defaults/gamestage/choiceButton.png";
	
	static final float CHOICE_WINDOW_BG_WIDTH = 300f;
	static final float CHOICE_WINDOW_PADDING = 20f;
	static final float CHOICE_BUTTON_WIDTH = CHOICE_WINDOW_BG_WIDTH - 2 * CHOICE_WINDOW_PADDING;
	static final float CHOICE_BUTTON_PADDING = 10f;
	static final float CHOICE_BUTTON_HEIGHT_ONELINE = 35f;
	static final float CHOICE_FONT_SIZE = 24F;
	
	
	
	
	
	
	
	
	
}
