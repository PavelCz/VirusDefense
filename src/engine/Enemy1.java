package engine;

import engine.graphics.Sprite;

public class Enemy1 extends Enemy {
	
	public Enemy1(Waypoint startingWaypoint) {
		super(75, 50,100, 0.1f, new Sprite("./data/Enemy_1_klein.png", 0.5f), startingWaypoint, Waypoint.DOWN);
	}

	@Override
	public void draw() {
		this.sprite.draw(this.x - this.sprite.getWidth()/2, this.y - this.sprite.getHeight()/2);

	}
	
	

}
