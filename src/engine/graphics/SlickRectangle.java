package engine.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Pavel A Rectangle based on my other Project JBreakout. This Rectangle is based on LWJGL. I tworked once, but now it doesn't
 *         seem to work anymore
 */
public class SlickRectangle extends GraphicsRenderObject {
	private Color color;

	private int width;
	private int height;

	public SlickRectangle(int width, int height) {
		this.width = width;
		this.height = height;
		this.color = new Color(Color.cyan);
	}

	public SlickRectangle(int width, int height, float r, float g, float b) {
		this.width = width;
		this.height = height;
		this.color = new Color(r,g,b);

	}
	
	public SlickRectangle(int width, int height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void draw(float x, float y, Graphics graphics) {
		graphics.fill(new Rectangle(x, y, this.width,
				this.height), new GradientFill(0, 0,this.color, this.width, this.height,
				this.color));
	}


	public void setWidth(float width) {
		this.width = (int) width;
	}

}
