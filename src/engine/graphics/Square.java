package engine.graphics;


public class Square extends RenderObject {
	private LWJGLRectangle rectangle;

	private int length;

	/**
	 * Default Square length is 1, just like a pixel
	 */
	public Square() {
		this(1);

	}
	public Square(int length) {
		this.rectangle = new LWJGLRectangle(length, length);
		this.length = length;

	}
	public Square(int length, float r, float g, float b) {
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
