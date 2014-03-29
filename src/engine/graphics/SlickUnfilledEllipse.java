package engine.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Ellipse;

public class SlickUnfilledEllipse extends SlickEllipse {

	public SlickUnfilledEllipse(Graphics graphics, int width, int height, Color color) {
		super(graphics, width, height, color);
	}

	public SlickUnfilledEllipse(Graphics graphics, int width, int height, float r, float g, float b) {
		super(graphics, width, height, r, g, b);
	}

	public SlickUnfilledEllipse(Graphics graphics, int width, int height) {
		super(graphics, width, height);
	}
	public void draw(float x, float y) {
		graphics.draw(new Ellipse(x + this.width/2, y+this.height/2, this.width/2,
				this.height/2), new GradientFill(0, 0,this.color, this.width/2, this.height/2,
				this.color));
	}
}
