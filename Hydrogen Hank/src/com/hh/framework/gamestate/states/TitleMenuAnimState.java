package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.GameObject;
import com.hh.framework.Handler;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.objects.MenuButton;
import com.hh.objects.MenuButton.ButtonID;
import com.hh.objects.bg.Cloud;

public class TitleMenuAnimState extends GameState
{
	private int position = Game.HEIGHT;
	private ArtAssets art = Game.getArtAssets();
	public static Handler handler;
	public boolean scrollingTitlesRunning = true;
	public boolean hitRightSide = false;

	public TitleMenuAnimState()
	{
		handler = new Handler();

		// Adding Buttons
		int xOffset = Game.WIDTH / 2;
		int yOffset = Game.HEIGHT / 3 - 150 + 350;
		handler.addObject(new MenuButton("New Game", xOffset, position + yOffset, Game.WIDTH / 3 - 15,
		    64, ButtonID.NEWGAME));
		yOffset += 75;
		handler.addObject(new MenuButton("High Scores", xOffset, position + yOffset,
		    Game.WIDTH / 3 - 15, 64, ButtonID.HIGHSCORE));
		yOffset += 75;
		handler.addObject(new MenuButton("Credits", xOffset, position + yOffset, Game.WIDTH / 3 - 15,
		    64, ButtonID.CREDITS));

		// Adding Animated Clouds
		handler.addObject(new Cloud(800, 20, 256, 128, new Vector2D(0, 0), true, true));
		handler.addObject(new Cloud(100, 100, 256, 128, new Vector2D(0, 0), true, true));
		handler.addObject(new Cloud(450, 225, 256, 128, new Vector2D(0, 0), true, true));
		handler.addObject(new Cloud(600, 300, 256, 128, new Vector2D(0, 0), true, true));
		handler.addObject(new Cloud(800, 510, 256, 128, new Vector2D(0, 0), true, true));
		handler.addObject(new Cloud(250, 430, 256, 128, new Vector2D(0, 0), true, true));
	}

	public void tick()
	{
		for (GameObject go : handler.getObjects())
		{
			if (go.getClass() == MenuButton.class)
			{
				go.setY(go.getY() - 3);
			}
		}
	}

	public void render(Graphics g)
	{
		g.setColor(new Color(109, 136, 253));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		if (scrollingTitlesRunning)
		{
			int yOffset = Game.HEIGHT / 3 - 150;
			g.drawImage(art.mainTitle, Game.WIDTH / 2 - 250, position + yOffset, 500, 300, null);
			handler.render(g);
		}

		if (position <= 0)
		{
			scrollingTitlesRunning = false;
			Game.manager.pop();
		}
		else
		{
			position -= 3;
		}
	}

}
