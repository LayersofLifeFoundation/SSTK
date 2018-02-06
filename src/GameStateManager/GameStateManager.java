package GameStateManager;

import java.awt.Graphics;
import java.util.ArrayList;



public class GameStateManager {
	/*
	 * Class that decides what should run at what time
	 */

	private ArrayList<GameState> gameStates = new ArrayList<GameState>();

	private int currentState = 0;
	
	private OverworldState overworld = new OverworldState();
	public int overworldStateNumber = 0;
	
	public GameStateManager() {
		gameStates.add(overworld);
	}

	/*
	 * Changes what state is currently running
	 */
	public void changeState(int i) {
		currentState = i;
	}

	/*
	 * passes the tick function down
	 */
	public void tick() {

		gameStates.get(currentState).tick();
	}

	
	/*
	 * returns the current state being updated
	 * Mostly used for the KeyListener to decide what keys should be bound to what
	 */
	public int getState() {
		return currentState;
	}

	
	/*
	 * passes the render functions down
	 */
	public void render(Graphics g) {
		gameStates.get(currentState).render(g);

	}
	
	
	
}
