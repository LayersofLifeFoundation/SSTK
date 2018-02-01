package GameStateManager;

import java.awt.Graphics;
import java.io.IOException;

import Maps.MovementMap;

public class OverworldState extends GameState{
	
	MovementMap movMap = new MovementMap();

	public OverworldState(){
		try {
			movMap.loadMap("test.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movMap.printMap();
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
}
