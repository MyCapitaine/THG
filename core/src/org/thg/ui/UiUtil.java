package org.thg.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UiUtil {
	public static Drawable resize(TextureRegion t) {
		Drawable d = new TextureRegionDrawable(t);
		d.setMinHeight(d.getMinHeight() * Config.scaleY);
		d.setMinWidth(d.getMinWidth() * Config.scaleX);
		return d;
	}
}
