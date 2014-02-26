package com.hh.keyboard;

import java.awt.event.KeyEvent;

public enum KeyBinding
{
  INFLATE(KeyEvent.VK_W), 
  DEFLATE(KeyEvent.VK_S),
  FANON(KeyEvent.VK_SPACE), 
  PAUSE(KeyEvent.VK_P),
  COLLIDERS(KeyEvent.VK_C),
  RESTART(KeyEvent.VK_R);
  
  private int value;
  
  private KeyBinding(final int value) {
    this.value = value;
  }
  
  public void SET(int value){
    this.value = value;
  }
  
  public int VALUE(){
    return value;
  }
}
