package engine.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Pavel A Rectangle based on my other Project JBreakout. This Rectangle is based on LWJGL. I tworked once, but now it doesn't
 *         seem to work anymore
 */
public class SlickEllipse extends SlickRectangle {

	
	public SlickEllipse(Graphics graphics, int width, int height, Color color) {
		super(graphics, width, height, color);
	}

	public SlickEllipse(Graphics graphics, int width, int height) {
		super(graphics, width, height);
	}

	public SlickEllipse(Graphics graphics, int width, int height, float r, float g, float b) {
		super(graphics, width, height, r, g, b);
	}

	public void draw(float x, float y) {
		graphics.draw(new Ellipse(x + this.width/2, y+this.height/2, this.width/2,
				this.height/2), new GradientFill(0, 0,this.color, this.width/2, this.height/2,
				this.color));
	}


}
