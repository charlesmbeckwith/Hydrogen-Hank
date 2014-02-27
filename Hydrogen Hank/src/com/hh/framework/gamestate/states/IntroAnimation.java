package com.hh.framework.gamestate.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import com.hh.Game;
import com.hh.framework.gamestate.GameState;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;

public class IntroAnimation extends GameState {
	private ArtAssets art = Game.getArtAssets();
	private float hankXPosition = 100;
	private float hankYPosition = (Game.HEIGHT / 2);
	private int heightShift = 2;
	private int hankSize = 500;
	private int scriptProgCounter = 0;
	private String scriptPath = "/res/scripts/introanimation.script";
	private LinkedList<String> script = new LinkedList<String>();
	private String currentline = "";
	private String lineToPrint = "";
	private int charPtr = 0;
	private Font animFont = new Font("Arial", Font.PLAIN, 50);
	ListIterator<String> scriptIter;

	public IntroAnimation() {
		parseScript();
		scriptIter = script.listIterator();

	}

	@Override
	public void tick() {
		hankXPosition -= 1;
		scriptProgCounter += 1;

		if (scriptProgCounter % 2 == 0 && charPtr < currentline.length())
			charPtr++;

		lineToPrint = currentline.substring(0, charPtr);

		if (scriptProgCounter % 75 == 0 && scriptIter.hasNext()) {
			currentline = scriptIter.next();
			charPtr = 0;
		}

		if (scriptProgCounter % 225 == 0) {
			hankSize *= 1.5;
			heightShift++;
			hankYPosition = (Game.HEIGHT / heightShift);
		}
		if(scriptProgCounter % 600 == 0)
			Game.manager.pop();

	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawImage(art.mainBg, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g2d.drawImage(art.getSpriteFrame(spriteID.HANK, 0),
				(int) hankXPosition, (int) hankYPosition, hankSize, hankSize,
				null);
		g2d.setFont(animFont);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		FontRenderContext fontRendContext = g2d.getFontRenderContext();
		if (lineToPrint.length() > 0) {
			TextLayout text = new TextLayout(lineToPrint, animFont,
					fontRendContext);
			Shape shape = text.getOutline(null);
			Rectangle rect = shape.getBounds();
			AffineTransform affineTransform = new AffineTransform();
		    affineTransform = g2d.getTransform();
		    affineTransform.translate(150, 150);
		    Graphics2D g2dd = (Graphics2D) g2d.create();
		    g2dd.transform(affineTransform);
			g2dd.setColor(Color.white);
			g2dd.setStroke(new BasicStroke(6.0f));
			g2dd.draw(shape);
			g2dd.setClip(shape);
		}

		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(1.0f));
		g2d.drawString(lineToPrint, 150, 150);

	}

	/**
	 * parses a "sprite spec sheet" detailing the height, width and number of
	 * columns and rows a sprite sheet contains
	 * 
	 * 
	 */
	private void parseScript() {

		File specSheet = new File(System.getProperty("user.dir") + scriptPath);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(specSheet));
			for (String line; (line = br.readLine()) != null;) {
				if (!line.isEmpty()) {
					script.add(line);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
