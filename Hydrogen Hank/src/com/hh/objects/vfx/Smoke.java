/**
 * COSC3550 Spring 2014 Created : March. 27, 2014 Last Updated : Mar. 31, 2014
 * Purpose: Smoke visual effect
 * 
 * @author Charlie Beckwith
 */
package com.hh.objects.vfx;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;

import com.hh.graphics.Animation;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.VisualEffect;

/**
 * @author blinginbeckwith
 *
 */
public class Smoke extends VisualEffect
{
	private Animation animation;
	private float opacity = 1.0f;
	  public Smoke(float x, float y, int width, int height, ObjectLayer layer)
	  {
			 super(x, y, width, height, layer);

	
	    
		initAnimation();

	  }


	@Override
	public void tick()
	{
		
		animation.runAnimation();
		if(opacity > .03)
			opacity-=.03;
		if(animation.finished()){
			kill();
		}
		
	}


	@Override
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
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
