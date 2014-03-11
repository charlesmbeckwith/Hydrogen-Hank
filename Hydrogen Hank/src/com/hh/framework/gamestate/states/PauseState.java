package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.hh.Game;
import com.hh.framework.RenderHelper;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;

public class PauseState extends GameState
{
	private ArtAssets art = Game.getArtAssets();
	private RenderHelper renderHelp = new RenderHelper();
	private float boxHeight = 0;
	private float boxWidth = 0;

	public void tick()
	{
		if (boxHeight < Game.HEIGHT / 2)
			boxHeight += Game.HEIGHT / 25;
		else
			boxHeight = Game.HEIGHT;

		if (boxWidth < Game.WIDTH / 2)
			boxWidth += Game.WIDTH / 25;
		else
			boxWidth = Game.WIDTH;
	}

	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		// TODO: Find a better fix for the shaky pause screen...
		Game.playState.render(g); // Solves the shaky pause screen issue

		renderHelp.tintedBox(g2d, Color.black, 0.9f, (int) (Game.WIDTH / 2 - boxWidth),
		    (int) (Game.HEIGHT / 2 - boxHeight), (int) (boxWidth * 2), (int) (boxHeight * 2));

		if (boxWidth >= Game.WIDTH / 2 && boxHeight >= Game.HEIGHT / 2)
		{
			g.drawImage(art.pauseScreen, (Game.WIDTH / 2 - art.pauseScreen.getWidth() / 2),
			    (Game.HEIGHT / 2 - 150), null);
		}
	}

}
