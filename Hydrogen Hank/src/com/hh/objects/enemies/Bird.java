/**
 * 
 */
package com.hh.objects.enemies;

import java.awt.Graphics;
import java.awt.Graphics2D;

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
public class Bird extends Enemy
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
  public Bird(float x, float y, int width, int height, Vector2D v)
  {
    super(x, y, width, height, v, EnemyType.Bird);
    art = Game.getArtAssets();
    initAnimations();
    ALIVE = true;
  }

  @Override
  public void tick()
  {
    //this.X = -PlayState.cam.getX()+400;
    //this.Y = -PlayState.cam.getY()+350;
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
    ANIM = new Animation(6, art.getSpriteFrame(spriteID.BIRD, 0), art.getSpriteFrame(
        spriteID.BIRD, 1), art.getSpriteFrame(spriteID.BIRD, 2), art.getSpriteFrame(spriteID.BIRD,
        3));
  }

}
