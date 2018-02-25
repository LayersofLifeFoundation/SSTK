package GameStateManager;

import java.awt.Graphics;
import java.util.ArrayList;

import Intro.InputState;
import Intro.IntroductionState;



public class GameStateManager {
	/*
	 * Class that decides what should run at what time
	 */

	private ArrayList<GameState> gameStates = new ArrayList<GameState>();

	private int currentState = 1;
	
	public OverworldState overworld = new OverworldState();
	private InputState input = new InputState();
	private IntroductionState intro  = new IntroductionState();
	private OptionState options = new OptionState();
	public static int overworldStateNumber = 0;
	public int introState = 1;
	public int introductionState = 2;
	public int optionState = 3;
	
	public GameStateManager() {
		gameStates.add(overworld);
		gameStates.add(input);
		gameStates.add(intro);
		gameStates.add(options);
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
