package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.objects.Cloud;

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

    // Adding Animated Clouds
    handler.addObject(new Cloud(800, 20, 256, 128, new Vector2D(0, 0), true));
    handler.addObject(new Cloud(100, 100, 256, 128, new Vector2D(0, 0), true));
    handler.addObject(new Cloud(450, 225, 256, 128, new Vector2D(0, 0), true));
    handler.addObject(new Cloud(600, 300, 256, 128, new Vector2D(0, 0), true));
    handler.addObject(new Cloud(800, 510, 256, 128, new Vector2D(0, 0), true));
    handler.addObject(new Cloud(250, 430, 256, 128, new Vector2D(0, 0), true));
  }

  public void tick()
  {
  }

  public void render(Graphics g)
  {
    g.setColor(new Color(109, 136, 253));
    g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT); 	

    if (scrollingTitlesRunning)
    {
      handler.render(g);
      g.drawImage(art.mainTitle, (Game.WIDTH / 2 - art.mainTitle.getWidth() / 2), position + 20, Game.HEIGHT, (int) ((Game.HEIGHT/2)*.8),
          null);
      g.drawImage(art.newButton, (Game.WIDTH / 2 - art.newButton.getWidth() / 2), position + 300,
          null);
      g.drawImage(art.scoresButton, (Game.WIDTH / 2 - art.scoresButton.getWidth() / 2),
          position + 375, null);
      g.drawImage(art.creditsButton, (Game.WIDTH / 2 - art.creditsButton.getWidth() / 2),
          position + 450, null);
       
    }

    if (position <= 0)
    {
      scrollingTitlesRunning = false;
      Game.manager.pop();
    } else
    {
      position -= 3;
    }
  }

}
