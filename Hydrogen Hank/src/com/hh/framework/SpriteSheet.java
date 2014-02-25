package com.hh.framework;

import java.awt.image.BufferedImage;

/**
 * COSC3550 Spring 2014 Homework 3
 * 
 * Created : Feb. 7, 2014
 * Last Updated : Feb. 11, 2014
 * Purpose: Defines a BufferedImage as a SpriteSheet
 * 
 * @author Mark Schlottke
 */
public class SpriteSheet {
    private BufferedImage image;

    /**
     * Initializes the SpriteSheet with the image
     * @param image
     */
    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    /**
     * Grabs the sprite from the column and row specified
     * @param col - column to grab from
     * @param row - row to grab from
     * @param width - width of the sprite
     * @param height - height of the sprite
     * @return
     */
    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col*width)-width, (row*height)-height, width, height);
        return img;
    }
}
