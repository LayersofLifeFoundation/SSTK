package NPC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Battle.BattleState;
import GameStateManager.OverworldState;

public class Encounter {

	public static boolean disableKeys;
	
	public int npcNum;
	public String direc;
	public int dist;
	
	public void tick() {
		//System.out.println(npcNum);
		//System.out.println(direc);
		//System.out.println(dist);
		
		if (!OverworldState.npcs.get(npcNum).talked) {
			int npcX = OverworldState.npcs.get(npcNum).x;
			int npcY = OverworldState.npcs.get(npcNum).y;
			boolean inBox = false;
			//System.out.println(npcY);
			if (direc.equals("l") && npcY == OverworldState.player.y && npcX < OverworldState.player.x + dist
					&& npcX > OverworldState.player.x && !OverworldState.inDialog) {
				inBox = true;
				try {
					OverworldState.player.moveRight();
				} catch (InterruptedException e) {
				}
			} else if (direc.equals("r") && npcY == OverworldState.player.y && npcX > OverworldState.player.x - dist
					&& npcX < OverworldState.player.x && !OverworldState.inDialog) {
				inBox = true;
				try {
					OverworldState.player.moveLeft();

				} catch (InterruptedException e) {
				}
			} else if (direc.equals("u") && npcX == OverworldState.player.x && npcY < OverworldState.player.y + dist
					&& npcY > OverworldState.player.y && !OverworldState.inDialog) {
					inBox = true;
				try {
					OverworldState.player.moveDown();
				} catch (InterruptedException e) {
				}
			} else if (direc.equals("d") && npcX == OverworldState.player.x && npcY > OverworldState.player.y - dist
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
		
				//toward end
				//encounterBoxCk(6, 'r', 10);
				//border control
				//encounterBoxCk(11, 'l', 20);
				//encounterBoxCk(10, 'l', 2);
				//on bend
				//Encounter.encounterBoxCk(5 , 'd', 5);
				//sign
				//encounterBoxCk(4 , 'u', 7);	
	}
	
	
	public void setNpcNum(int n){
		npcNum = n;
	}
	public void setDirec(String n) {
		direc = n;
	}
	public void setDist(int n){
		dist = n;
	}
	
	
	
}
