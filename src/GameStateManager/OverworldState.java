package GameStateManager;

import java.awt.Graphics;
import java.io.IOException;

import Maps.MovementMap;
import Player.Player;
import TextMap.TextMap;

public class OverworldState extends GameState{
	
	public static MovementMap movMap = new MovementMap();
	TextMap textMap = new TextMap();
	public static Player player = new Player();

	public OverworldState(){
		try {
			movMap.loadMap("test.txt");
			textMap.loadMap("Test.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		movMap.printMap();
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		textMap.render(g);
		player.render(g);
		
	}
}
