package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.hh.Game;
import com.hh.framework.RenderHelper;
import com.hh.framework.Score;
import com.hh.framework.Score.ScoreType;
import com.hh.framework.ScoreKeeper;
import com.hh.framework.gamestate.GameState;

public class HighScoresState extends GameState
{
	private RenderHelper renderHelp = new RenderHelper();
	private ScoreKeeper scorekeeper;
	private Font font;

	public HighScoresState()
	{
		scorekeeper = Game.getScoreKeeper();
		font = new Font("Arial", Font.PLAIN, 20);

		if (scorekeeper.getScores().size() == 0)
		{
			scorekeeper.addScore(new Score(100, ScoreType.OVERALL));
			scorekeeper.addScore(new Score(50, ScoreType.ALTITUDE));
			scorekeeper.addScore(new Score(50, ScoreType.TIME));
		}
	}

	public void tick()
	{

	}

	public void render(Graphics g)
	{
		int position = 100;

		g.setColor(new Color(109, 136, 253));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		for (Score score : scorekeeper.getScores())
		{
			renderHelp.outlinedText((Graphics2D) g, font, String.valueOf(score.getValue()), 0.9f,
			    Color.black, Color.white, 100, position);
			position += 50;
		}
	}
}
