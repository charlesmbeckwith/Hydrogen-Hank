/**
 * 
 */
package com.hh.objects.enemies;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.GameTime;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.states.PlayState;
import com.hh.graphics.Animation;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.Enemy;
import com.hh.objects.vfx.Smoke;
import com.hh.sound.SoundManager.SoundFile;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Mar. 5, 2014 Last Updated : Mar. 19, 2014 Purpose: Defines a plane
 * 
 * @author Mark Schlottke
 */
public class Plane extends Enemy
{
	private Animation anim;
	private ArtAssets art;
	private int smokeCounter;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param v
	 */
	public Plane(float x, float y, int width, int height, Vector2D v)
	{
		super(x, y, width, height, v, EnemyType.Plane);
		art = Game.getArtAssets();
		initAnimations();
		alive = true;
	}

	@Override
	public void tick()
	{
		if (isVisible())
		{
			Game.soundManager.playAudioClip(SoundFile.helicopter);
			System.out.println("Helicopter Sound called");
		}

		x += v.dx * GameTime.delta();
		anim.runAnimation();
		smokeCounter++;
		if (smokeCounter == 15)
		{
			// addSmoke
			PlayState.handler.addObject(new Smoke(x + width/2-15,
					y-height/2, 32, 32, ObjectLayer.middleground));
			smokeCounter = 0;
		}
	}

	@Override
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		if (alive)
		{
			g2d.drawImage(anim.getAnimationFrame(), (int) center.getX(),
					(int) center.getY(), (int) width, (int) height, null);

		}

		super.render(g);
	}

	@Override
	public Area boundingBox()
	{
		float offsetX = width * 0.08f;
		float offsetY = height * 0.13f;

		return new Area(new Rectangle((int) (center.getX() + offsetX),
				(int) (center.getY() + offsetY),
				(int) (width - offsetX * 2), (int) (height - offsetY * 2)));
	}

	/**
	 * initialize Animations
	 */
	private void initAnimations()
	{
		Random rand = new Random();

		switch (rand.nextInt(4))
		{
		case 1:
			anim = new Animation(1, art.getSpriteFrame(spriteID.PLANES, 4),
					art.getSpriteFrame(spriteID.PLANES, 5),
					art.getSpriteFrame(spriteID.PLANES, 6),
					art.getSpriteFrame(spriteID.PLANES, 7));
			break;
		case 2:
			anim = new Animation(1, art.getSpriteFrame(spriteID.PLANES, 8),
					art.getSpriteFrame(spriteID.PLANES, 9),
					art.getSpriteFrame(spriteID.PLANES, 10),
					art.getSpriteFrame(spriteID.PLANES, 11));
			break;
		case 3:
			anim = new Animation(1,
					art.getSpriteFrame(spriteID.PLANES, 12),
					art.getSpriteFrame(spriteID.PLANES, 13),
					art.getSpriteFrame(spriteID.PLANES, 14),
					art.getSpriteFrame(spriteID.PLANES, 15));
			break;
		default:
			anim = new Animation(1, art.getSpriteFrame(spriteID.PLANES, 0),
					art.getSpriteFrame(spriteID.PLANES, 1),
					art.getSpriteFrame(spriteID.PLANES, 2),
					art.getSpriteFrame(spriteID.PLANES, 3));
			break;
		}
	}

}
