/**
 * 
 */
package com.hh.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hh.Game;
import com.hh.framework.GameObject;

/**
 * @author blinginbeckwith
 * 
 */
public class Powerup extends GameObject
{

	public enum PowerupType
	{
		BalloonPack, HydrogenTank, HydrogenMolecule
	}

	protected PowerupType powerupType;

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
		if (Game.debugOptions().contains("power"))
		{
			g2d.setColor(Color.black);
			g2d.draw(this.boundingBox());
		}
	}

	@Override
	public Rectangle boundingBox()
	{
		return new Rectangle((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
	}

	public PowerupType getPowerupType()
	{
		return powerupType;
	}

}
