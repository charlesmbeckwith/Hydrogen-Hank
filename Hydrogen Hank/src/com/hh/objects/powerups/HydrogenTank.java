/**
 * 
 */
package com.hh.objects.powerups;

import java.awt.Graphics;
import java.awt.geom.Area;

import com.hh.objects.Powerup;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 28, 2014 
 * Last Updated : Feb. 28, 2014 
 * Purpose: Defines a hydrogen tank powerup
 * 
 * @author Charlie Beckwith
 */
public class HydrogenTank extends Powerup
{

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param powerupType
	 */
	public HydrogenTank(float x, float y, int width, int height)
	{
		super(x, y, width, height, PowerupType.HydrogenTank);
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
	public Area boundingBox(){
		return new Area( super.boundingBox());
	}

}
