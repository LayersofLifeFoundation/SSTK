package FileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import GameStateManager.OverworldState;

public class Save {

	
	
	public static void save() {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("Save\\save.txt"));
			write.write(OverworldState.player.returnX() + "");
			write.newLine();
			write.write(OverworldState.player.returnY()+ "");
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void load() throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new FileReader("Save\\save.txt"));
		OverworldState.player.setX(Integer.parseInt(in.readLine()));
		OverworldState.player.setY(Integer.parseInt(in.readLine()));
	}
}
