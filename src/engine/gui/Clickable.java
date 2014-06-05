package engine.gui;

import org.newdawn.slick.GameContainer;
import towerDefense.Gameplay;

public abstract class Clickable extends GUI {
	protected float collisionWidth, collisionHeight;
	protected boolean clicked = false;
	protected Gameplay game;
	protected boolean stayClicked;
	protected boolean active = true;

	public Clickable(float x, float y, Gameplay game, boolean stayClicked) {
		super(x, y);
		this.game = game;
		this.stayClicked = stayClicked;
	}

	public void update(float mouseX, float mouseY, GameContainer container) {
		if (this.collides((int) mouseX, (int) mouseY, Gameplay.GLOBAL_GUI_SCALE)) {
			if (this.clicked) {
				this.onRelease();
			} else {
				this.game.releaseAllClickablesExcept(this);
				this.onClick();

			}
			if (this.active) {
				this.game.getSoundHandler().play("press");
			}
		}

	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
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

	public boolean isStayClicked() {
		return this.stayClicked;
	}

	public boolean isClicked() {
		return this.clicked;
	}

}
