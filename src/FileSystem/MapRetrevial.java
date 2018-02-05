package FileSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		BufferedReader in = new BufferedReader(new FileReader("map\\TextMaps\\" + mapName));
		int count = 0;
		while((line = in.readLine()) != null) {
			for(int i = 0; i < line.length();i++) {
				map[count][i] = line.substring(i, i + 1);
			}
			count++;
		}
	}

}
