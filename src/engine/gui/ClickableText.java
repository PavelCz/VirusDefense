package engine.gui;

import org.newdawn.slick.Color;

import towerDefense.Gameplay;
import engine.graphics.Text;

public class ClickableText extends Clickable {
	private Text text;

	public ClickableText(float x, float y, String text, float globalScale, Gameplay game) {
		super(x, y, game);
		this.text = new Text(15, text, Color.white, globalScale);
		this.collisionWidth = this.text.getWidth();
		this.collisionHeight = this.text.getHeight();
	}

	@Override
	public void onClick() {
		super.onClick();
		this.text.setColor(Color.blue);

	}

	@Override
	public void onRelease() {
		super.onRelease();
		this.text.setColor(Color.white);

	}

	@Override
	public void draw() {
		this.text.draw(this.x, this.y, 1f);

	}

	@Override
	public void onHover() {
		this.text.setColor(Color.gray);

	}

	@Override
	public void onUnHover() {
		this.text.setColor(Color.white);

	}
}
