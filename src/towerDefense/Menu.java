package towerDefense;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

import engine.GameComponent;
import engine.gui.ExitClickable;
import engine.gui.StartClickable;

public class Menu extends GameComponent {
	private TextField t;

	public Menu(TowerDefense game) {
		super(game);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		super.init(container);
		StartClickable c = new StartClickable(0, 0, this.game);
		this.clickables.add(c);
		this.guiElements.add(c);

		ExitClickable e = new ExitClickable(0, 20, this.game);
		this.clickables.add(e);
		this.guiElements.add(e);

		this.t = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), 0, 100, 75, 25);
		this.t.setText("Player 1");
		this.t.setBorderColor(Color.gray);
		this.t.setBackgroundColor(Color.lightGray);
		this.t.setMaxLength(32);
		
	}

	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			this.t.deactivate();
		}

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
		this.t.render(container, graphics);
	}

}
