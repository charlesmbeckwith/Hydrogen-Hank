package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.MenuButton;
import com.hh.objects.MenuButton.ButtonID;
import com.hh.objects.bg.Cloud;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Mar. 19, 2014 
 * Purpose: Defines the Main Menu state
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class TitleMenuState extends GameState
{
  public static Handler handler;
  private ArtAssets art = Game.getArtAssets();
  private int hankFlyingPosition = 0;
  private BufferedImage hank;
  private int flip;

  public TitleMenuState()
  {
    handler = new Handler();
    hank = art.getSpriteFrame(spriteID.HANK2, 0);
    flip = 0;

    // Adding Buttons
    int xOffset = Game.width / 2;
    int yOffset = Game.height / 3 - 150 + 350;
    handler.addObject(new MenuButton("New Game", xOffset, yOffset, Game.width / 3 - 15, 64,
        ButtonID.NEWGAME));
    yOffset += 75;
    handler.addObject(new MenuButton("High Scores", xOffset, yOffset, Game.width / 3 - 15, 64,
        ButtonID.HIGHSCORE));
    yOffset += 75;
    handler.addObject(new MenuButton("Credits", xOffset, yOffset, Game.width / 3 - 15, 64,
        ButtonID.CREDITS));

    // Adding Animated Clouds
    handler.addObject(new Cloud(800, 20, 256, 128, new Vector2D(-120, 0), true, true));
    handler.addObject(new Cloud(100, 100, 256, 128, new Vector2D(-100, 0), true, true));
    handler.addObject(new Cloud(450, 225, 256, 128, new Vector2D(-50, 0), true, true));
    handler.addObject(new Cloud(600, 300, 256, 128, new Vector2D(-75, 0), true, true));
    handler.addObject(new Cloud(800, 510, 256, 128, new Vector2D(-85, 0), true, true));
    handler.addObject(new Cloud(250, 430, 256, 128, new Vector2D(-100, 0), true, true));
  }

  public void tick()
  {
    handler.tick();
    flip++;
    if (flip == 10 || flip == 20)
    {
      hank = art.getSpriteFrame(spriteID.HANK2, 1);
    } else if (flip == 30)
    {
      hank = art.getSpriteFrame(spriteID.HANK2, 0);
      flip = 0;
    }
  }

  public void render(Graphics g)
  {
    if (Game.manager.getFirstClass() == this.getClass())
    {
      g.setColor(new Color(109, 136, 253));
      g.fillRect(0, 0, Game.width, Game.height);
      g.drawImage(art.mainTitle, Game.width / 2 - 250, Game.height / 3 - 150, 500, 300, null);
      handler.render(g);
    }

    // Draw Hank animation
    /*g.drawImage(art.getSpriteFrame(spriteID.BALLOON, 3), hankFlyingPosition - 12,
        (int) (Game.height - hankFlyingPosition) - 79, 73, 100, null);
    g.drawImage(art.getSpriteFrame(spriteID.BALLOON, 2), hankFlyingPosition + 9,
        (int) (Game.height - hankFlyingPosition) - 83, 73, 100, null);
    g.drawImage(art.getSpriteFrame(spriteID.BALLOON, 1), hankFlyingPosition,
        (int) (Game.height - hankFlyingPosition) - 83, 73, 100, null);

    g.drawImage(hank, hankFlyingPosition += 2, (int) (Game.height - hankFlyingPosition), 150, 150,
        null);

    if (hankFlyingPosition > Game.width)
    {
      hankFlyingPosition = 0;
    }*/
  }
}
