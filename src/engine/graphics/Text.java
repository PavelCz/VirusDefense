package engine.graphics;

import org.newdawn.slick.Graphics;

public class Text extends RenderObject {
	private Graphics graphics;
	private String text;

	public Text(String text, Graphics graphics) {
		this.graphics = graphics;
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	@Override
	public void draw(float x, float y) {
		this.graphics.drawString(this.text, x, y);

	}

}
