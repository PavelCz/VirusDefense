package engine.graphics;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Text extends RenderObject {
	private TrueTypeFont font;
	private String text;
	private Color color;
	private int height;

	public Text(int height, String text, Color color) {
		this.height = height;
		Font fontSettings = new Font("Verdana", Font.PLAIN, this.height);
		this.font = new TrueTypeFont(fontSettings, true);
		this.text = text;
		this.color = color;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void draw(float x, float y) {
		this.font.drawString(x, y, this.text, this.color);

	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getWidth() {
		return this.font.getWidth(this.text);
	}

	public int getHeight() {
		return this.font.getHeight();
	}
}
