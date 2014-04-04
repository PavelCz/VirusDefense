package engine;

import engine.graphics.Sprite;

public class EnemyType {
	private float radius;
	private int health;
	private float speed;
	protected Sprite sprite;
	private Game game;

	public EnemyType(int health, float speed, Sprite sprite, int direction, Game game, float radius) {
		this.health = health;
		this.speed = speed;
		this.sprite = sprite;
		this.radius = radius;
	}

	public Enemy createEnemy() {
		return new Enemy(this.health, this.speed, this.sprite, this.game.getWaypoints(), this.game.getWaypoints().getDirection(),
				this.game, this.radius);
	}
}
