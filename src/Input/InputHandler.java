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
				OverworldState.player.moveUp();
			}
			if(keyCode == KeyEvent.VK_A) {
				OverworldState.player.moveLeft();
			}
			if(keyCode == KeyEvent.VK_S) {
				OverworldState.player.moveDown();
			}
			if(keyCode == KeyEvent.VK_D) {
				OverworldState.player.moveRight();
			}
		}
	
	}

	public void keyReleased(KeyEvent a) {
		int keyCode = a.getKeyCode();

	}

	public void keyTyped(KeyEvent d) {

	}


}