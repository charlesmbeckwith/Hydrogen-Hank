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
	private BufferedImage IMG;
	private ArtAssets art = Game.getArtAssets();

	public Ground(float x, float y, int width, int height)
	{
		super(x, y, width, height, BackgroundElementType.Ground, ObjectLayer.background);
		IMG = art.dirt;
		ALIVE = true;
	}

	@Override
	public void tick()
	{
	}

	@Override
	public void render(Graphics g)
	{
		if (ALIVE)
		{
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(IMG, (int) X, (int) Y, WIDTH, HEIGHT, null);
		}
		
		super.render(g);
	}
	
	@Override
	public Rectangle boundingBox(){
		return super.boundingBox();
	}

	public int getWidth()
	{
		return WIDTH;
	}

	public int getHeight()
	{
		return HEIGHT;
	}
}
