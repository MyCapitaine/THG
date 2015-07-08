package com.mygdx.game;

import org.thg.logic.THG;
import org.thg.ui.GLoadingMenu;

import com.badlogic.gdx.Game;

public class DemoGame extends Game {
	
	@Override
	public void create () {
		setScreen(new GLoadingMenu());
		THG.setGame(this);
	}

}
