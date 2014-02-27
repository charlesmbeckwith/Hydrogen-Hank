package com.hh.framework.gamestate.states;

import java.awt.Graphics;

import com.hh.Game;
import com.hh.framework.Handler;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.MenuButton;
import com.hh.objects.MenuButton.ButtonID;

public class TitleMenuAnimState extends GameState
{
  private int position = Game.HEIGHT;
  private ArtAssets art = Game.getArtAssets();
  public static Handler handler;
  public boolean scrollingTitlesRunning = true;
  public boolean hitRightSide = false;
  
  public TitleMenuAnimState(){
  	handler = new Handler();
  }
  
  public void tick()
  {
  }

  public void render(Graphics g)
  {
    g.drawImage(art.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);

    //Draw Hank animation
    //g.drawImage(art.getSpriteFrame(spriteID.HANK, 0), hankFlyingPosition+=2, (int) ( Game.HEIGHT - hankFlyingPosition), 150, 150, null);
    //if(hankFlyingPosition > Game.WIDTH){
    //	hankFlyingPosition = 0;
    //}    	
    
    if(scrollingTitlesRunning){
	    g.drawImage(art.mainTitle,
	        (Game.WIDTH / 2 - art.mainTitle.getWidth() / 2), position + 20, null);
	    g.drawImage(art.newButton,
	        (Game.WIDTH / 2 - art.newButton.getWidth() / 2), position + 300, null);
	    g.drawImage(art.scoresButton,
	        (Game.WIDTH / 2 - art.scoresButton.getWidth() / 2), position + 375, null);
	    g.drawImage(art.creditsButton,
	        (Game.WIDTH / 2 - art.creditsButton.getWidth() / 2), position + 450, null);
    }else{
    	g.drawImage(art.mainTitle,(Game.WIDTH / 2 - art.mainTitle.getWidth() / 2), 20, null);
    	handler.render(g);
    }    

    if (position <= 0)
    {
    	scrollingTitlesRunning = false;
    	
        handler.addObject(new MenuButton(art.newButton, art.newButton2,
            Game.WIDTH / 2, Game.HEIGHT - 275, art.newButton.getWidth(),
            art.newButton.getHeight(),ButtonID.NEWGAME));
        handler.addObject(new MenuButton(art.scoresButton, art.scoresButton2,
            Game.WIDTH / 2, Game.HEIGHT - 200, art.scoresButton.getWidth(),
            art.scoresButton.getHeight(),ButtonID.HIGHSCORE));
        handler.addObject(new MenuButton(art.creditsButton, art.creditsButton2,
            Game.WIDTH / 2, Game.HEIGHT - 125, art.creditsButton.getWidth(),
            art.creditsButton.getHeight(),ButtonID.CREDITS));
        
        Game.manager.pop();
    }else{
    	position -= 3;
    }
  }

}
