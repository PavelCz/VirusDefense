package unused;

import engine.graphics.RenderObject;


/**
 * @author Pavel
 * see LWJGLRectangle
 */
public class LWJGLSquare extends RenderObject {
	private LWJGLRectangle rectangle;

	private int length;

	/**
	 * Default Square length is 1, just like a pixel
	 */
	public LWJGLSquare() {
		this(1);

	}
	public LWJGLSquare(int length) {
		this.rectangle = new LWJGLRectangle(length, length);
		this.length = length;

	}
	public LWJGLSquare(int length, float r, float g, float b) {
		this(length);
		this.setColor(r, g, b);
	}
	
	

	public void draw(float x, float y) {
		this.rectangle.draw(x, y);
	}
	public void setColor(float r, float g, float b) {
		this.rectangle.setColor(r, g, b);
	}

	
}
