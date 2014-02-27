package engine.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import engine.graphics.Text;

public class StaticText extends GUI {
	private Text text;
	private TrueTypeFont font;

	public StaticText(TrueTypeFont font, float x, float y, Color color, String text) {
		super(x, y);
		this.font = font;

		this.text = new Text(font, text, color);

	}

	@Override
	public void draw() {
		this.text.draw(this.x, this.y);

	}

}
