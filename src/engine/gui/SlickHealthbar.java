package engine.gui;

import org.newdawn.slick.Graphics;

import towerDefense.Gameplay;
import engine.graphics.SlickTwoColoredBar;

/**
 * @author Pavel see LWJGLRectangle
 */
public class SlickHealthbar extends GUI {
	// protected MyVector2f coordinates;
	private int maxHealth;
	private float healthLeft;
	private int length;
	private int height;
	private SlickTwoColoredBar bar;

	public SlickHealthbar(Graphics graphics, float x, float y, int maxHealth, int length, int height) {
		super(x, y);
		this.maxHealth = maxHealth;
		this.healthLeft = maxHealth;
		this.length = length;
		this.height = height;

		this.bar = new SlickTwoColoredBar(graphics, this.length, this.height);

	}

	public void changeHealth(float amount) {
		this.healthLeft += amount;
		this.bar.setFractionLeft(this.healthLeft / this.maxHealth);
	}

	public void setHealth(float amount) {
		this.healthLeft = amount;
		this.bar.setFractionLeft(this.healthLeft / this.maxHealth);
	}

	@Override
	public void draw() {
		this.bar.setFractionLeft(this.healthLeft / this.maxHealth);
		this.bar.draw(this.x, this.y, Gameplay.GLOBAL_GAME_SCALE);

	}

	public void setBordered(boolean bordered) {
		this.bar.setBordered(bordered);
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

}
