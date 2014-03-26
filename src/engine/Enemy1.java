package engine;

import engine.graphics.Sprite;

public class Enemy1 extends Enemy {
	
	public Enemy1(Waypoint startingWaypoint, Game game) {
		super(1000, 0.25f, new Sprite("enemy/v1.png", 0.5f), startingWaypoint, Waypoint.DOWN, game);
	}

		
	

}
