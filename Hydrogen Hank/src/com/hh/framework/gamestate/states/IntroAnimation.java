package com.hh.framework.gamestate.states;

import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;

public class IntroAnimation extends GameState{
	 private ArtAssets art = Game.getArtAssets();
	 private float hankPosition = 100;
	 
	@Override
	public void tick() {
		  hankPosition-=1;

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(art.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.drawImage(art.getSpriteFrame(spriteID.HANK, 0), (int) hankPosition, (int) ( Game.HEIGHT /2), 500, 500, null);
		
		
	}

	
}
