package GameStateManager;

import java.awt.Graphics;
import java.util.ArrayList;



public class GameStateManager {

	private ArrayList<GameState> gameStates = new ArrayList<GameState>();

	private int currentState = 0;
	
	private OverworldState overworld = new OverworldState();
	public int overworldStateNumber = 0;
	
	public GameStateManager() {
		gameStates.add(overworld);
	}

	public void changeState(int i) {
		currentState = i;
	}

	public void tick() {

		gameStates.get(currentState).tick();
	}

	public int getState() {
		return currentState;
	}

	public void render(Graphics g) {
		gameStates.get(currentState).render(g);

	}
	
	
	
}
