package engine;

public class Bomb extends Entity implements Drawable {
	protected int bombRadius;
	protected float damage;

	public Bomb(float x, float y, int bombRadius, float damage) {
		super(x, y);
		this.bombRadius = bombRadius;
		this.damage = damage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
