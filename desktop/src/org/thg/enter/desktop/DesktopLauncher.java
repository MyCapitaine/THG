package org.thg.enter.desktop;

import org.thg.enter.TouHouGal;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 400;
		config.height = 300;
		config.resizable = false;
		config.addIcon("icon/icon32.png", FileType.Internal);
		org.thg.ui.Config.scaleX = (float)config.width / (float)(org.thg.ui.Config.SCREEN_WIDTH);
		org.thg.ui.Config.scaleY = (float)config.height / (float)(org.thg.ui.Config.SCREEN_HEIGHT);
		
		new LwjglApplication(new TouHouGal(), config);
	}
}
