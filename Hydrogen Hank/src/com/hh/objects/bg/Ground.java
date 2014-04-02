package com.hh.objects.bg;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.Collidable;
import com.hh.graphics.ArtAssets;
import com.hh.objects.BackgroundElement;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Feb. 25, 2014 
 * Purpose: Defines the player
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class Ground extends BackgroundElement implements Collidable
{
	private BufferedImage img;
	private ArtAssets art = Game.getArtAssets();

	public Ground(float x, float y, int width, int height)
	{
		super(x, y, width, height, BackgroundElementType.Ground, ObjectLayer.background);
		img = art.dirt;
		alive = true;
	}

	@Override
	public void tick()
	{
	}

	@Override
	public void render(Graphics g)
	{
		if (alive)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(img, (int) x, (int) y, (int) width, (int) height, null);
		}

		super.render(g);
	}

	@Override
	public Area boundingBox()
	{
		return super.boundingBox();
	}
}
