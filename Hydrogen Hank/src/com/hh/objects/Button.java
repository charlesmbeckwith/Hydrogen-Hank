package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.*;

/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke
 */
public class Button extends GameObject {
	private boolean ACTIVE = true;
	private BufferedImage IMG, IMGPRESS;
	private Animation ANIM;
	
	public Button(float x, float y, int width, int height) {
		super(x, y, width, height, ObjectID.Button);
		ANIM = new Animation(3, Game.artassets.button, Game.artassets.button, Game.artassets.button,Game.artassets.buttonpress);
	}


	public void tick() {
	  ANIM.runAnimation();
	}
	
	public void render(Graphics g) {
		if(ACTIVE){
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(ANIM.getAnimationFrame(), (int)X, (int)Y, WIDTH, HEIGHT, null);
		}
	}
	
	public boolean isAlive(){
	  return ACTIVE;
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
}
