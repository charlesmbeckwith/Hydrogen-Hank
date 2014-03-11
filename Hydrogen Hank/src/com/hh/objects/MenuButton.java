package com.hh.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.graphics.ArtAssets;

/**
 * COSC3550 Spring 2014 Homework 2
 * 
 * Defines the Brick class
 * 
 * @author Mark Schlottke
 */
public class MenuButton extends GameObject
{
	private BufferedImage IMG;
	public boolean SELECTED;
	private ButtonID BUTTONID;
	private ArtAssets art;
	private RenderHelper renderHelp = new RenderHelper();
	private Font font;
	String text = "";

	public enum ButtonID
	{
		NEWGAME, HIGHSCORE, CREDITS
	};

	public MenuButton(String text, float x, float y, int width, int height, ButtonID buttontype)
	{
		super(x, y, width, height, ObjectID.Tile, ObjectLayer.background);
		ALIVE = true;
		art = Game.getArtAssets();
		IMG = art.button;
		font = new Font("Arial", Font.BOLD, 32);
		SELECTED = false;
		BUTTONID = buttontype;
		this.text = text;
	}

	public void tick()
	{
		Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
		mouseLoc.x -= Game.getPosition().x;
		mouseLoc.y -= Game.getPosition().y + 25; // 25 pixels is roughly the size of the top of the
																						 // window

		if (mouseLoc.x > (X - WIDTH / 2) && mouseLoc.y > (Y - HEIGHT / 2)
		    && mouseLoc.x < (X + WIDTH / 2) && mouseLoc.y < (Y + HEIGHT / 2))
		{
			SELECTED = true;
		}
		else
		{
			SELECTED = false;
		}
	}

	public void render(Graphics g)
	{
		if (ALIVE)
		{
			Graphics2D g2d = (Graphics2D) g;

			g2d.drawImage(IMG, (int) (X - WIDTH / 2), (int) (Y - HEIGHT / 2), WIDTH, HEIGHT, null);

			FontMetrics metrics = g.getFontMetrics(font);
			int textX = (int) (X - (metrics.stringWidth(text) / 2) + (WIDTH * 0.04));
			int textY = (int) Y + (metrics.getHeight() / 4);
			if (!SELECTED)
			{
				renderHelp.outlinedText(g2d, font, text, 1.25f, Color.black, Color.white, textX, textY);
			}
			else
			{
				renderHelp.outlinedText(g2d, font, text, 1.25f, Color.black, Color.red, textX, textY);
			}
		}
	}

	public int getWidth()
	{
		return WIDTH;
	}

	public int getHeight()
	{
		return HEIGHT;
	}

	public ButtonID getButtonID()
	{
		return BUTTONID;
	}
}
