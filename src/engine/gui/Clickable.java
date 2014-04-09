package engine.gui;

public abstract class Clickable extends GUI {
	protected float collisionWidth, collisionHeight;

	public Clickable(float x, float y) {
		super(x, y);
	}

	public abstract void onClick();

	public abstract void onRelease();

	public abstract void onHover();

	public boolean checkCollision(int x, int y) {
		return (x >= this.x && x <= this.x + this.collisionWidth && y >= this.y && y <= this.y + this.collisionHeight);
	}
}
