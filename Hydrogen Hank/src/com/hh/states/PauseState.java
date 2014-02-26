package com.hh.states;

import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.GameState;

public class PauseState extends GameState {

	public void tick() {
	}

	public void render(Graphics g) {
		Game.playState.render(g);
		g.drawImage(Game.artassets.pauseScreen,
				(Game.WIDTH / 2 - Game.artassets.pauseScreen.getWidth() / 2),
				(Game.HEIGHT / 2 - 150), null);
	}

}
