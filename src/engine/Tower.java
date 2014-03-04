package engine;

import engine.graphics.Sprite;

public abstract class Tower extends Entity implements Drawable{
	private int cost;
	private int radius;
	private Weapon weapon;
	protected Sprite sprite;

	public Tower(float x, float y, int cost, int radius, Weapon weapon) {
		super(x, y);
		
		this.cost = cost;
		this.radius = radius;
		this.weapon = weapon;

		
	}
	
	
	
}
