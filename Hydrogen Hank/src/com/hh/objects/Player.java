package com.hh.objects;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import com.hh.Game;
import com.hh.framework.*;
import com.hh.framework.GameObject.ObjectLayer;
import com.hh.framework.gamestate.states.GameOverState;
import com.hh.framework.gamestate.states.HighScoresState;
import com.hh.framework.gamestate.states.PlayState;
import com.hh.framework.gamestate.states.TitleMenuAnimState;
import com.hh.framework.gamestate.states.TitleMenuState;
import com.hh.graphics.Animation;
import com.hh.graphics.ArtAssets;
import com.hh.graphics.SpriteSheet.spriteID;
import com.hh.input.KeyBinding;
import com.hh.input.KeyInput;
import com.hh.objects.Powerup.PowerupType;
import com.hh.objects.bg.Ground;
import com.hh.objects.powerups.HydrogenMolecule;
import com.hh.objects.vfx.Explosion;
import com.hh.objects.vfx.Smoke;
import com.hh.sound.SoundManager.SoundFile;

/**
 * COSC3550 Spring 2014 Created : Feb. 25, 2014 Last Updated : Mar. 19, 2014
 * Purpose: Defines the player
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
@SuppressWarnings("unused")
public class Player extends GameObject implements Collidable
{
  private final int DEATHCOUNTDOWN = 81;
  private final float gravity = 9f;
  private Animation normal, death, current;
  private ArtAssets art;
  private LinkedList<String> debugOptions;
  private boolean startDeath = false;
  private int deathCountdown = DEATHCOUNTDOWN;
  private float hLevel;
  private float maxHLevel = 200f;
  private boolean grounded = false;
  private float weight = 180f;
  private float buoyancy;
  private LinkedList<Balloon> balloons = new LinkedList<Balloon>();
  private boolean balloonAlreadyBlownUp = false;
  private int extraBalloons = 50; // How many balloons you start with
  private final int balloonCost = 10; // How much hydrogen blowing up a
  // balloon
  // costs.

  private final int maxBalloons = 10;
  private boolean inflate = false;
  private boolean deflate = false;
  private float gInc = 0f;
  private final float inflateAmt = 3.0f;
  private final float deflateAmt = 3.0f;
  private final float leakAmt = 0.01f;
  private int balloonsUsed = 0;
  private int heliumUsed = 0;
  private float inflationCost = (float) 0.333;

  private boolean collision = false;

  private int moleculesPickedUp = 0;
  private int closeCalls = 0;
  private int birdsObliterated = 0;
  private int powerUps = 0;

  public Player(float x, float y, int width, int height, Vector2D v)
  {
    super(x, y, width, height, v, ObjectID.Player, ObjectLayer.foreground);
    alive = true;
    buoyancy = 0.0f;
    hLevel = 200f;

    art = Game.getArtAssets();
    initAnimations();
    current = normal;
    Game.soundManager.playAudioClip(SoundFile.hank);
  }

  @Override
  public void tick()
  {
    if (alive)
    {
      if (DebugManager.infiniteHelium)
        hLevel = 200f;

      collision = collision();

      if (v.dx < 150 && !collision)
      {
        v.dx += 1;
      }

      if (v.dy > (weight - buoyancy))
      {
        v.dy += (weight - buoyancy) * 0.01f;
      } else if (v.dy < weight * gravity)
      {
        v.dy += gravity;
      }

      if (v.dy > weight * gravity)
        v.dy = weight * gravity;
      if (deathCountdown > 40)
      {
        x += v.dx * GameTime.delta();
        y += v.dy * GameTime.delta();
      }

      current.runAnimation();

      tickBalloons();

      if (startDeath)
      {
        deathIterator();
      }
    }
  }

  /**
   * Processes the events leading up to player death
   * 
   */
  public void deathIterator()
  {
    /* If the player is dying but blows up a balloon stop death */
    if (balloons.size() > 0 && hLevel > 0 && deathCountdown > 40)
    {
      stopKill();
      closeCalls++;
    }

    if (deathCountdown == 80)
    {
      // Play "explosion imminent" noise and scream sound.
      Game.soundManager.playAudioClip(SoundFile.fuse);
      Game.soundManager.playAudioClip(SoundFile.scream);
    }

    if (deathCountdown > 40)
    {
      // Add smoke effect behind player as he falls.
      PlayState.handler.addObject(new Smoke(x, y, 64, 64, ObjectLayer.middleground));
    }
    if (deathCountdown == 40)
    {
      // Add gigantic explosion indicating player death
      PlayState.handler.addObject(new Explosion(x, y, 512, 512, ObjectLayer.hud));
      deathCountdown--;
    }
    if (deathCountdown == 0)
    {
      kill();
    }

    deathCountdown--;
  }

  public void tickBalloons()
  {
    buoyancy = 0;

    if (KeyInput.keysDown.contains(KeyBinding.INFLATE.VALUE()))
    {
      if (hLevel > 0 && !balloons.isEmpty())
      {
        inflate = true;
        hLevel -= inflationCost;
        heliumUsed += inflationCost;
      } else if (hLevel < 0)
      {
        hLevel = 0;
      }
    }
    if (DebugManager.debugMode)
    {
      /* Insta-Kill */
      if (KeyInput.keysDown.contains(KeyBinding.KILL.VALUE()))
      {
        startKill();
      }
      if (KeyInput.keysDown.contains(KeyBinding.POPALL.VALUE()))
      {
        balloons.clear();
      }
      if (KeyInput.keysDown.contains(KeyBinding.TestKey1.VALUE()))
      {
        /*
         * Add Values or methods to test here.
         */
      }
      if (KeyInput.keysDown.contains(KeyBinding.TestKey2.VALUE()))
      {
        /*
         * Add Values or methods to test here.
         */
      }
    }

    if (KeyInput.keysDown.contains(KeyBinding.DEFLATE.VALUE()))
    {
      deflate = true;
    }

    if (KeyInput.keysDown.contains(KeyBinding.BLOWUP_BALLOON.VALUE()))
    {
      if (extraBalloons > 0 && !balloonAlreadyBlownUp && hLevel > balloonCost
          && balloons.size() < maxBalloons)
      {
        balloons.push(new Balloon(x - 12, y - height / 2 + 6, (int) (width * 0.9),
            (int) (height * 0.9)));
        if (!DebugManager.infiniteBalloons)
          extraBalloons--;
        hLevel -= balloonCost;
        heliumUsed += balloonCost;
        balloonsUsed++;
        balloonAlreadyBlownUp = true;
      }
    } else
    {
      balloonAlreadyBlownUp = false;
    }

    for (Balloon bloon : balloons)
    {
      bloon.tick(x - 12, y - height / 2 + 6);

      if (inflate)
      {
        bloon.inflate(inflateAmt / balloons.size());
      }

      if (deflate)
      {
        bloon.deflate(deflateAmt / balloons.size());
      }

      buoyancy += (bloon.getFillLevel()) * gravity * 0.1f;
      bloon.deflate(leakAmt);
    }

    inflate = false;
    deflate = false;
  }

  /**
   * Method that detects a collision between player and
   * 
   * @return
   */
  public boolean collision()
  {
    boolean col = false;
    grounded = false;

    for (final GameObject go : PlayState.handler.getObjects())
    {

      // if (go instanceof Collidable)
      if (true)
      {
        boolean collided = collided(go);

        if (go != this && go.getId() == ObjectID.Enemy && collided && go.isAlive())
        {
          Enemy enemy = (Enemy) go;
          switch (enemy.getEnemyType())
          {
          case Bird:
            go.kill();
            destroyBalloon();
            birdsObliterated++;
            break;
          case Plane:
            destroyBalloon();
            break;
          }

        }

        // check for ground collision
        if (go != this && go.getClass() == Ground.class && (y + (height / 2) - 14) >= go.getY()
            && x > go.getX() && x < go.getX() + go.getWidth())
        {
          col = true;
          grounded = true;

          if (v.dy > weight * 3 || (balloons.isEmpty() && hLevel < balloonCost) || hLevel == 0
              || (extraBalloons == 0 && balloons.isEmpty()))
          {
            if (!startDeath)
            {
              deathCountdown = 40;
              startKill();
            }

          }

          y = (go.getY() - (height / 2) + 14);
          v.dy = 0;

          if (v.dx > 0)
            v.dx -= (v.dx * 0.01);
        }

        // Check for powerup collision
        if (go != this && go.getId() == ObjectID.Powerup && collided)
        {
          // cast go as Powerup
          Powerup pu = (Powerup) go;

          switch (pu.getPowerupType())
          {
          case BalloonPack:
            // Add an extra Balloon
            extraBalloons += pu.getValue();
            go.kill();
            powerUps++;
            break;
          case HydrogenTank:
            // Add hydrogen to tank
            hLevel += pu.getValue();
            go.kill();
            powerUps++;
            break;
          case HydrogenMolecule:
            hLevel += pu.getValue();
            moleculesPickedUp++;
            go.kill();
            break;
          }

          if (hLevel > maxHLevel)
          {
            hLevel = maxHLevel;
          }
        }
      } else
      {
        // System.out.println("Collision Detection averted");
      }
    }

    return col;
  }

  public boolean collided(GameObject go)
  {

    Area player = new Area(this.boundingBox());
    player.intersect(go.boundingBox());
    return !player.isEmpty();

  }

  @Override
  public void render(Graphics g)
  {
    if (alive)
    {
      Graphics2D g2d = (Graphics2D) g.create();

      // BufferedImage image = art.hueImg(CURRENT.getAnimationFrame(),
      // width, height, HUE);
      if (DebugManager.showBounds)
      {
        g2d.setColor(Color.black);
        g2d.draw(this.boundingBox());
      }
      if (DebugManager.debugMode)
      {
        debugOptions(g2d);
      }
      for (Balloon bloons : balloons)
      {
        bloons.render(g2d);
      }
      if (deathCountdown > 40)
        g2d.drawImage(current.getAnimationFrame(), (int) center.getX(), (int) center.getY(),
            (int) width, (int) height, null);
    }
  }

  @Override
  public Area boundingBox()
  {
    return new Area(new Ellipse2D.Double(center.getX(), center.getY(), width - 5, height - 5));

  }

  private void debugOptions(Graphics2D g2d)
  {
    initDebug();
    g2d.setColor(Color.BLACK);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    float relativeZeroX = -PlayState.cam.getX() + 10;
    float relativeZeroY = -PlayState.cam.getY() + Game.height - (11 * (debugOptions.size() + 1));
    int row = 1;
    for (String x : debugOptions)
    {
      g2d.drawString(x, relativeZeroX, relativeZeroY + 11 * row);
      row++;
    }

  }

  /**
   * Destroy one balloon attached to Hank
   * 
   */
  public void destroyBalloon()
  {
    if (!balloons.isEmpty())
    {
      // Play popping sound
      Game.soundManager.playAudioClip(SoundFile.pop);
      balloons.pop();
      if (balloons.isEmpty())
      {
        buoyancy = 0f;
      }
    } else
    {

      startKill();
    }
  }

  public void startKill()
  {
    current = death;
    startDeath = true;
  }

  public void stopKill()
  {
    Game.soundManager.playAudioClip(SoundFile.hankbetter);
    Game.soundManager.stopAudioClip(SoundFile.scream);
    current = normal;
    startDeath = false;
    deathCountdown = DEATHCOUNTDOWN;
  }

  @Override
  public void kill()
  {
    // TODO: Kill Hank - Game Over Screen!
    GameOverState gameOver = new GameOverState(calculateScore(), Game.playState.maxAltitude,
        (int) Game.playState.getPlayTime(), balloonsUsed, moleculesPickedUp, closeCalls,
        birdsObliterated, powerUps, heliumUsed);

    Game.manager.push(new TitleMenuState());
    Game.manager.push(new TitleMenuAnimState());
    Game.manager.push(gameOver);
    Game.playState.restart = true;
    super.kill();
  }

  public int calculateScore()
  {
    if (balloonsUsed == 0)
    {
      return 0;
    } else
    {
      return (int) (moleculesPickedUp * (heliumUsed / balloonsUsed));
    }
  }

  private void initDebug()
  {
    debugOptions = new LinkedList<String>();
    String BouyancyDebug = new String().concat("Bouyancy = " + (int) buoyancy);
    String HydrogenLevelDebug = new String().concat("Hydrogen Level = " + hLevel);
    String AltitudeDebug = new String().concat("Altitude = " + (((400 - (height / 2)) - y) / 30)); // Measured
    // in
    // Meters
    String PositionDebug = new String().concat("XPosition: " + (int) x + " || YPosition: "
        + (int) y);
    String VelocityDebug = new String().concat("XVelocity: " + (int) v.dx);
    String XYOffset = new String().concat("XOffset = " + PlayState.cam.getX() + " || YOffset = "
        + PlayState.cam.getY());
    String BalloonValues = new String().concat("# of Balloons = " + extraBalloons
        + "  ||  # Balloons in Play = " + balloons.size());
    String deltaBuoyancy = new String()
        .concat("deltaBuoyancy = " + (gravity * 2) / balloons.size());
    String objectCount = new String().concat("Object Count = "
        + PlayState.handler.getObjects().size());
    String fps = new String().concat("FPS = " + Game.getFPS());
    debugOptions.add(BouyancyDebug);
    debugOptions.add(HydrogenLevelDebug);
    debugOptions.add(AltitudeDebug);
    debugOptions.add(PositionDebug);
    debugOptions.add(VelocityDebug);
    debugOptions.add(XYOffset);
    debugOptions.add(BalloonValues);
    debugOptions.add(deltaBuoyancy);
    debugOptions.add(objectCount);
    debugOptions.add(fps);
  }

  /**
   * initialize Animations
   */
  private void initAnimations()
  {
    normal = new Animation(10, art.getSpriteFrame(spriteID.HANK2, 0), art.getSpriteFrame(
        spriteID.HANK2, 1), art.getSpriteFrame(spriteID.HANK2, 1));
    death = new Animation(10, art.getSpriteFrame(spriteID.HANK2, 2), art.getSpriteFrame(
        spriteID.HANK2, 3), art.getSpriteFrame(spriteID.HANK2, 1));
  }

  /**
   * return the hydrogen level percentage
   * 
   * @return float
   */
  public float getHydrogenLevelPercent()
  {
    return (hLevel / maxHLevel);
  }

  /**
   * return how many extra balloons are left
   * 
   * @return int
   */
  public int getExtraBalloons()
  {
    return extraBalloons;
  }

  /**
   * get current "altitude" of player
   * 
   * @return int
   */
  public int getAltitude()
  {
    return (int) ((400 - (height / 2)) - y) / 30;
  }

  private class Balloon
  {
    private float x, y, rX, rY, xOffset, yOffset;
    private int balloonColor;
    private boolean alive = true;
    private int width, height, adjWidth, adjHeight;
    private float fillLevel;
    private BufferedImage img;
    private int dirCounter;
    private boolean toRight = false;

    public Balloon(float x, float y, int width, int height)
    {
      balloonColor = Game.Rand.nextInt(8);
      this.width = (int) (width);
      this.height = height;
      fillLevel = 100;
      dirCounter = 0;
      img = art.balloon_sheet2.getFrame(balloonColor);
      xOffset = Game.Rand.nextBoolean() ? -Game.Rand.nextFloat() * 10 : Game.Rand.nextFloat() * 10;
      yOffset = Game.Rand.nextBoolean() ? -Game.Rand.nextFloat() * 15 : Game.Rand.nextFloat() * 4;
      adjWidth = (int) ((getFillLevel() / 100) * width);
      adjHeight = (int) ((getFillLevel() / 100) * height);

      // Play sound of balloon blowing up
      Game.soundManager.playAudioClip(SoundFile.blow);
    }

    public void tick(float x, float y)
    {
      this.x = x;
      this.y = y;

      if (fillLevel > 35)
      {
        adjWidth = (int) ((getFillLevel() / 100) * width);
        adjHeight = (int) ((getFillLevel() / 100) * height);
      }

      if (dirCounter == 10)
      {
        toRight = Game.Rand.nextBoolean();
        dirCounter = 0;
      }

      if (toRight)
      {
        xOffset += Game.Rand.nextFloat();
      } else
      {
        xOffset -= Game.Rand.nextFloat();
      }

      if (xOffset > 10)
      {
        xOffset = 10;
      } else if (xOffset < -10)
      {
        xOffset = -10;
      }

      rX = (int) x - adjWidth / 2 + xOffset;
      rY = (int) y - 10 - adjHeight + yOffset;
      dirCounter++;
    }

    public void render(Graphics2D g)
    {
      g.setColor(Color.gray);
      g.drawLine((int) rX + adjWidth / 2, (int) rY + adjHeight - 5, (int) x, (int) y + 10);
      g.drawImage(img, (int) rX, (int) rY, adjWidth, adjHeight, null);
    }

    public void inflate(float amt)
    {
      fillLevel += amt;

      if (fillLevel > 100)
      {
        fillLevel = 100;
      }
    }

    public void deflate(float amt)
    {
      fillLevel -= amt;

      if (fillLevel < 0)
      {
        fillLevel = 0;
      }
    }

    public float getFillLevel()
    {
      return fillLevel;
    }
  }
}
