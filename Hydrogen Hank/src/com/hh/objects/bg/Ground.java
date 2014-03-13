package com.hh.objects.bg;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.graphics.ArtAssets;
import com.hh.objects.BackgroundElement;

/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke
 */
public class Ground extends BackgroundElement
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
	public Rectangle boundingBox()
	{
		return super.boundingBox();
	}
}
