package com.hh.states;

import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.GameState;
import com.hh.framework.Handler;
import com.hh.graphics.ArtAssets;
import com.hh.objects.MenuButton;
import com.hh.objects.MenuButton.ButtonID;

public class TitleMenuState extends GameState
{
  public static Handler handler;
  private ArtAssets art = Game.getArtAssets();

  public TitleMenuState()
  {
    handler = new Handler();
    handler.addObject(new MenuButton(art.newButton, art.newButton2,
        Game.WIDTH / 2, Game.HEIGHT - 275, art.newButton.getWidth(),
        art.newButton.getHeight(),ButtonID.NEWGAME));
    handler.addObject(new MenuButton(art.scoresButton, art.scoresButton2,
        Game.WIDTH / 2, Game.HEIGHT - 200, art.scoresButton.getWidth(),
        art.scoresButton.getHeight(),ButtonID.HIGHSCORE));
    handler.addObject(new MenuButton(art.creditsButton, art.creditsButton2,
        Game.WIDTH / 2, Game.HEIGHT - 125, art.creditsButton.getWidth(),
        art.creditsButton.getHeight(),ButtonID.CREDITS));
  }

  public void tick()
  {
    handler.tick();
  }

  public void render(Graphics g)
  {
    g.drawImage(art.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
    g.drawImage(art.mainTitle,
        (Game.WIDTH / 2 - art.mainTitle.getWidth() / 2), 20, null);

    handler.render(g);
  }
}
