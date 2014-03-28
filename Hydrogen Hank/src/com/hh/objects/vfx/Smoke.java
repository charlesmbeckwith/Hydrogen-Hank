/**
 * 
 */
package com.hh.objects.vfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;

import com.hh.framework.GameObject.ObjectLayer;
import com.hh.graphics.Animation;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.Powerup;
import com.hh.objects.VisualEffect;

/**
 * @author blinginbeckwith
 *
 */
public class Smoke extends VisualEffect
{
	private Animation animation;
	private int ticker;

	  public Smoke(float x, float y, int width, int height, ObjectLayer layer)
	  {
			 super(x, y, width, height, layer);

	
	    
		initAnimation();

	  }


	@Override
	public void tick()
	{
		
		animation.runAnimation();

		if(animation.finished()){
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
		return new Area(super.boundingBox());
	}
	
	private void initAnimation()
	  {
	    animation = new Animation(1, spriteID.SMOKE);
		
	  }
}
