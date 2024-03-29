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
import NPC.Encounter;
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

		if(keyCode == KeyEvent.VK_DELETE) {
			Game.gameStateManager.changeState(Game.gameStateManager.introState);
			Game.restart();
		}
		
		if (Game.gameStateManager.getState() == Game.gameStateManager.introductionState) {
			if (keyCode == KeyEvent.VK_SPACE) {
				Game.gameStateManager.changeState(Game.gameStateManager.overworldStateNumber);
				Music.stopSound();
				OverworldState.stateOverworldState();
			}
		}

		if (Game.gameStateManager.getState() == Game.gameStateManager.introState) {
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				InputState.moveUp();
			}
			if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				InputState.moveDown();
			}
			if (keyCode == KeyEvent.VK_SPACE) {
				InputState.pushButton();
			}
		}

		if (Game.gameStateManager.getState() == Game.gameStateManager.optionState) {
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				OptionState.moveUp();
			}
			if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				OptionState.moveDown();
			}
			if (keyCode == KeyEvent.VK_SPACE) {
				OptionState.pushButton();
			}
			if(keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
				OptionState.inInv = false;
				OptionState.itemM.inMenu = false;
			}
		}


		// in battle
		if (Game.gameStateManager.getState() == Game.gameStateManager.battleStateNum) {
			if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
				BattleState.moveRight();
			}
			if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
				BattleState.moveLeft();
			}
			if (keyCode == KeyEvent.VK_SPACE) {
				BattleState.action();
			}

		}
		if (Game.gameStateManager.getState() == Game.gameStateManager.overworldStateNumber && !Encounter.disableKeys) {
			/*
			 * Key bounds for the OverworldState
			 */
			if (keyCode == KeyEvent.VK_SPACE && !GameStateManager.battle.shrek.rip) {
				OverworldState.interact();
			}

			if (keyCode == KeyEvent.VK_P) {
				Game.gameStateManager.changeState(Game.gameStateManager.optionState);
			}
			if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
				try {
					OverworldState.player.moveUp();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
			if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
				try {
					OverworldState.player.moveLeft();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
			if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
				try {
					OverworldState.player.moveDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
			if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
				try {
					OverworldState.player.moveRight();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (!Player.isMoving) {
/*
				if (keyCode == KeyEvent.VK_1) {
					OverworldState.player.punLeft = true;
				}

				if (keyCode == KeyEvent.VK_2) {
					OverworldState.player.punRight = true;
				}

				if (keyCode == KeyEvent.VK_3) {
					BattleState.shrek.rip = true;
					Music.stopSound();
					Music.startSound("Music\\GOW1.wav", true);
				}
*/
				if (keyCode == KeyEvent.VK_4) {
					OverworldState.player.Dab();	
					if (!Music.boosted) {
						Music.boost();
					}
				} else {
					if (Music.boosted && keyCode != KeyEvent.VK_SPACE) {
						Music.norm();
						}
				}
/*
				if (keyCode == KeyEvent.VK_B) {
					Player.isMoving = true;
					BattleState.startSwampBattle();
				
				}
				*/
			}
		}
	}

	public void keyReleased(KeyEvent a) {
	}

	public void keyTyped(KeyEvent d) {

	}

}