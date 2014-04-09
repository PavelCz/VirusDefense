package engine.gui;

import org.newdawn.slick.Color;

import engine.graphics.Text;

public class ClickableText extends Clickable {
	private Text text;

	public ClickableText(float x, float y, String text) {
		super(x, y);
		this.text = new Text(15, text, Color.white);
	}

	@Override
	public void onClick() {
		this.text.setColor(Color.blue);

	}

	@Override
	public void onRelease() {
		this.text.setColor(Color.white);

	}

	@Override
	public void draw() {
		this.text.draw(this.x, this.y);

	}
}
