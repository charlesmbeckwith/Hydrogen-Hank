/**
 * 
 */
package com.hh.objects.bg;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;

import com.hh.Game;
import com.hh.framework.GameObject;
import com.hh.graphics.Animation;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.Powerup;

/**
 * @author blinginbeckwith
 *
 */
public class Explosion extends Powerup
{
	private Animation animation;
	private int ticker;

	private ArtAssets art;
	  public Explosion(float x, float y, int width, int height)
	  {
	    super(x, y, width, height, PowerupType.HydrogenTank);
	
	    art = Game.getArtAssets();
		initAnimation();

	  }


	@Override
	public void tick()
	{
		
		animation.runAnimation();
		ticker++;
		if(ticker >25){
			kill();
		}
		
	}


	@Override
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(animation.getAnimationFrame(), (int) center.getX(),
				(int) center.getY(), (int) width, (int) height, null);
		g2d.dispose();
		
	}


	@Override
	public Area boundingBox()
	{
		// TODO Auto-generated method stub
		return new Area(super.boundingBox());
	}
	
	private void initAnimation()
	  {
	    animation = new Animation(1, spriteID.EXPLOSION);
		//animation = new Animation(3, art.getSpriteFrame(spriteID.EXPLOSION, 0));
	  }
}
