package engine.graphics;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Text extends RenderObject {
	private Font fontSettings;
	private TrueTypeFont font;
	private String text;
	private Color color;
	private int height;
	private boolean visible = true;

	public Text(int height, String text, Color color, float globalScale) {
		this.height = height;
		this.fontSettings = new Font("Verdana", Font.PLAIN, (int) (this.height * globalScale));
		this.font = new TrueTypeFont(this.fontSettings, true);
		this.text = text;
		this.color = color;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void draw(float x, float y, float globalScale) {
		if (this.visible) {
			this.font.drawString(x, y, this.text, this.color);
		}
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

	public void setVisible(boolean visible) {
		this.visible = visible;

	}
}
