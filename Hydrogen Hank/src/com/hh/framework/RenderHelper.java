package com.hh.framework;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Mar. 4, 2014 
 * Last Updated : Mar. 19, 2014 
 * Purpose: Object to contain common rendering techniques
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class RenderHelper
{
	public RenderHelper()
	{

	}

	/**
	 * Draws a semi-transparent box
	 * 
	 * @param g
	 *          - graphics
	 */
	public void tintedBox(Graphics2D g, Color tint, float tintAmt, int posX, int posY, int width,
	    int height)
	{
	  Composite origComp = g.getComposite();
	  
		g.setColor(tint);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tintAmt));
		g.fillRect(posX, posY, width, height);
		
		// Set the composite setting back to the original setting
		g.setComposite(origComp);
	}

	/**
	 * Draws text with outline around it
	 * 
	 * @param g2d
	 *          -- graphics
	 */
	public void outlinedText(Graphics2D g, Font font, String line, float thickness,
	    Color outlineColor, Color textColor, int posX, int posY)
	{
		Shape[] outlines = new Shape[line.length()];

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		GlyphVector gv = font.createGlyphVector(g.getFontRenderContext(), line);

		// Get the shapes of each letter in the line
		for (int i = 0; i < line.length(); i++)
		{
			outlines[i] = gv.getGlyphOutline(i);
		}

		// Outline the letters with a thickness-pixel wide line
		g.setStroke(new BasicStroke(thickness));

		// Translate g to the location to draw
		g.translate(posX, posY);
		for (int i = 0; i < outlines.length; i++)
		{
			g.setPaint(textColor);
			g.fill(outlines[i]);
			g.setPaint(outlineColor);
			g.draw(outlines[i]);
		}

		// Translate g back to origianl position
		g.translate(-posX, -posY);
	}
}
