package TextMap;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import FileSystem.MapRetrevial;
import GameStateManager.Game;
import GameStateManager.OverworldState;

public class TextMap {
	
	static Font font  = new Font("Courier New", Font.PLAIN, 20);
	static String map[][] = new String[50][50];
	
	/*
	 * retrieves the data for the double array list map
	 */
	public static void loadMap(String mapName) throws IOException {
		MapRetrevial.retriveTextMap(map, mapName);
	}
	
	
	/*
	 * Draws the text map
	 */
	public static void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(font);
		for(int i = 0; i < 50; i++) {
			for(int k = 0; k < 50; k++) {
				if(k != OverworldState.player.returnY() || i != OverworldState.player.returnX()) 
				{
					if(map[k][i] == null) {
						map[k][i] = " ";
										}
					g.drawString(map[k][i], (i * Game.PIXSIZE), (k * Game.PIXSIZE) + 20);
				}
			}
		}
		
	}

}
