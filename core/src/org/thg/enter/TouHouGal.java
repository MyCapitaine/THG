package org.thg.enter;

import org.thg.logic.THG;
import org.thg.ui.GLoadingMenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TouHouGal extends Game {
	
	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true);
		setScreen(new GLoadingMenu());
		THG.setGame(this);
	}

}
