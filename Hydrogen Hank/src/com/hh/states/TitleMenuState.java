package com.hh.states;

import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.GameState;
import com.hh.framework.Handler;
import com.hh.objects.MenuButton;

public class TitleMenuState extends GameState
{
  public static Handler handler;

  public TitleMenuState()
  {
    handler = new Handler();
    handler.addObject(new MenuButton(Game.artassets.newButton, Game.artassets.newButton2,
        Game.WIDTH / 2, Game.HEIGHT - 275, Game.artassets.newButton.getWidth(),
        Game.artassets.newButton.getHeight()));
    handler.addObject(new MenuButton(Game.artassets.scoresButton, Game.artassets.scoresButton2,
        Game.WIDTH / 2, Game.HEIGHT - 200, Game.artassets.scoresButton.getWidth(),
        Game.artassets.scoresButton.getHeight()));
    handler.addObject(new MenuButton(Game.artassets.creditsButton, Game.artassets.creditsButton2,
        Game.WIDTH / 2, Game.HEIGHT - 125, Game.artassets.creditsButton.getWidth(),
        Game.artassets.creditsButton.getHeight()));
  }

  public void tick()
  {
    handler.tick();
  }

  public void render(Graphics g)
  {
    g.drawImage(Game.artassets.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
    g.drawImage(Game.artassets.mainTitle,
        (Game.WIDTH / 2 - Game.artassets.mainTitle.getWidth() / 2), 20, null);

    handler.render(g);
  }
}
