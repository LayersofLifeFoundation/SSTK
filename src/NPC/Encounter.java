package NPC;

import Battle.BattleState;
import GameStateManager.OverworldState;

public class Encounter {

	public static boolean disableKeys;

	// If OverworldState.player is in encounter box, walk to them, talk, and start
	// battle
	public static void encounterBoxCk(int npcNum, char direc, int dist) {
		if (!OverworldState.npcs.get(npcNum).talked) {
			int npcX = OverworldState.npcs.get(npcNum).x;
			int npcY = OverworldState.npcs.get(npcNum).y;
			boolean inBox = false;

			if (direc == 'l' && npcY == OverworldState.player.y && npcX < OverworldState.player.x + dist
					&& npcX > OverworldState.player.x && !OverworldState.inDialog) {
				inBox = true;
				try {
					OverworldState.player.moveRight();
				} catch (InterruptedException e) {
				}
			} else if (direc == 'r' && npcY == OverworldState.player.y && npcX > OverworldState.player.x - dist
					&& npcX < OverworldState.player.x && !OverworldState.inDialog) {
				inBox = true;
				try {
					OverworldState.player.moveLeft();

				} catch (InterruptedException e) {
				}
			} else if (direc == 'u' && npcX == OverworldState.player.x && npcY < OverworldState.player.y + dist
					&& npcY > OverworldState.player.y && !OverworldState.inDialog) {
				inBox = true;
				try {
					OverworldState.player.moveDown();
				} catch (InterruptedException e) {
				}
			} else if (direc == 'd' && npcX == OverworldState.player.x && npcY > OverworldState.player.y - dist
					&& npcY < OverworldState.player.y && !OverworldState.inDialog) {
				inBox = true;
				try {
					OverworldState.player.moveUp();
				} catch (InterruptedException e) {
				}
			}
			if (inBox) {
				OverworldState.interact();
				disableKeys = true;
				if (OverworldState.inDialog) {
					OverworldState.player.isMoving = true;
					BattleState.bs = "FidgetSpinners.wav";
					BattleState.batMus = "VerBat.wav";
					BattleState.startSwampBattle();
					OverworldState.npcs.get(npcNum).talked = true;
				}
			}
		}
	}
	
	public void tick() {
		
				//toward end
				//encounterBoxCk(6, 'r', 10);
				//border control
				//encounterBoxCk(11, 'l', 20);
				//encounterBoxCk(10, 'l', 2);
				//on bend
				//Encounter.encounterBoxCk(5 , 'd', 5);
				//sign
				encounterBoxCk(4 , 'u', 7);	
		
		
	}
	
}
