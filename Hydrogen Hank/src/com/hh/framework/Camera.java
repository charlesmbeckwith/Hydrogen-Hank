package com.hh.framework;

import com.hh.Game;
import com.hh.input.KeyBinding;
import com.hh.input.KeyInput;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 7, 2014 
 * Last Updated : Mar. 19, 2014 
 * Purpose: Simulates a camera
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class Camera
{
	private float x, y;
	private float xOffset, yOffset;

	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
		this.xOffset = Game.width / 3;
		this.yOffset = Game.height * 5 / 9;
	}

	public void tick(GameObject go)
	{
		if (KeyInput.keysDown.contains(KeyBinding.PAN_LEFT.VALUE()))
		{
			panLeft();
		}

		if (KeyInput.keysDown.contains(KeyBinding.PAN_RIGHT.VALUE()))
		{
			panRight();
		}

		x = -go.getX() + xOffset;
		y = -go.getY() + yOffset;

		// Hits left side of screen
		if (x < (-go.getX() + go.getWidth() / 2))
		{
			x = (-go.getX() + go.getWidth() / 2);
		}
		// Hits right side of the screen
		else if (x > ((-go.getX() - go.getWidth()) + Game.width))
		{
			x = ((-go.getX() - go.getWidth()) + Game.width);
		}
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getXOffset()
	{
		return xOffset;
	}

	public float getYOffset()
	{
		return yOffset;
	}

	public void panRight()
	{
		xOffset -= 2;

		if (xOffset < 0)
		{
			xOffset = 0;
		}
	}

	public void panLeft()
	{
		xOffset += 2;

		if (xOffset > 800)
		{
			xOffset = 800;
		}
	}
}
