package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.hh.Game;
import com.hh.framework.RenderHelper;
import com.hh.framework.gamestate.GameState;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Mar. 19, 2014 
 * Purpose: Defines the Loading state for the game
 * 
 * @author Mark Schlottke
 */
public class LoadState extends GameState
{
	private RenderHelper renderHelp;
	private boolean loading;
	private Font font;

	public LoadState()
	{
		renderHelp = new RenderHelper();
		loading = false;
		font = new Font("Arial", Font.BOLD, 32);
	}

	public void tick()
	{
	}

	public void render(Graphics g)
	{
		g.setColor(new Color(109, 136, 253));
		g.fillRect(0, 0, Game.width, Game.height);
		FontMetrics metrics = g.getFontMetrics(font);
		int textX = (int) (Game.width / 2 - (metrics.stringWidth("Loading") / 2));
		int textY = (int) Game.height / 2 + (metrics.getHeight());
		renderHelp.outlinedText((Graphics2D) g, font, "Loading", 1.25f, Color.black, Color.white,
		    textX, textY);

		if (!loading)
		{
			Game.getArtAssets().load();
			loading = true;
		}
	}

}
