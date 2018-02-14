package GameStateManager;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import FileSystem.MapRetrevial;
import Maps.Link;
import Maps.MovementMap;
import Player.Player;
import TextMap.TextMap;
import java.io.*;
import sun.audio.*;
public class OverworldState extends GameState{
	/*
	 * GameState that loads the text base view that is used mostly for testing
	 */
	
	public static MovementMap movMap = new MovementMap();
	public static TextMap textMap = new TextMap();
	public static Player player = new Player();
	public static ArrayList<Link> links = new ArrayList<Link>();



	/*
	 * loading and initializing objects in OverworldState
	 */
	public OverworldState(){
		Link.startSound("C:\\Users\\DSU\\Documents\\GitHub\\ShrekSavesTheKids\\Music\\Intro.wav"); 
		
		try {
			movMap.loadMap("Test.txt");
			textMap.loadMap("test");			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		movMap.printMap();
		
	}
	
	/*
	 * Passing down the tick() function even more to any object that needs to update
	 */
	public void tick() {
		player.tick();
		try {
			for(Link link:links) {
				link.tick();
			}
		}catch(ConcurrentModificationException e) {
			
		}
	}
	
	
	/*
	 * Passes down the render function
	 */
	public void render(Graphics g) {
		textMap.render(g);
		player.render(g);
		
	}
}
