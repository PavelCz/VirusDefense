package engine;

import towerDefense.Gameplay;
import towerDefense.TowerDefense;
import engine.graphics.Sprite;

public abstract class Projectile extends Entity {
	protected int Radius;
	protected float damage;
	protected Gameplay game;
	protected float targetX;
	protected float targetY;
	protected float speed;
	protected MyVector2f velocity;
	protected Sprite sprite;

	public Projectile(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void update(int delta) {
	}

	public void draw() {
		this.sprite.draw(this.x - Gameplay.SIZE / 2, this.y - Gameplay.SIZE / 2, TowerDefense.GLOBAL_GAME_SCALE);
	}

}
