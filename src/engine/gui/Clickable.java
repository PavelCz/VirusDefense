package engine.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public abstract class Clickable extends GUI {
	protected float collisionWidth, collisionHeight;
	protected boolean clicked = false;

	public Clickable(float x, float y) {
		super(x, y);
	}

	public void onClick() {
		this.clicked = true;
	}

	public void onRelease() {
		this.clicked = false;
	}

	public abstract void onHover();

	public boolean collides(int x, int y) {
		return (x >= this.x && x <= this.x + this.collisionWidth && y >= this.y && y <= this.y + this.collisionHeight);
	}

	public void update(GameContainer container) {
		Input input = container.getInput();
		float x = input.getMouseX();
		float y = input.getMouseY();
		if (!this.clicked && this.collides((int) x, (int) y)) {
			this.onHover();
		} else if (!this.clicked) {
			this.onRelease();
		}
	}
}
