package Maps;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import Battle.BattleState;
import FileSystem.MapRetrevial;
import GameStateManager.Game;
import GameStateManager.OverworldState;
import Sounds.Music;

public class Link {
	

	
	int x,y,toX,toY;
	String linkMovement, linkText;
	public static String nextTrack;
	/*
	 * Provides a tile to link different maps
	 */	

	
	/*
	 * A bunch of functions to set variables
	 */
	
	public void setX(int x) {
		this.x = x;
	}
	public void setToX(int x) {
		toX = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setToY(int y) {
		toY = y;
	}
	public void setLinkMovement(String s) {
		linkMovement = s;
	}
	public void setLinkMap(String s) {
		linkText = s;
	}
	public void setNextTrack(String s) {
		nextTrack = s;
	}

	/*
	 * Changes map if player is on given x and y
	 */
	public void tick() {
		if(OverworldState.player.returnX() == x && OverworldState.player.returnY() == y) {
			try {
				MapRetrevial.readNPC(linkMovement);
				MapRetrevial.readEncounter(linkMovement);
				
				
				//Music.stopSound();
				//Music.startSound(nextTrack, true);
				
				OverworldState.player.setX(toX);
				OverworldState.player.setY(toY);
				
				
				OverworldState.movMap.loadMap(linkMovement);
				System.out.println(linkText);
				Game.gameStateManager.overworld.setMap(linkText);
				Game.gameStateManager.overworld.mapLocation = linkText;
				Game.gameStateManager.overworld.linksLocation = linkMovement;
				Game.gameStateManager.overworld.changeLinks = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void render(Graphics g) {
		
	}
	
}
