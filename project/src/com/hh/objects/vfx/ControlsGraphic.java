/**
 * COSC3550 Spring 2014 Created : March. 27, 2014 Last Updated : Mar. 31, 2014
 * Purpose: Smoke visual effect
 * 
 * @author Charlie Beckwith
 */
package com.hh.objects.vfx;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import com.hh.Game;
import com.hh.objects.VisualEffect;

/**
 * @author blinginbeckwith
 *
 */
public class ControlsGraphic extends VisualEffect
{

	private static int width = 800;
	private static int height = 600;
	  public ControlsGraphic(float x, float y, ObjectLayer layer)
	  {
			 super(x, y, width, height, layer);

	  }


	@Override
	public void tick()
	{
		
		
			//kill();
		
	}


	@Override
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(Game.getArtAssets().controls, (int) x - 50,
				(int) y+40, (int) width*3/4, (int) height*3/4, null);
		g2d.dispose();
		
	}


	@Override
	public Area boundingBox()
	{
		return new Area(super.boundingBox());
	}
	

}
