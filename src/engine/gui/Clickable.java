package engine.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import towerDefense.Gameplay;

public abstract class Clickable extends GUI {
	protected float collisionWidth, collisionHeight;
	protected boolean clicked = false;

	public Clickable(float x, float y) {
		super(x, y);
	}

	public void update(float mouseX, float mouseY) {
		if (this.collides((int) this.x, (int) this.y, Gameplay.GLOBAL_GUI_SCALE)) {
			this.onClick();
			this.game.getSoundHandler().play("press");

		}
	}

	public void onClick() {
		this.clicked = true;
	}

	public void onRelease() {
		this.clicked = false;
	}

	public abstract void onHover();

	public abstract void onUnHover();

	public boolean collides(int x, int y, float globalScale) {
		return (x >= this.x && x <= this.x + this.collisionWidth * globalScale && y >= this.y && y <= this.y + this.collisionHeight
				* globalScale);
	}

	public void update(GameContainer container, float globalScale) {
		Input input = container.getInput();
		float x = input.getMouseX();
		float y = input.getMouseY();
		if (!this.clicked && this.collides((int) x, (int) y, globalScale)) {
			this.onHover();
		} else if (!this.clicked) {
			this.onUnHover();
		}
	}
}
