package engine.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Text extends RenderObject {
	private TrueTypeFont font;
	private String text;
	private Color color;

	public Text(TrueTypeFont font, String text, Color color) {
		this.font = font;
		this.text = text;
		this.color = color;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	@Override
	public void draw(float x, float y) {
		this.font.drawString( x, y, this.text, this.color);

	}

}
