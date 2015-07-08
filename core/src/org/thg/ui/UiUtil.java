package org.thg.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class UiUtil {
	public static Drawable resize(TextureRegion t) {
		Drawable d = new TextureRegionDrawable(t);
		d.setMinHeight(d.getMinHeight() * Config.scaleY);
		d.setMinWidth(d.getMinWidth() * Config.scaleX);
		return d;
	}
	
	public static void resize(Actor i) {
		i.setWidth(i.getWidth() * Config.scaleX);
		i.setHeight(i.getHeight() * Config.scaleY);
		i.setOrigin(Align.center);
	}
}
