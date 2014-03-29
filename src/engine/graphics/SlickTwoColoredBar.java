package engine.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class SlickTwoColoredBar extends RenderObject {
	private SlickRectangle base;
	private SlickRectangle health;
	private float length;
	private float height;
	private float length2;
	
	
	
	public SlickTwoColoredBar(Graphics graphics, float length, float height) {
		this.length = length;
		this.height = height;
		
		this.base = new SlickRectangle(graphics, (int)this.length, (int)this.height,Color.red); 
		this.health = new SlickRectangle(graphics, (int)this.length, (int)this.height,Color.green);
		
		this.length2 = length;
	}



	@Override
	public void draw(float x, float y) {
		this.base.draw(x, y);
		this.health.setWidth(length2);
		this.health.draw(x, y);

	}
	
	public void setFractionLeft(float fraction) {
		this.length2 = this.length * fraction;
	}
}
