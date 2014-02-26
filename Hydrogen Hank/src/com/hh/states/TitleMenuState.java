package com.hh.states;

import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.GameState;

public class TitleMenuState extends GameState {	
	
	public TitleMenuState(){
		
	}

	public void tick() {
	}

	public void render(Graphics g) {		
		g.drawImage(Game.artassets.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
    	g.drawImage(Game.artassets.mainTitle, (Game.WIDTH/2 - Game.artassets.mainTitle.getWidth()/2), 20, null);
    	g.drawImage(Game.artassets.newButton, (Game.WIDTH/2 - Game.artassets.newButton.getWidth()/2), Game.HEIGHT - 275, null);
    	g.drawImage(Game.artassets.scoresButton, (Game.WIDTH/2 - Game.artassets.scoresButton.getWidth()/2), Game.HEIGHT - 200, null);
    	g.drawImage(Game.artassets.creditsButton, (Game.WIDTH/2 - Game.artassets.creditsButton.getWidth()/2), Game.HEIGHT - 125, null);		
	}
}
