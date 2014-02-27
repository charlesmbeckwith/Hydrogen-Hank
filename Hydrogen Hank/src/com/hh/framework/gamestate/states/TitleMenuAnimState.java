package com.hh.framework.gamestate.states;

import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;

public class TitleMenuAnimState extends GameState
{

  private int position = Game.HEIGHT;
  private ArtAssets art = Game.getArtAssets();

  public void tick()
  {
    // TODO Auto-generated method stub

  }

  public void render(Graphics g)
  {
    g.drawImage(art.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);


    g.drawImage(art.mainTitle,
        (Game.WIDTH / 2 - art.mainTitle.getWidth() / 2), position + 20, null);
    g.drawImage(art.newButton,
        (Game.WIDTH / 2 - art.newButton.getWidth() / 2), position + Game.HEIGHT - 275,

        null);

    g.drawImage(art.scoresButton,
        (Game.WIDTH / 2 - art.scoresButton.getWidth() / 2),
        position + Game.HEIGHT - 200, null);
    g.drawImage(art.creditsButton,
        (Game.WIDTH / 2 - art.creditsButton.getWidth() / 2), position + Game.HEIGHT
            - 125, null);


    position -= 3;

    if (position <= 0)
    {
      Game.manager.STATES.pop();
    }
  }

}
