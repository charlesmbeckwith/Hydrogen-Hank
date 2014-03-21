package com.hh.objects.bg;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.graphics.ArtAssets;
import com.hh.objects.BackgroundElement;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Feb. 28, 2014 
 * Purpose: Defines the ground
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class Cloud extends BackgroundElement
{
	private BufferedImage img;
	private ArtAssets art;
	private static Random rand = new Random();
	private boolean wrap;

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
		img = art.cloud;
		alive = true;
		this.wrap = wrap;

		if (!wrap)
		{
			double offset = rand.nextDouble() / 2;
			if (layer == ObjectLayer.foreground)
			{
				width *= (1 + offset);
				height *= (1 + offset);
			}
			else
			{
				width *= (1 - offset);
				height *= (1 - offset);
			}
		}
	}

	@Override
	public void tick()
	{
		x += v.dx * GameTime.delta();

		if (wrap && x + width < 0)
		{
			x = 0 + Game.width;
		}
	}

	@Override
	public void render(Graphics g)
	{
		if (alive)
		{
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
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
