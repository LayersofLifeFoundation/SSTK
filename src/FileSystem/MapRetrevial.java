package FileSystem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Battle.BattleState;
import Battle.Enemy;
import Battle.Moves;
import GameStateManager.OverworldState;
import Maps.Link;
import NPC.Encounter;
import NPC.NPC;

public class MapRetrevial {

	/*
	 * Central place for all functions needed to read files for the Overworld
	 */

	/*
	 * Function to transfer values into double array from the the given text
	 * file
	 */
	public static void retriveMovementMap(int[][] map, String mapName) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\" + mapName + "\\movement.txt"));
		int count = 0;
		while ((line = in.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				map[count][i] = Integer.parseInt(line.substring(i, i + 1));
			}
			count++;
		}
	}

	/*
	 * Reads in a text map from a file into a double array
	 * 
	 * Not Currently used outside of early testing
	 */
	public static void retriveTextMap(String[][] map, String mapName) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\TextMaps\\" + mapName + "\\map.txt"));
		//Resets the current array to clear any other values
		for (int i = 0; i < 50; i++) {
			for (int k = 0; k < 50; k++) {
				map[i][k] = " ";
			}
		}
		
		//Reading in the values
		int count = 0;
		while ((line = in.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				map[count][i] = line.substring(i, i + 1);
			}
			count++;
		}
		
		//Call to start retrieving "Links" which are points in the map that transfer the player to a different map 
		retiveLinks(OverworldState.links, mapName);
	}

	/*
	 * Populates the ArrayList links that allows transfer between different areas
	 */

	public static void retiveLinks(ArrayList<Link> links, String mapName) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\" + mapName + "\\links.txt"));
		int count = 0;
		links.clear();

		while ((line = in.readLine()) != null) {
			
			//setting all the variables for the links
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
			links.get(count).setLinkMap(line);
			line = in.readLine();
			links.get(count).setNextTrack(line);
			count++;

		}
	}

	
	
	/*
	 * Reads in the variables for the NPCs in a map
	 */
	public static void readNPC(String mapName) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\" + mapName + "\\NPC.txt"));
		int count = 0;
		OverworldState.npcs.clear();

		while ((line = in.readLine()) != null) {

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
	public static void readEncounter(String mapName) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\" + mapName + "\\Encounters.txt"));
		int count = 0;
		OverworldState.encounter.clear();
		//System.out.println(mapName);
		while ((line = in.readLine()) != null) {
			
			OverworldState.encounter.add(new Encounter());

			OverworldState.encounter.get(count).setNpcNum(Integer.parseInt(line));
		//	System.out.println(line);
			line = in.readLine();
			OverworldState.encounter.get(count).setDirec(line);
			//System.out.println(line);
			line = in.readLine();
			OverworldState.encounter.get(count).setDist(Integer.parseInt(line));
			//System.out.println(line);
			line = in.readLine();
			OverworldState.encounter.get(count).setEName(line);
			count++;
		}
		in.close();
	}

	
	public static void loadEnemy(Enemy e, String mapName) throws IOException {
		//System.out.println("Loading Character Now");
		Random rando = new Random();
		int enemyNo; 
		//%4 is tmp this is # of mons in area
		enemyNo = Math.abs(rando.nextInt() % 6) + 2;
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\" + mapName + "\\Enemies.txt"));
		if(e.equals(BattleState.shrek)) 
			enemyNo = 1;
		for (int j = 0; j < enemyNo; j++) {
			e.moves.clear();
			line = in.readLine();
			e.setImage(line);
			line = in.readLine();
			e.setName(line);
			line = in.readLine();
			e.setHP(Double.parseDouble(line)); 
			// read four moves and store them
			for (int i = 0; i < 4; i++) {
				e.moves.add(new Moves());
				line = in.readLine();
				e.moves.get(i).setName(line);
				line = in.readLine();
				e.moves.get(i).setDamage(Integer.parseInt(line));
				line = in.readLine();
				e.moves.get(i).setAccuracy(Integer.parseInt(line));
				line = in.readLine();
				e.moves.get(i).setSound(line);
			}
		}
		//System.out.println("Done! Loaded " + e.name);	
	}
}