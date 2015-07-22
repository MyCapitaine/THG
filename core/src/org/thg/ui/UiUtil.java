package org.thg.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UiUtil {
	public static Drawable resize(TextureRegion t, float width, float height) {
		Drawable d = new TextureRegionDrawable(t);
		d.setMinHeight(width * Config.scaleY);
		d.setMinWidth(height * Config.scaleX);
		return d;
	}
}
