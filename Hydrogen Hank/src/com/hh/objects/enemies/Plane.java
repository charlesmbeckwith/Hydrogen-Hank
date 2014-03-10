/**
 * 
 */
package com.hh.objects.enemies;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.GameTime;
import com.hh.framework.Vector2D;
import com.hh.graphics.Animation;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.objects.Enemy;

/**
 * @author blinginbeckwith
 *
 */
public class Plane extends Enemy
{
  private Animation ANIM;
  private ArtAssets art;

  /**
   * @param x
   * @param y
   * @param width
   * @param height
   * @param v
   */
  public Plane(float x, float y, int width, int height, Vector2D v)
  {
    super(x, y, width, height, v, EnemyType.Bird);
    art = Game.getArtAssets();
    initAnimations();
    ALIVE = true;
  }

  @Override
  public void tick()
  {
    X += V.DX * GameTime.delta();
    ANIM.runAnimation();
  }

  @Override
  public void render(Graphics g)
  {
	  Graphics2D g2d = (Graphics2D) g.create();
    if (ALIVE)
    {
      g2d.drawImage(ANIM.getAnimationFrame(), (int) (X - (WIDTH / 2)), (int) (Y - (HEIGHT / 2)),
          WIDTH, HEIGHT, null);
    }
  }

  /**
   * initialize Animations
   */
  private void initAnimations()
  {
	Random rand = new Random();  
	
	switch(rand.nextInt(4)){
	case 1:
		ANIM = new Animation(1, art.getSpriteFrame(spriteID.PLANES, 4), art.getSpriteFrame(
			spriteID.PLANES, 5), art.getSpriteFrame(spriteID.PLANES, 6), art.getSpriteFrame(spriteID.PLANES,
			7));
		break;
	case 2:
		ANIM = new Animation(1, art.getSpriteFrame(spriteID.PLANES, 8), art.getSpriteFrame(
			spriteID.PLANES, 9), art.getSpriteFrame(spriteID.PLANES, 10), art.getSpriteFrame(spriteID.PLANES,
			11));
		break;
	case 3:
		ANIM = new Animation(1, art.getSpriteFrame(spriteID.PLANES, 12), art.getSpriteFrame(
			spriteID.PLANES, 13), art.getSpriteFrame(spriteID.PLANES, 14), art.getSpriteFrame(spriteID.PLANES,
			15));
		break;
	default:
		ANIM = new Animation(1, art.getSpriteFrame(spriteID.PLANES, 0), art.getSpriteFrame(
			spriteID.PLANES, 1), art.getSpriteFrame(spriteID.PLANES, 2), art.getSpriteFrame(spriteID.PLANES,
			3));
		break;
	}
  }

}
