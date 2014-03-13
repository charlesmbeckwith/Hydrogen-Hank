package com.hh.objects.powerups;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hh.Game;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.Powerup;

/**
 * @author Charlie Beckwith
 * 
 */
public class HydrogenMolecule extends Powerup
{

	private BufferedImage IMG;
	private ArtAssets art;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param powerupType
	 */
	public HydrogenMolecule(float x, float y, int width, int height)
	{
		super(x, y, width, height, PowerupType.HydrogenMolecule);
		art = Game.getArtAssets();
		Random rand = new Random();
		int frame = rand.nextInt(5);
		IMG = art.getSpriteFrame(spriteID.HYDROGEN, frame);

	}

	@Override
	public void tick()
	{

	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(IMG, (int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height, null);

		super.render(g);
	}

	@Override
	public Rectangle boundingBox()
	{
		return super.boundingBox();
	}

}