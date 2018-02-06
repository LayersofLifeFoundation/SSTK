package FileSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import GameStateManager.OverworldState;
import Maps.Link;

public class MapRetrevial {
	
	
	public static void retriveMovementMap(int[][] map, String mapName ) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\MovementMaps\\" + mapName));
		int count = 0;
		while((line = in.readLine()) != null) {
			for(int i = 0; i < line.length();i++) {
				map[count][i] = Integer.parseInt(line.substring(i, i + 1));
			}
			count++;
		}
	}
	
	public static void retriveTextMap(String[][] map, String mapName ) throws IOException {
		String line;
		BufferedReader in = new BufferedReader(new FileReader("map\\TextMaps\\" + mapName + "\\map.txt"));
		int count = 0;
		while((line = in.readLine()) != null) {
			for(int i = 0; i < line.length();i++) {
				map[count][i] = line.substring(i, i + 1);
			}
			count++;
		}
		retiveLinks(OverworldState.links, mapName);
	}
	
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
				count++;
		
		}
	}

}
