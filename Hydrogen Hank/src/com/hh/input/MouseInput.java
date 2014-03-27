package com.hh.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.hh.Game;
import com.hh.framework.GameObject;
import com.hh.framework.ScoreKeeper;
import com.hh.framework.gamestate.states.*;
import com.hh.objects.MenuButton;
import com.hh.objects.ScoreTab;

/**
 * COSC3550 Spring 2014
 * 
 * Created : Feb. 25, 2014 
 * Last Updated : Feb. 28, 2014 
 * Purpose: Allows mouseclicks
 * 
 * @author Mark Schlottke & Charlie Beckwith
 */
public class MouseInput extends MouseAdapter
{
	public void mousePressed(MouseEvent e)
	{

	}

	public void mouseReleased(MouseEvent e)
	{
		if (Game.manager.getFirstClass() == TitleMenuAnimState.class
		    || Game.manager.getFirstClass() == IntroAnimation.class)
		{
			Game.manager.pop();
		}
		else if (Game.manager.getFirstClass() == TitleMenuState.class)
		{
			for (GameObject go : TitleMenuState.handler.getObjects())
			{
				if (go.getClass() == MenuButton.class)
				{
					MenuButton button = (MenuButton) go;
					if (button.isSelected())
					{
						switchMenuButton(button);
					}
				}
			}
		}
		else if (Game.manager.getFirstClass() == HighScoresState.class)
		{
			for (GameObject go : HighScoresState.handler.getObjects())
			{
				if (go.getClass() == ScoreTab.class)
				{
					ScoreTab tab = (ScoreTab) go;
					if (tab.isHoveringOver())
					{
						tab.setSelected(true);
					}
					else{
						tab.setSelected(false);
					}
				}
			}
		}
	}

	/**
	 * Switches game state depending on which button was pushed
	 * 
	 * @param button
	 */
	public void switchMenuButton(MenuButton button)
	{
		switch (button.getButtonID())
		{
		case NEWGAME:
			Game.manager.pop();
			break;
		case HIGHSCORE:
			Game.manager.push(new HighScoresState());
			break;
		case CREDITS:
			System.out.println("Credits");
			// Game.manager.STATES.push(new CreditsState());
			break;
		case RESETSCORES:
			ScoreKeeper.resetScores();
			
			break;

		}
	}

}
