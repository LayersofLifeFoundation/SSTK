package Input;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.io.IOException;

import Battle.BattleState;
import GameStateManager.Game;
import GameStateManager.GameStateManager;
import GameStateManager.OverworldState;
import Intro.InputState;
import Intro.IntroductionState;
import Player.Player;
import Sounds.Music;
import FileSystem.Save;
import GameStateManager.OptionState;

public class InputHandler implements KeyListener {

	/*
	 * Key bindings
	 */
	public void keyPressed(KeyEvent a) {
		int keyCode = a.getKeyCode();
		String keyChar = KeyEvent.getKeyText(a.getKeyChar());

		if (Game.gameStateManager.getState() == Game.gameStateManager.introductionState) {
			if (keyCode == KeyEvent.VK_ENTER) {
				Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
				Music.stopSound();
				OverworldState.stateOverworldState();
			}
		}

		if (Game.gameStateManager.getState() == Game.gameStateManager.introState) {
			if (keyCode == KeyEvent.VK_W) {
				InputState.moveUp();
			}
			if (keyCode == KeyEvent.VK_S) {
				InputState.moveDown();
			}
			if (keyCode == KeyEvent.VK_ENTER) {
				InputState.pushButton();
			}
		}

		if (Game.gameStateManager.getState() == Game.gameStateManager.optionState) {
			if (keyCode == KeyEvent.VK_W) {
				OptionState.moveUp();
			}
			if (keyCode == KeyEvent.VK_S) {
				OptionState.moveDown();
			}
			if (keyCode == KeyEvent.VK_ENTER) {
				OptionState.pushButton();
			}
		}
		
		if (keyCode == KeyEvent.VK_P) {
			Game.gameStateManager.changeState(Game.gameStateManager.optionState);
		}

		// in battle
		if (Game.gameStateManager.getState() == Game.gameStateManager.battleStateNum) {
			if (keyCode == KeyEvent.VK_D) {
				BattleState.moveRight();
			}
			if (keyCode == KeyEvent.VK_A) {
				BattleState.moveLeft();
			}
			if (keyCode == KeyEvent.VK_SPACE) {
				BattleState.attack();
			}

		}
		if (Game.gameStateManager.getState() == Game.gameStateManager.overworldStateNumber) {
			/*
			 * Key bounds for the OverworldState
			 */
			if (keyCode == KeyEvent.VK_SPACE) {
				OverworldState.interact();
			}
			if (keyCode == KeyEvent.VK_W) {
				try {
					OverworldState.player.moveUp();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}

			}
			if (keyCode == KeyEvent.VK_A) {
				try {
					OverworldState.player.moveLeft();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
			if (keyCode == KeyEvent.VK_S) {
				try {
					OverworldState.player.moveDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
			if (keyCode == KeyEvent.VK_D) {
				try {
					OverworldState.player.moveRight();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (keyCode == KeyEvent.VK_1) {
				OverworldState.player.punLeft = true;
			}

			if (keyCode == KeyEvent.VK_2) {
				OverworldState.player.punRight = true;
			}

			if (keyCode == KeyEvent.VK_3) {
				OverworldState.player.die = true;
			}

			if (keyCode == KeyEvent.VK_4) {
				OverworldState.player.Dab();
				if (!Music.boosted) {
					Music.boost();
				}
			} else {
				if (Music.boosted && keyCode != KeyEvent.VK_SPACE) {
					Music.norm();
					Player.dab = false;
				}
			}
			if (keyCode == KeyEvent.VK_B) {
				
				BattleState.startBattleMusic();
				Game.gameStateManager.changeState(Game.gameStateManager.battleStateNum);
			}
		}

	}

	public void keyReleased(KeyEvent a) {
	}

	public void keyTyped(KeyEvent d) {

	}

}