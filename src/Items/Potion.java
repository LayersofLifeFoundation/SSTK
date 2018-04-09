package Items;

import Battle.BattleState;
import GameStateManager.OverworldState;

public class Potion extends Item{

	String name;
	int value;
	String description;
	int q;
	
	
	public Potion(int t, int quantity) {
		if(t == 0) {
			value = 30;
			name = "Small Potion";
		}else if(t == 1) {
			value = 60;
			name = "Medium Potion";
		}else if(t == 2) {
			value = 100;
			name = "Large Potion";
		}
		description = "Restores " + value + " health points";
		q = quantity;
	}
	
	public void use() {
		BattleState.shrek.hpLev += value;
		System.out.println(BattleState.shrek.hpLev);
	
		q--;
	}
	
	public String returnName() {
		return name;
	}
	
	public int returnQ() {
		System.out.println(q);
		return q;
		
	}
	
	
	
	
}
