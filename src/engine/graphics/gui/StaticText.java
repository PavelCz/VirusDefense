package engine.graphics.gui;

import org.newdawn.slick.Graphics;

import engine.graphics.Text;

public class StaticText extends GUI {
	private Text text;
	
	public StaticText(float x, float y, String text) {
		super(x,y);
		
		this.text = new Text(text, null);
		
	}

	@Override
	public void draw(Graphics graphics) {
		this.text.setGraphics(graphics);
		this.text.draw(this.x, this.y);
		

	}

}
