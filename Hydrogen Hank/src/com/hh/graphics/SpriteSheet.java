package com.hh.graphics;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014 Last Updated : Feb. 26, 2014 
 * Purpose: Defines a BufferedImage as a SpriteSheet
 * 
 * @author Mark Schlottke and Charlie Beckwith
 */
public class SpriteSheet {

	public enum spriteID {
		HANK
	}

	private BufferedImage image;
	private BufferedImage[] sprites;
	private spriteID ID;

	private String resourcePath = "";
	private String resourceSpecPath = "";
	private int WIDTH, HEIGHT, COLUMNS, ROWS = 1;

	/**
	 * Initializes the SpriteSheet with the image
	 * 
	 * @param image
	 */
	public SpriteSheet(spriteID id, String resourcePath) {
		this.resourcePath = resourcePath;
		this.image = loadImage(resourcePath);
		ID = id;
		parseSpriteSpecs();
		getFrames();
		
	}

	private void getFrames() {
		sprites = new BufferedImage[COLUMNS*ROWS];
		int spriteNum = 0;
		for(int row = 0; row < ROWS; row++){
			for(int col = 0; col < COLUMNS; col++){
				sprites[spriteNum] = grabImage(col+1,row+1,WIDTH,HEIGHT);
				spriteNum++;
			}
		}

	}

	public spriteID getID() {
		return ID;
	}

	/**
	 * Grabs the sprite from the column and row specified
	 * 
	 * @param col
	 *            - column to grab from
	 * @param row
	 *            - row to grab from
	 * @param width
	 *            - width of the sprite
	 * @param height
	 *            - height of the sprite
	 * @return
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * width) - width,
				(row * height) - height, width, height);
		return img;
	}

	/**
	 * 
	 * @param frame
	 * @return returns a specified frame of a sprite sheet.
	 */
	public BufferedImage getFrame(int frame) {
		if (frame < sprites.length)
			return sprites[frame];
		else
			return null;
	}

	/**
	 * Loads the BufferedImage from the path
	 * 
	 * @param path
	 *            - location of the image
	 * @return - BufferedImage of the asset image
	 */
	private BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return image;
	}

	/**
	 * parses a "sprite spec sheet" detailing the height, width and number of
	 * columns and rows a sprite sheet contains
	 * 
	 * 
	 */
	private void parseSpriteSpecs() {
		resourceSpecPath = resourcePath.substring(0, resourcePath.indexOf('.'))
				.concat(".sheet");
		File specSheet = new File(System.getProperty("user.dir")
				+ "/res/" + resourceSpecPath);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(specSheet));
			for (String line; (line = br.readLine()) != null;) {
				int specVal = 1;
				if (!line.isEmpty()) {
					if (line.charAt(0) != '#' && line.charAt(0) != '!') {
						try {
							specVal = Integer.parseInt(line.substring(line
									.indexOf('=') + 1));
						} catch (Exception e) {
							e.printStackTrace();
							break;
						}
						if (specVal != 1) {
							if (line.contains("WIDTH")) {
								WIDTH = specVal;
							} else if (line.contains("HEIGHT")) {
								HEIGHT = specVal;
							} else if (line.contains("COLUMNS")) {
								COLUMNS = specVal;
							} else if (line.contains("ROWS")) {
								ROWS = specVal;
							}
						}
					}
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
