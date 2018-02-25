package FileSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Battle.BattleState;
import Battle.Enemy;
import GameStateManager.OverworldState;
import Maps.Link;
import NPC.NPC;
import Battle.*;

public class MapRetrevial {
	
	/*
	 * Central place for all functions needed to read files for the Overworld
	 */
	
	
	
	/*
	 * Function to transfer values into double array map from the the given text file
	 */
	public static void retriveMovementMap(int[][] map, String mapName ) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\"+ mapName + "\\movement.txt"));
		int count = 0;
		while((line = in.readLine()) != null) {
			for(int i = 0; i < line.length();i++) {
				map[count][i] = Integer.parseInt(line.substring(i, i + 1));
			}
			count++;
		}
	}
	
	
	/*
	 * Does the same thing but for the Text map that is shown for testing 
	 * 
	 */
	public static void retriveTextMap(String[][] map, String mapName ) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\TextMaps\\" + mapName + "\\map.txt"));
		for(int i = 0; i < 50; i++) {
			for(int k = 0; k < 50; k++) {
				map[i][k] = " ";
			}
		}
		int count = 0;
		while((line = in.readLine()) != null) {
			for(int i = 0; i < line.length();i++) {
				map[count][i] = line.substring(i, i + 1);
			}
			count++;
		}
		retiveLinks(OverworldState.links, mapName);
	}
	
	/*
	 * Populates the ArrayList links that allows transfer between different areas
	 */
	
	public static void retiveLinks(ArrayList<Link> links, String mapName) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\TextMaps\\" + mapName + "\\links.txt"));
		int count = 0;
		links.clear();
		
		
	
		while((line = in.readLine()) != null) {
				links.add(new Link());
				links.get(count).setX(Integer.parseInt(line));
				line = in.readLine();
				links.get(count).setY(Integer.parseInt(line));
				line = in.readLine();
				links.get(count).setToX(Integer.parseInt(line));
				line = in.readLine();
				links.get(count).setToY(Integer.parseInt(line));
				line = in.readLine();
				links.get(count).setLinkMovement(line);
				line = in.readLine();
				links.get(count).setLinkText(line);
				line = in.readLine();
				links.get(count).setNextTrack(line);
				count++;
		
		}
	}
	
	public static void readNPC(String mapName) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\" + mapName + "\\NPC.txt"));
		int count = 0;
		OverworldState.npcs.clear();
		
		while((line = in.readLine()) != null) {
				
			OverworldState.npcs.add(new NPC());

			OverworldState.npcs.get(count).setName(line);
				line = in.readLine();
				OverworldState.npcs.get(count).setX(Integer.parseInt(line));
				line = in.readLine();
				OverworldState.npcs.get(count).setY(Integer.parseInt(line));
				line = in.readLine();
				OverworldState.npcs.get(count).setR(Integer.parseInt(line));
				line = in.readLine();
				OverworldState.npcs.get(count).setC(Integer.parseInt(line));
				line = in.readLine();
				OverworldState.npcs.get(count).setText(line);
				OverworldState.npcs.get(count).setSprite();
				count++;
		
		}
		
	}

	public static void readEnemy() throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\DSU\\Documents\\GitHub\\ShrekSavesTheKids\\map\\World1\\Enemies.txt"));
		int count = 0;
		BattleState.nmy.clear();
		
		while((line = in.readLine()) != null) {
				BattleState.nmy.add(new Enemy());	
				BattleState.nmy.get(count).setImage(line);
				line = in.readLine();
				BattleState.nmy.get(count).setName(line);
				line = in.readLine();
				BattleState.nmy.get(count).setHP(Double.parseDouble(line));
				line = in.readLine();
				BattleState.nmy.get(count).setM1(line);
				line = in.readLine();
				BattleState.nmy.get(count).setM1d(Integer.parseInt(line));
				line = in.readLine();
				BattleState.nmy.get(count).setM1a(Integer.parseInt(line));
				line = in.readLine();

				BattleState.nmy.get(count).setM2(line);
				line = in.readLine();
				BattleState.nmy.get(count).setM2d(Integer.parseInt(line));
				line = in.readLine();
				BattleState.nmy.get(count).setM2a(Integer.parseInt(line));
				line = in.readLine();
				
				BattleState.nmy.get(count).setM3(line);
				line = in.readLine();
				BattleState.nmy.get(count).setM3d(Integer.parseInt(line));
				line = in.readLine();
				BattleState.nmy.get(count).setM3a(Integer.parseInt(line));
				line = in.readLine();
				
				BattleState.nmy.get(count).setM4(line);
				line = in.readLine();
				BattleState.nmy.get(count).setM4d(Integer.parseInt(line));
				line = in.readLine();
				BattleState.nmy.get(count).setM4a(Integer.parseInt(line));
				line = in.readLine();
				
				count++;
		
		}
		
	}
	
}
