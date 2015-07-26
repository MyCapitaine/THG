package org.thg.enter.android;

import org.thg.enter.TouHouGal;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Point screen = new Point();
		getWindowManager().getDefaultDisplay().getSize(screen);
		org.thg.ui.Config.scaleX = (float)screen.x / (float)org.thg.ui.Config.SCREEN_WIDTH;
		org.thg.ui.Config.scaleY = (float)screen.y / (float)org.thg.ui.Config.SCREEN_HEIGHT;
		
		//禁止屏幕休眠
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new TouHouGal(), config);
	}
}
