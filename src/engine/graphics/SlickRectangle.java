package engine.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Pavel A Rectangle based on my other Project JBreakout. This Rectangle is based on LWJGL. I tworked once, but now it doesn't
 *         seem to work anymore
 */
public class SlickRectangle extends RenderObject {
	protected Color color;
	protected Graphics graphics;
	protected int width;
	protected int height;

	public SlickRectangle(Graphics graphics, int width, int height) {
		this(graphics, width,height, Color.pink);
	}

	public SlickRectangle(Graphics graphics, int width, int height, float r, float g, float b) {
		this(graphics, width,height, new Color(r, g, b));

	}

	public SlickRectangle(Graphics graphics, int width, int height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.graphics = graphics;
	}

	public void draw(float x, float y) {
		this.graphics.fill(new Rectangle(x, y, this.width, this.height), new GradientFill(0, 0, this.color, this.width, this.height,
				this.color));
	}

	public void setWidth(float width) {
		this.width = (int) width;
	}

}
