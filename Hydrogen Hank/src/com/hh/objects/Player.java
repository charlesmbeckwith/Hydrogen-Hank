package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.framework.gamestate.states.PlayState;
import com.hh.framework.gamestate.states.TitleMenuAnimState;
import com.hh.framework.gamestate.states.TitleMenuState;
import com.hh.graphics.Animation;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.input.KeyBinding;
import com.hh.input.KeyInput;
import com.hh.objects.bg.Ground;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014 Last Updated : Feb. 11, 2014 Purpose: Defines the Dust Bunny
 * 
 * @author Mark Schlottke
 */
@SuppressWarnings("unused")
public class Player extends GameObject
{
	private final float gravity = 2f;
	private int extraBalloons = 50; // How many balloons you start with
	private final int balloonCost = 10; // How much hydrogen blowing up a
	// balloon costs.
	private LinkedList<Balloon> balloons = new LinkedList<Balloon>();
	private boolean balloonAlreadyBlownUp = false;
	private Animation normal, death, current;
	private ArtAssets art;
	private float buoyancy;
	private LinkedList<String> debugOptions;
	private boolean startDeath = false;
	private int deathCountdown = 100;

	private float hLevel;
	private float maxHLevel = 200f;

	public Player(float x, float y, int width, int height, Vector2D v)
	{
		super(x, y, width, height, v, ObjectID.Player, ObjectLayer.middleground);
		alive = true;
		buoyancy = 0.0f;
		hLevel = 200f;

		// balloons.add(new Balloon(x, y, width, height));

		art = Game.getArtAssets();
		initAnimations();
		current = normal;
	}

	@Override
	public void tick()
	{
		if (alive)
		{
			if (startDeath)
			{
				if (deathCountdown == 0)
				{
					kill();
				}

				deathCountdown--;
			}

			boolean collision = collision();

			if (KeyInput.keysDown.contains(KeyBinding.INFLATE.VALUE()))
			{
				if (hLevel > 0 && buoyancy > -390 && !balloons.isEmpty())
				{
					buoyancy -= 0.667f;
					hLevel -= 0.333;
				}
				else if (hLevel < 0)
				{
					hLevel = 0;
				}
			}

			if (KeyInput.keysDown.contains(KeyBinding.BLOWUP_BALLOON.VALUE()))
			{
				// TODO: FIX BUG WHERE BALLOONS ARE ADDED TOO FAST...
				if (extraBalloons > 0 && !balloonAlreadyBlownUp && hLevel > balloonCost
				    && balloons.size() < 3)
				{
					balloons.push(new Balloon(x - 12, y + 10, (int) (width / 2), (int) height));
					extraBalloons--;
					hLevel -= balloonCost;
					balloonAlreadyBlownUp = true;
				}
			}
			else
			{
				balloonAlreadyBlownUp = false;
			}

			if (KeyInput.keysDown.contains(KeyBinding.DEFLATE.VALUE()))
			{
				buoyancy += 1.67f;
			}

			if (buoyancy > 0)
			{
				buoyancy = 0;
			}
			if (buoyancy < -(gravity * 2))
			{
				buoyancy = -(gravity * 2);
				// Trying to make buoyancy reflect how many balloons you have.
				// Aka, the more balloons you have the less quickly you fall to
				// the ground
			}

			if (v.dx < 150 && !collision)
			{
				v.dx += 1;
			}

			v.dy += buoyancy + gravity;

			if (v.dy > 1200)
			{
				v.dy = 1200;
			}

			x += v.dx * GameTime.delta();
			y += v.dy * GameTime.delta();

			current.runAnimation();

			// Simulate a slow leak in the balloon
			buoyancy += 0.0111 / (balloons.size());
			for (Balloon bloon : balloons)
			{
				bloon.tick(x - 12, y + 10);
			}

		}
	}

	public boolean collision()
	{
		boolean col = false;

		for (GameObject go : PlayState.handler.getObjects())
		{
			if (go != this && go.getId() == ObjectID.Enemy && collided(go))
			{
				Enemy enemy = (Enemy) go;
				switch (enemy.getEnemyType())
				{
				case Bird:
					go.kill();
					destroyBalloon();
					break;
				case Plane:
					destroyBalloon();
					break;
				}

			}

			// check for ground collision
			if (go != this && go.getClass() == Ground.class && (y + (height / 2) - 14) >= go.getY()
			    && x > go.getX() && x < go.getX() + go.getWidth())
			{
				col = true;

				if (v.dy > 300 || (balloons.isEmpty() && hLevel < balloonCost) || hLevel == 0
				    || (extraBalloons == 0 && balloons.isEmpty()))
				{
					startKill();
				}

				y = (go.getY() - (height / 2) + 14);
				v.dy = 0;

				if (v.dx > 0)
					v.dx -= (v.dx * 0.01);
			}

			// Check for powerup collision
			if (go != this && go.getId() == ObjectID.Powerup && collided(go))
			{
				// cast go as Powerup
				Powerup pu = (Powerup) go;
				switch (pu.getPowerupType())
				{
				case BalloonPack:
					// Add an extra Balloon
					extraBalloons++;
					break;
				case HydrogenTank:
					// Add hydrogen to tank
					hLevel += 50;
					break;
				case HydrogenMolecule:
					hLevel += 1;
					go.kill();
					break;
				}

				if (hLevel > maxHLevel)
				{
					hLevel = maxHLevel;
				}
			}
		}

		return col;
	}

	public boolean collided(GameObject go)
	{
		return this.boundingBox().intersects(go.boundingBox());
	}

	@Override
	public void render(Graphics g)
	{
		if (alive)
		{
			Graphics2D g2d = (Graphics2D) g.create();

			// BufferedImage image = art.hueImg(CURRENT.getAnimationFrame(),
			// width, height, HUE);
			if (Game.debugOptions().contains("Info"))
			{
				g2d.setColor(Color.black);
				g2d.draw(this.boundingBox());
				debugOptions(g2d);
			}
			for (Balloon bloons : balloons)
			{
				bloons.render(g2d);
			}
			g2d.drawImage(current.getAnimationFrame(), (int) (x - (width / 2)), (int) (y - (height / 2)),
			    (int) width, (int) height, null);
		}
	}

	@Override
	public Rectangle boundingBox()
	{
		return new Rectangle((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
	}

	private void debugOptions(Graphics2D g2d)
	{
		initDebug();
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		float relativeZeroX = -PlayState.cam.getX() + 10;
		float relativeZeroY = -PlayState.cam.getY() + Game.height - (11 * (debugOptions.size() + 1));
		int row = 1;
		for (String x : debugOptions)
		{
			g2d.drawString(x, relativeZeroX, relativeZeroY + 11 * row);
			row++;
		}

	}

	public void destroyBalloon()
	{
		if (!balloons.isEmpty())
		{
			balloons.pop();
			if (balloons.isEmpty())
			{
				buoyancy = 0f;
			}
		}
		else
		{
			startKill();
		}
	}

	public void startKill()
	{
		current = death;
		startDeath = true;
	}

	@Override
	public void kill()
	{
		// TODO: Kill Hank - Game Over
		Game.manager.push(new TitleMenuState());
		Game.manager.push(new TitleMenuAnimState());
		Game.playState.restart = true;
		super.kill();
	}

	private void initDebug()
	{
		debugOptions = new LinkedList<String>();
		String BouyancyDebug = new String().concat("Bouyancy = " + (int) buoyancy);
		String HydrogenLevelDebug = new String().concat("Hydrogen Level = " + hLevel);
		String AltitudeDebug = new String().concat("Altitude = " + (((400 - (height / 2)) - y) / 30)); // Measured
		                                                                                               // in
		                                                                                               // Meters
		String PositionDebug = new String().concat("XPosition: " + (int) x + " || YPosition: "
		    + (int) y);
		String VelocityDebug = new String().concat("XVelocity: " + (int) v.dx);
		String XYOffset = new String().concat("XOffset = " + PlayState.cam.getX() + " || YOffset = "
		    + PlayState.cam.getY());
		String BalloonValues = new String().concat("# of Balloons = " + extraBalloons
		    + "  ||  # Balloons in Play = " + balloons.size());
		String deltaBuoyancy = new String()
		    .concat("deltaBuoyancy = " + (gravity * 2) / balloons.size());
		String objectCount = new String().concat("Object Count = "
		    + PlayState.handler.getObjects().size());
		debugOptions.add(BouyancyDebug);
		debugOptions.add(HydrogenLevelDebug);
		debugOptions.add(AltitudeDebug);
		debugOptions.add(PositionDebug);
		debugOptions.add(VelocityDebug);
		debugOptions.add(XYOffset);
		debugOptions.add(BalloonValues);
		debugOptions.add(deltaBuoyancy);
		debugOptions.add(objectCount);
	}

	/**
	 * initialize Animations
	 */
	private void initAnimations()
	{
		normal = new Animation(10, art.getSpriteFrame(spriteID.HANK2, 0), art.getSpriteFrame(
		    spriteID.HANK2, 1), art.getSpriteFrame(spriteID.HANK2, 1));
		death = new Animation(10, art.getSpriteFrame(spriteID.HANK2, 2), art.getSpriteFrame(
		    spriteID.HANK2, 3), art.getSpriteFrame(spriteID.HANK2, 1));
	}

	public float getHydrogenLevelPercent()
	{
		return (hLevel / maxHLevel);
	}

	public int getExtraBalloons()
	{
		return extraBalloons;
	}

	public int getAltitude()
	{
		return (int) ((400 - (height / 2)) - y) / 30;
	}

	private class Balloon
	{
		private float x;
		private float y;
		private int balloonColor;
		private boolean alive = true;
		private int width, height;

		public Balloon(float x, float y, int width, int height)
		{

			Random rand = new Random();
			balloonColor = rand.nextInt(3);
			this.width = (int) (width);
			this.height = height;
			setOffset(x, y);

		}

		public void render(Graphics2D g)
		{
			g.drawImage(art.getSpriteFrame(spriteID.BALLOON, balloonColor), (int) x, (int) y, width,
			    height, null);
		}

		public void tick(float x, float y)
		{
			setOffset(x, y);
		}

		/**
		 * Sets X,Y position to be offset by player position by a certain amount.
		 * 
		 * @param x
		 * @param y
		 */
		public void setOffset(float x, float y)
		{
			this.x = x - width / 2;
			this.y = (float) (y - height * 1.4);
		}
	}
}