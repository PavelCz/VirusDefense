package unused;

import engine.graphics.RenderObject;

public class LWJGLTwoColoredBar extends RenderObject {
	private LWJGLRectangle base;
	private LWJGLRectangle health;
	private float length;
	private float height;
	private float length2;

	public LWJGLTwoColoredBar(float length, float height) {
		this.length = length;
		this.height = height;

		this.base = new LWJGLRectangle((int) this.length, (int) this.height);
		this.base.setColor(0.5f, 0.1f, 0.1f);
		this.health = new LWJGLRectangle((int) this.length, (int) this.height);
		this.health.setColor(0.1f, 0.5f, 0.1f);

		this.length2 = length;
	}

	@Override
	public void draw(float x, float y) {
		this.base.draw(x, y);
		this.health.setWidth(this.length2);
		this.health.draw(x, y);

	}

	public void setFractionLeft(float fraction) {
		this.length2 = this.length * fraction;
	}
}
