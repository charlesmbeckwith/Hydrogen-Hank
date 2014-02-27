package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.framework.gamestate.states.PlayState;
import com.hh.graphics.ArtAssets;

/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke
 */
public class Cloud extends GameObject {
	private BufferedImage IMG;
	private ArtAssets art;
	private static Random rand = new Random();

	public Cloud(float x, float y, int width, int height) {
		// TODO: If we want to have particles/clouds in the foreground we should
		// make sure they're bigger and maybe more atmospheric?
		// TODO: Make another semi transparent fog overlay that comes in like a
		// cloud. For now I'm going to take it out, though. As I thought there
		// was a problem with my sorting algorithm :p

		super(x, y, width, height, ObjectID.Background, (rand.nextBoolean() ?
		 ObjectLayer.background : ObjectLayer.foreground));
		
		art = Game.getArtAssets();
		IMG = art.cloud;
		ALIVE = true;
	}

	public void tick() {
	}

	public void render(Graphics g) {
		if (X + WIDTH < -PlayState.cam.getX()) {
			ALIVE = false;
		}

		if (ALIVE) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.8f));
			g2d.drawImage(IMG, (int) X, (int) Y, WIDTH, HEIGHT, null);

		}
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
}
