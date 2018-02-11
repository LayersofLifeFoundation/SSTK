package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import GameStateManager.Game;
import GameStateManager.GameStateManager;
import GameStateManager.OverworldState;

public class InputHandler implements KeyListener {

	/*
	 * Key bindings
	 */
	public void keyPressed(KeyEvent a) {
		int keyCode = a.getKeyCode();
		String keyChar = KeyEvent.getKeyText(a.getKeyChar());
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
			if(keyCode == KeyEvent.VK_A) {
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
					//e.printStackTrace();
				}
			}
			
			if(keyCode == KeyEvent.VK_R) {
				try {
					OverworldState.player.animateDie();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			
			if(keyCode == KeyEvent.VK_P) {
				
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