package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.MenuButton;
import com.hh.objects.MenuButton.ButtonID;
import com.hh.objects.bg.Cloud;

public class TitleMenuState extends GameState
{
  public static Handler handler;
  private ArtAssets art = Game.getArtAssets();
  private int hankFlyingPosition = 0;

  public TitleMenuState()
  {
    handler = new Handler();
    // Adding Animated Clouds
    handler.addObject(new Cloud(800, 20, 256, 128, new Vector2D(-120, 0), true));
    handler.addObject(new Cloud(100, 100, 256, 128, new Vector2D(-100, 0), true));
    handler.addObject(new Cloud(450, 225, 256, 128, new Vector2D(-50, 0), true));
    handler.addObject(new Cloud(600, 300, 256, 128, new Vector2D(-75, 0), true));
    handler.addObject(new Cloud(800, 510, 256, 128, new Vector2D(-85, 0), true));
    handler.addObject(new Cloud(250, 430, 256, 128, new Vector2D(-100, 0), true));

    // Adding Buttons
    int xOffset = Game.WIDTH / 2;
    int yOffset = Game.HEIGHT / 3 - 150 + 350;
    handler.addObject(new MenuButton(art.newButton, art.newButton2, xOffset, yOffset,
        Game.WIDTH / 3 - 15, art.newButton.getHeight(), ButtonID.NEWGAME));
    yOffset += 75;
    handler.addObject(new MenuButton(art.scoresButton, art.scoresButton2, xOffset, yOffset,
        Game.WIDTH / 3 - 15, art.scoresButton.getHeight(), ButtonID.HIGHSCORE));
    yOffset += 75;
    handler.addObject(new MenuButton(art.creditsButton, art.creditsButton2, xOffset, yOffset,
        Game.WIDTH / 3 - 15, art.creditsButton.getHeight(), ButtonID.CREDITS));
  }

  public void tick()
  {
    handler.tick();
  }

  public void render(Graphics g)
  {
    if (Game.manager.getFirstClass() == this.getClass())
    {
      g.setColor(new Color(109, 136, 253));
      g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
      //g.drawImage(art.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
    }

    //Draw Hank animation
    g.drawImage(art.getSpriteFrame(spriteID.BALLOON, 3), hankFlyingPosition - 12,
        (int) (Game.HEIGHT - hankFlyingPosition) - 86, 73, 100, null);
    g.drawImage(art.getSpriteFrame(spriteID.BALLOON, 2), hankFlyingPosition + 9,
        (int) (Game.HEIGHT - hankFlyingPosition) - 90, 73, 100, null);
    g.drawImage(art.getSpriteFrame(spriteID.BALLOON, 1), hankFlyingPosition,
        (int) (Game.HEIGHT - hankFlyingPosition) - 90, 73, 100, null);
    g.drawImage(art.getSpriteFrame(spriteID.HANK, 0), hankFlyingPosition += 2,
        (int) (Game.HEIGHT - hankFlyingPosition), 150, 150, null);

    if (hankFlyingPosition > Game.WIDTH)
    {
      hankFlyingPosition = 0;
    }

    if (Game.manager.getFirstClass() == this.getClass())
    {
      handler.render(g);

      g.drawImage(art.mainTitle, Game.WIDTH / 2 - 250, Game.HEIGHT / 3 - 150, 500, 300, null);
    }
  }
}
