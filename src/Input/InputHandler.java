package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import GameStateManager.Game;
import GameStateManager.GameStateManager;
import GameStateManager.OverworldState;
import Intro.InputState;
import Intro.IntroductionState;
import Player.Player;
import Sounds.Music;

public class InputHandler implements KeyListener {

	/*
	 * Key bindings
	 */
	public void keyPressed(KeyEvent a) {
		int keyCode = a.getKeyCode();
		String keyChar = KeyEvent.getKeyText(a.getKeyChar());
		
		if(Game.gameStateManager.getState() == Game.gameStateManager.introductionState) {
			if(keyCode == KeyEvent.VK_ENTER) {
				Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
				Music.stopSound();
				OverworldState.stateOverworldState();
			}
		}
		
		if(Game.gameStateManager.getState() == Game.gameStateManager.introState) {
			if(keyCode == KeyEvent.VK_W) {
				InputState.moveUp();
			}
			if(keyCode == KeyEvent.VK_S) {
				InputState.moveDown();
			}
			if(keyCode == KeyEvent.VK_ENTER) {
				InputState.pushButton();
			}
		}
		
		
		
		
		if(Game.gameStateManager.getState() == Game.gameStateManager.overworldStateNumber) {
			/*
			 * Key bounds for the OverworldState
			 */
			if(keyCode == KeyEvent.VK_W) {
				try {
					OverworldState.player.moveUp();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			if(keyCode == KeyEvent.VK_A ) {
				try {
					OverworldState.player.moveLeft();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			if(keyCode == KeyEvent.VK_S) {
				try {
					OverworldState.player.moveDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			if(keyCode == KeyEvent.VK_D) {
					try {
						OverworldState.player.moveRight();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}			
			
			if(keyCode == KeyEvent.VK_1) {
				OverworldState.player.punLeft = true;
			}
			
			if(keyCode == KeyEvent.VK_2) {
				OverworldState.player.punRight = true;
			}
			
			if(keyCode == KeyEvent.VK_3) {
				OverworldState.player.die = true;
			}
			
			if(keyCode == KeyEvent.VK_4) {			
				OverworldState.player.Dab();	
			}
			
		}
		
	}
	

	public void keyReleased(KeyEvent a) {
		int keyCode = a.getKeyCode();

	}

	public void keyTyped(KeyEvent d) {

	}


}