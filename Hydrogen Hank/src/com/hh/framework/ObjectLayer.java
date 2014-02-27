package com.hh.framework;

/**
 * @author Charlie Beckwith
 *
 */
/*
 * Layers that decide what layer things will be painted on. 
 * Effectively there are 5 layers. A background, middleground,
 *  foreground, hud and a fifth "toplevel" which could be used 
 *  in a worst case scenario. In regular practice I don't anticipate 
 *  this needing to be used.
 */
public enum ObjectLayer
{
  background, middleground, foreground, hud, toplevel

}
