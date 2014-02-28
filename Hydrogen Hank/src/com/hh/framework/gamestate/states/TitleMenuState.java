package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.Cloud;
import com.hh.objects.MenuButton;
import com.hh.objects.MenuButton.ButtonID;

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
    handler.addObject(new MenuButton(art.newButton, art.newButton2, art.newButton.getWidth()/2 - 20,
    		Game.HEIGHT - 50, Game.WIDTH/3 - 15, art.newButton.getHeight(), ButtonID.NEWGAME));
    
    handler.addObject(new MenuButton(art.scoresButton, art.scoresButton2, Game.WIDTH/2,
    		Game.HEIGHT - 50, Game.WIDTH/3 - 15, art.scoresButton.getHeight(),
        ButtonID.HIGHSCORE));
    
    handler.addObject(new MenuButton(art.creditsButton, art.creditsButton2, Game.WIDTH - art.creditsButton.getWidth()/2 + 20,
    		Game.HEIGHT - 50, Game.WIDTH/3 - 15, art.creditsButton.getHeight(),
        ButtonID.CREDITS));   
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
    g.drawImage(art.getSpriteFrame(spriteID.HANK, 0), hankFlyingPosition += 2,
        (int) (Game.HEIGHT - hankFlyingPosition), 150, 150, null);
    if (hankFlyingPosition > Game.WIDTH)
    {
      hankFlyingPosition = 0;
    }

    if (Game.manager.getFirstClass() == this.getClass())
    {
      handler.render(g);
      
      g.drawImage(art.mainTitle, (100), 20, Game.HEIGHT, (int) ((Game.HEIGHT)*.8), null);
    }
  }
}
