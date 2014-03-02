/**
 * 
 */
package com.hh.objects;

import java.awt.Graphics;

import com.hh.framework.GameObject;

/**
 * @author blinginbeckwith
 *
 */
public class Powerup extends GameObject {

	public enum PowerupType{
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
	public Powerup(float x, float y, int width, int height, PowerupType powerupType) {
		super(x, y, width, height, ObjectID.Powerup, ObjectLayer.hud);
		this.powerupType = powerupType;
		ALIVE = true;
	}

	
	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}
	
	public PowerupType getPowerupType(){return powerupType;}
	

}
