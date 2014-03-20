/**
 * 
 */
package com.hh.objects.powerups;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.hh.objects.Powerup;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 28, 2014 
 * Last Updated : Feb. 28, 2014 
 * Purpose: Defines a balloon pack powerup
 * 
 * @author Charlie Beckwith
 */
public class BalloonPack extends Powerup
{

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param powerupType
	 */
	public BalloonPack(float x, float y, int width, int height)
	{
		super(x, y, width, height, PowerupType.BalloonPack);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void tick()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g)
	{
		// TODO Auto-generated method stub

		super.render(g);
	}
	
	@Override
	public Rectangle boundingBox(){
		return super.boundingBox();
	}
}
