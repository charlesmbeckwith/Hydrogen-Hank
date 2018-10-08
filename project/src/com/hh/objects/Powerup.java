/**
 * 
 */
package com.hh.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;

import com.hh.framework.Collidable;
import com.hh.framework.DebugManager;
import com.hh.framework.GameObject;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 28, 2014 
 * Last Updated : Feb. 28, 2014 
 * Purpose: Defines a powerup
 * 
 * @author Charlie Beckwith
 */
public abstract class Powerup extends GameObject implements Collidable
{

	public enum PowerupType
	{
		BalloonPack, HydrogenTank, HydrogenMolecule
	}

	protected PowerupType powerupType;
	protected Double VALUE;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param layer
	 */
	public Powerup(float x, float y, int width, int height, PowerupType powerupType)
	{
		super(x, y, width, height, ObjectID.Powerup, ObjectLayer.middleground);
		this.powerupType = powerupType;
		alive = true;
	}
	public Powerup(float x, float y, int width, int height, PowerupType powerupType, ObjectLayer layer)
	{
		super(x, y, width, height, ObjectID.Powerup, layer);
		this.powerupType = powerupType;
		alive = true;
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
		Graphics2D g2d = (Graphics2D) g;
		if (DebugManager.showBounds)
		{
			g2d.setColor(Color.black);
			g2d.draw(this.boundingBox());
		}
	}

	@Override
	public Area boundingBox()
	{
		return new Area(new Rectangle((int) center.getX(), (int) center.getY(), (int) width, (int) height));
	}

	public PowerupType getPowerupType()
	{
		return powerupType;
	}
	
	public abstract double getValue();

}
