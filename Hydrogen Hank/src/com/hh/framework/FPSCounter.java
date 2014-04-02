/**
 * 
 */
package com.hh.framework;

/**
 * COSC3550 Spring 2014 Created : Apr. 2, 2014 Last Updated : Apr. 2, 2014
 * 
 * FPSCounter
 * 
 * Class used to determine Frames Per Second
 * 
 * @author Charlie Beckwith
 *
 */
public class FPSCounter extends Thread{
    private long lastTime;
    private double fps; //could be int or long for integer values
    private double sampledFPS;
    private int samples;
    private final int SAMPLESIZE = 10;

    public void run(){
        while (true){//lazy me, add a condition for an finishable thread
            lastTime = System.nanoTime();
            try{
                Thread.sleep(2000); // longer than one frame
            }
            catch (InterruptedException e){

            }
            fps += 1000000000.0 / (System.nanoTime() - lastTime); //one second(nano) divided by amount of time it takes for one frame to finish
            lastTime = System.nanoTime();
            samples++;
            if(samples == SAMPLESIZE){
            	sampledFPS = fps/SAMPLESIZE;
            	fps = 0;
            	samples = 0;
            }
        }
    }
    public double getFPS(){
        return sampledFPS;
    } 
}
