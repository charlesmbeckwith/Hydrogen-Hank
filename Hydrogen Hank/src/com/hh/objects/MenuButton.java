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
	private boolean selected;
	private ButtonID buttonId;
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
		alive = true;
		art = Game.getArtAssets();
		IMG = art.button;
		font = new Font("Arial", Font.BOLD, 32);
		selected = false;
		buttonId = buttontype;
		this.text = text;
	}

	@Override
	public void tick()
	{
		Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
		mouseLoc.x -= Game.getPosition().x;
		mouseLoc.y -= Game.getPosition().y + 25; // 25 pixels is roughly the size of the top of the
		                                         // window

		if (this.boundingBox().contains(mouseLoc))
		{
			selected = true;
		}
		else
		{
			selected = false;
		}
	}

	@Override
	public void render(Graphics g)
	{
		if (alive)
		{
			Graphics2D g2d = (Graphics2D) g;

			g2d.drawImage(IMG, (int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height,
			    null);

			FontMetrics metrics = g.getFontMetrics(font);
			int textX = (int) (x - (metrics.stringWidth(text) / 2) + (width * 0.04));
			int textY = (int) y + (metrics.getHeight() / 4);
			if (!selected)
			{
				renderHelp.outlinedText(g2d, font, text, 1.25f, Color.black, Color.white, textX, textY);
			}
			else
			{
				renderHelp.outlinedText(g2d, font, text, 1.25f, Color.black, Color.red, textX, textY);
			}
		}
	}

	@Override
	public Rectangle boundingBox()
	{
		return new Rectangle((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
	}

	public ButtonID getButtonID()
	{
		return buttonId;
	}

	public boolean isSelected()
	{
		return selected;
	}
}
