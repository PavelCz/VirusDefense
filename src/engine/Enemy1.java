package engine;

import engine.graphics.Sprite;

public class Enemy1 extends Enemy {
	
	public Enemy1(Waypoint startingWaypoint) {
		super(75, 50,100, 0.5f, new Sprite("./data/Enemy_1_klein.png", 0.5f), startingWaypoint, Waypoint.DOWN);
	}

		
	

}
