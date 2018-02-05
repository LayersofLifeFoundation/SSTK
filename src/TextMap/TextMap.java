package TextMap;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import FileSystem.MapRetrevial;
import GameStateManager.OverworldState;

public class TextMap {
	
	static Font font  = new Font("Courier New", Font.PLAIN, 20);
	static String map[][] = new String[10][10];
	
	public static void loadMap(String mapName) throws IOException {
		MapRetrevial.retriveTextMap(map, mapName);
	}
	
	
	public static void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(font);
		for(int i = 0; i < 10; i++) {
			for(int k = 0; k < 10; k++) {
				if(k != OverworldState.player.returnY() || i != OverworldState.player.returnX()) {
					g.drawString(map[i][k], (i * 20), (k * 20) + 20);
				}
			}
		}
		
	}

}
