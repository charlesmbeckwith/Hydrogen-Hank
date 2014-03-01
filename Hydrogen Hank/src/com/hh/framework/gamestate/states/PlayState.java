package com.hh.framework.gamestate.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.hh.Game;
import com.hh.framework.Camera;
import com.hh.framework.Handler;
import com.hh.framework.Vector2D;
import com.hh.framework.gamestate.GameState;
import com.hh.objects.*;
import com.hh.objects.bg.*;
import com.hh.objects.enemies.*;

public class PlayState extends GameState {
	public static Handler handler;
	public static Camera cam;

	public Player player;
	private int start;

	public PlayState() {
		handler = new Handler();
		cam = new Camera(0, 0);
	}

	public void tick() {
		if (handler.getObjects().size() < 50) {
			addBackground(start);
			start += 75;
		}
		handler.tick();
		cam.tick(player);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(new Color(109, 136, 253));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		g2d.translate(cam.getX(), cam.getY()); // begin cam
		// Begin Drawing
		handler.render(g);
		// End Drawing
		g2d.translate(-cam.getX(), -cam.getY()); // end cam
	}

	/**
	 * Clears the necessary objects and reinitializes them to enable a restart
	 * of the game
	 */
	public void restart() {
		handler.clearObjects();

		player = new Player(100, 100, 64, 64, new Vector2D(0, 100));
		handler.addObject(player);
		cam = new Camera(0,0);

		start = -175-Game.WIDTH;
		for (int i = start; i < start + Game.WIDTH * 2; i += 75) {
			addBackground(i);
		}
		
		handler.addObject(new Bird(-cam.getX(), -cam.getY(), 48, 48, new Vector2D(0,0)));

		start = start + Game.WIDTH * 2;
	}

	private void addBackground(int x) {
		int cloudYPosition = genCloudYPosition();
		while(cloudYPosition > 100)
			cloudYPosition = genCloudYPosition();
		handler.addObject(new Cloud((int) (x + (Math.random() * 20)),
				cloudYPosition, 256, 128));
		handler.addObject(new Ground(x, 400, 75, 300));
	}
	
	private int genCloudYPosition(){
		return (int)(-cam.getY() + (Math.random()*500)); 
	}
	
	public Camera getCamera(){return cam;}

}
