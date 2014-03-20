package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.RenderHelper;
import com.hh.framework.Score;
import com.hh.framework.Score.ScoreType;
import com.hh.framework.ScoreKeeper;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.objects.ScoreTab;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Mar. 19, 2014 
 * Purpose: Defines the High Scores state for the game
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class HighScoresState extends GameState
{
	public static Handler handler = new Handler();
	private RenderHelper renderHelp = new RenderHelper();
	private ScoreKeeper scorekeeper;
	private Font font;
	private ArtAssets art;

	public HighScoresState()
	{
		int tabX = 250;
		scorekeeper = Game.getScoreKeeper();
		font = new Font("Arial", Font.PLAIN, 20);
		art = Game.getArtAssets();

		if (scorekeeper.getScores().size() == 0)
		{
			scorekeeper.addScore(new Score(100, ScoreType.OVERALL));
			scorekeeper.addScore(new Score(50, ScoreType.ALTITUDE));
			scorekeeper.addScore(new Score(50, ScoreType.TIME));
		}

		handler.addObject(new ScoreTab("Overall", scorekeeper.getScores(ScoreType.OVERALL), tabX, 90,
		    150, 64, true));
		handler.addObject(new ScoreTab("Altitude", scorekeeper.getScores(ScoreType.ALTITUDE),
		    tabX + 150, 90, 150, 64, false));
		handler.addObject(new ScoreTab("Flight Time", scorekeeper.getScores(ScoreType.TIME),
		    tabX + 300, 90, 150, 64, false));
	}

	public void tick()
	{
		handler.tick();
	}

	public void render(Graphics g)
	{
		g.setColor(new Color(109, 136, 253));
		g.fillRect(0, 0, Game.width, Game.height);

		g.drawImage(art.highScoreBG, 100, 100, 600, 425, null);
		handler.render(g);
	}
}
