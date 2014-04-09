package engine.gui;

import org.newdawn.slick.Color;

import engine.Gameplay;
import engine.graphics.Text;

public class StaticText extends GUI {
	private Text text;

	public StaticText(float x, float y, int height, Color color, String text) {
		super(x, y);

		this.text = new Text(height, text, color);

	}

	public StaticText(float x, float y, Color color, String text) {
		this(x, y, Gameplay.STANDARD_TEXT_SCALE, color, text);

	}

	@Override
	public void draw() {
		this.text.draw(this.x, this.y);

	}

	public void setText(String text) {
		this.text.setText(text);
		;
	}

}
