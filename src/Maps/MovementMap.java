package Maps;

import java.io.IOException;

import FileSystem.MapRetrevial;

public class MovementMap {
	
	static int[][] map = new int[100][100];
	
	public static void loadMap(String mapName) throws IOException {
		MapRetrevial.retriveMovementMap(map, mapName);
	}
	
	public static boolean canMoveTo(int x, int y) {
		if(map[x][y] == 0) {
			return true;
		}
		return false;
	}
	
	public static void printMap() {
		for(int i = 0; i < 10; i++) {
			for(int k = 0; k < 10; k++) {
				System.out.print(map[i][k] + " ");
			}
			System.out.println();
		}

	}

}
