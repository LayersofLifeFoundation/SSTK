package Maps;

import java.io.IOException;

import FileSystem.MapRetrevial;

public class MovementMap {
	
	static int[][] map = new int[100][100];
	
	/*
	 * retrieves the data for the double array map
	 */
	public static void loadMap(String mapName) throws IOException {
		MapRetrevial.retriveMovementMap(map, mapName);
	}
	
	/*
	 * Returns if the given x and y can be a valid place for the player
	 */
	public static int canMoveTo(int x, int y) {
		return map[y][x];
		
	}
	
	/*
	 * prints the double array map
	 */
	public static void printMap() {
		for(int i = 0; i < 10; i++) {
			for(int k = 0; k < 10; k++) {
				//System.out.print(map[i][k] + " ");
			}
			//System.out.println();
		}

	}

}
