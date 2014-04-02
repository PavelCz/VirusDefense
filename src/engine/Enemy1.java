package engine;

import engine.graphics.Sprite;

public class Enemy1 extends Enemy {
	
	public Enemy1(Waypoint startingWaypoint, Game game) {
		super(1000, 0.1f, new Sprite("enemy/v1.png", 0.05f), startingWaypoint, Waypoint.DOWN, game);
	}
}
