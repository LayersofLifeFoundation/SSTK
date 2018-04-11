package GameStateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;

import Battle.BattleState;
import FileSystem.MapRetrevial;
import Maps.Link;
import Maps.MovementMap;
import NPC.Encounter;
import NPC.NPC;
import Player.Hitmarker;
import Player.Player;
import Sounds.Music;
import TextMap.TextMap;
public class OverworldState extends GameState {

	public static MovementMap movMap = new MovementMap();
	public static TextMap textMap = new TextMap();
	public static Player player = new Player();
	public static Hitmarker hitmarker = new Hitmarker();
	public static ArrayList<Link> links = new ArrayList<Link>();
	public static BufferedImage map;
	BufferedImage subMap;

	public static ArrayList<NPC> npcs = new ArrayList<NPC>();
	static String dialog;
	static ArrayList<String> lines = new ArrayList<String>();
	ArrayList<String> displayText = new ArrayList<String>();
	public static ArrayList<Encounter> encounter = new ArrayList<Encounter>();
	public static boolean inDialog = false;
	static int currentLine = 0;
	Font font = new Font("Courier New", Font.PLAIN, 30);
	Font bold = new Font("Courier New", Font.BOLD, 30);
	static String npcName;
	public static String swampMusic = "Music\\All_Star_Chip.wav";
	public static boolean disableKeys;
	public static boolean changeLinks = false;
	public static String linksLocation;
	public static String mapLocation;
	public static String song = "Music\\All_Star_Chip.wav";
	/*
	 * loading and initializing objects in OverworldState
	 */
	public OverworldState() {

		try {
			movMap.loadMap("ShrekHouse");
			MapRetrevial.retiveLinks(links, "ShrekHouse");
			//MapRetrevial.readNPC("ShrekHouse");
			//MapRetrevial.readEncounter("World1");
			map = ImageIO.read(getClass().getResource("/map.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//movMap.printMap();
	}
	
	public void setMap(String m) {
		try {
			map = ImageIO.read(getClass().getResource(m));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void stateOverworldState() {
		Music.startSound(song, true);
		do {
			GameStateManager.battle.nextEnemy(BattleState.enemy);
		}while(BattleState.enemy.name == "Verizon Employee");
		Player.isMoving = false;
		Encounter.disableKeys = false;
		}

	/*
	 * returns if an npc is standing at given location
	 */
	public static boolean NPCPresent(int x, int y) {
		for (NPC npc : npcs) {
			if (npc.x == x && npc.y == y) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Determines what action should be taken on the press of the spacebar
	 */
	public static void interact() {
		if (inDialog) {
			//scrolling through dialog box
			currentLine += 3;
			if (currentLine >= lines.size()) {
				inDialog = false;
			}

		} else {
			//checking if the player is trying to interact with an NPC
			for (NPC npc : npcs) {
				if (player.getFacing().equals("Up")) {
					if (npc.x == player.returnX() && npc.y == player.returnY() - 1) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				} else if (player.getFacing().equals("Down")) {
					if (npc.x == player.returnX() && npc.y == player.returnY() + 1) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				} else if (player.getFacing().equals("Left")) {
					if (npc.x == player.returnX() - 1 && npc.y == player.returnY()) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				} else if (player.getFacing().equals("Right")) {
					if (npc.x == player.returnX() + 1 && npc.y == player.returnY()) {
						dialog = npc.getText();
						inDialog = true;
						calculateLines();
						npcName = npc.name;
					}
				}
			}
		}
	}

	/*
	 * Breaks up a line of text into smaller parts that fit on screen
	 */
	public static void calculateLines() {
		currentLine = 0;
		lines.clear();
		int maxPer = 58;
		int n = 0;
		String line;
		do {
			if (n + maxPer > dialog.length()) {
				//case for the last line
				line = dialog.substring(n, dialog.length());
				if(line.substring(0, 1).equals(" ")) {
					line = line.substring(1, line.length());
				}
				lines.add(line);
				n = dialog.length();
			} else {
				int i = maxPer;
				//setting i to a space to not cut off words
				while (!dialog.substring(n + i, n + i + 1).equals(" ")) {
					i--;
				}
				line = dialog.substring(n, n + i);
				//getting rid of any extra spaces
				if(line.substring(0, 1).equals(" ")) {
					line = line.substring(1, line.length());
				}
				lines.add(line);
				n += i;
			}
		} while (n < dialog.length());
	}
			
	/*
	 * Passing down the tick() function even more to any object that needs to update
	 */
	public void tick() {
		player.tick();
			
		try {
			for (Link link : links) {
				link.tick();
			}
		} catch (ConcurrentModificationException e) {
		}
		int count = 0;
		for (Encounter e : encounter) {
			e.tick();
			count++;
			
		}
		//*This is when the BattleState starts*
		//It is triggered when a key sound finishes
		
		if(Music.audioClip.getMicrosecondLength() <= Music.audioClip.getMicrosecondPosition() 
				&& Music.audioFile.getName().equals(BattleState.bs)) {
			Player.isMoving = true;
			Game.gameStateManager.changeState(Game.gameStateManager.battleStateNum);
			Music.startSound("Music\\" + BattleState.batMus, true);	
		}
		
		if(changeLinks) {
			try {
				MapRetrevial.retiveLinks(links, linksLocation);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Game Over actions
	//GO str is rendered below
	//music will be unique based on map in future 
	public static void gameOver() {
			Music.stopSound();
			Music.startSound("Music\\GOW1.wav", true);
	}
	/*
	 * Passes down the render function
	 */
	public void render(Graphics g) {
		subMap = map.getSubimage(1000 + player.returnX() * Game.PIXSIZE - 11 * Game.PIXSIZE,
			1000 + player.returnY() * Game.PIXSIZE - 6 * Game.PIXSIZE, Game.WIDTH + 30, Game.HEIGHT + 30);
		g.drawImage(subMap, 0, 0, null);
		player.render(g);
		hitmarker.render(g);
		for (NPC npc : npcs) {
			npc.render(g);
		}
		if (inDialog) {
			//displays the dialog box
			g.setFont(bold);
			g.setColor(Color.WHITE);
			g.fillRect(0, Game.HEIGHT - Game.PIXSIZE * 3, Game.WIDTH + 10, Game.HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(0, Game.HEIGHT - Game.PIXSIZE * 3, Game.WIDTH + 10, Game.HEIGHT);
			g.drawString(npcName, 20 ,Game.HEIGHT - Game.PIXSIZE * 3 + 40);
			g.setFont(font);
			for (int i = currentLine; i < lines.size() && i < currentLine + 3; i++) {
				g.drawString(lines.get(i), 20, Game.HEIGHT - Game.PIXSIZE * 3 + 70 + 30 * (i - currentLine));
			}
		}
		
		if(BattleState.shrek.rip) {
			g.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 100));
			g.setColor(Color.BLACK);
			g.drawString("GAME OGRE",  145 , Game.HEIGHT / 2 - 105);
			g.setColor(Color.RED);
			g.drawString("GAME OGRE",  150 , Game.HEIGHT / 2 - 100);
			}
		
	}
}
