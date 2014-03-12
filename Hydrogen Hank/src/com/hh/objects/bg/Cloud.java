package com.hh.objects.bg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.graphics.ArtAssets;
import com.hh.objects.BackgroundElement;

/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke, Charlie Beckwith
 */
public class Cloud extends BackgroundElement
{
	private BufferedImage IMG;
	private ArtAssets art;
	private static Random rand = new Random();
	private boolean WRAP;

	public Cloud(float x, float y, int width, int height, boolean background)
	{
		// TODO: If we want to have particles/clouds in the foreground we should
		// make sure they're bigger and maybe more atmospheric?
		// TODO: Make another semi transparent fog overlay that comes in like a
		// cloud. For now I'm going to take it out, though. As I thought there
		// was a problem with my sorting algorithm :p

		super(x, y, width, height, BackgroundElementType.Cloud, (background ? ObjectLayer.background
		    : ObjectLayer.foreground));

		init(false);
	}

	public Cloud(float x, float y, int width, int height, Vector2D v, boolean background, boolean wrap)
	{
		super(x, y, width, height, v, BackgroundElementType.Cloud, (background ? ObjectLayer.background
		    : ObjectLayer.foreground));

		init(wrap);
	}

	public void init(boolean wrap)
	{
		art = Game.getArtAssets();
		IMG = art.cloud;
		ALIVE = true;
		WRAP = wrap;

		if (!WRAP)
		{
			double offset = rand.nextDouble() / 2;
			if (LAYER == ObjectLayer.foreground)
			{
				WIDTH *= (1 + offset);
				HEIGHT *= (1 + offset);
			}
			else
			{
				WIDTH *= (1 - offset);
				HEIGHT *= (1 - offset);
			}
		}
	}

	@Override
	public void tick()
	{
		X += V.DX * GameTime.delta();

		if (WRAP && X + WIDTH < 0)
		{
			X = 0 + Game.WIDTH;
		}
	}

	@Override
	public void render(Graphics g)
	{
		if (ALIVE)
		{
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
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
