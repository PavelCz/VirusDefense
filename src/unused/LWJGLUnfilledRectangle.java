package unused;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.graphics.RenderObject;

/**
 * @author Pavel see LWJGLRectangle
 */
public class LWJGLUnfilledRectangle extends RenderObject {
	// private Quad quad;
	private Vector3f colorsRGB;

	private int width;
	private int height;

	public LWJGLUnfilledRectangle(int width, int height) {
		this.width = width;
		this.height = height;
		this.colorsRGB = new Vector3f();
		this.setColor(0.1f, 0.5f, 0.1f);
	}

	public LWJGLUnfilledRectangle(int width, int height, float r, float g, float b) {
		this.width = width;
		this.height = height;
		this.colorsRGB = new Vector3f();
		this.setColor(r, g, b);

	}

	public void draw(float x, float y) {
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(this.colorsRGB.getX(), this.colorsRGB.getY(), this.colorsRGB.getZ());

		// draw quad
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2f(0 + x, 0 + y);
		GL11.glVertex2f(this.width + x, 0 + y);
		GL11.glVertex2f(this.width + x, this.height + y);
		GL11.glVertex2f(0 + x, this.height + y);
		GL11.glEnd();
	}

	public void setColor(float r, float g, float b) {
		this.colorsRGB.set(r, g, b);
	}

	public void setWidth(float width) {
		this.width = (int) width;
	}

}
