package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.GameObject;
import com.hh.framework.Handler;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.objects.Cloud;
import com.hh.objects.MenuButton;
import com.hh.objects.MenuButton.ButtonID;

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
  }

  public void render(Graphics g)
  {
    g.setColor(new Color(109, 136, 253));
    g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT); 	
    
    for(GameObject go : handler.getObjects()){
      if(go.getClass() == MenuButton.class){
        go.setY(position+go.getY());        
      }
    }

    if (scrollingTitlesRunning)
    {
      handler.render(g);
      int yOffset = art.newButton.getHeight();
      
      //g.drawImage(art.newButton, 20, Game.HEIGHT - yOffset - 20, Game.WIDTH/3 - 15, yOffset, null);
      //g.drawImage(art.scoresButton, Game.WIDTH/2, Game.HEIGHT - 50, null);
      //g.drawImage(art.creditsButton, Game.WIDTH - art.creditsButton.getWidth()/2 + 20, Game.HEIGHT - 50, null);
      
      g.drawImage(art.mainTitle, (100), 20, Game.HEIGHT, position + (int) ((Game.HEIGHT)*.8), null);
       
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
