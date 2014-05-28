package towerDefense;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

import engine.GameComponent;
import engine.gui.GoToMenuButton;

public class Settings extends GameComponent {

	private TextField widthField;
	private TextField heightField;

	public Settings(TowerDefense game, GameContainer container) {
		super(game);

		GoToMenuButton settings = new GoToMenuButton(0, 0, "Back", this.game);
		this.clickables.add(settings);
		this.guiElements.add(settings);
		settings.setX(0);
		settings.setY(TowerDefense.getHeight() - settings.getHeight() * 2);

		this.widthField = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), 0, 100, 50, 25);
		this.widthField.setText(TowerDefense.getWidth() + "");
		this.widthField.setBorderColor(Color.gray);
		this.widthField.setBackgroundColor(Color.lightGray);
		this.widthField.setMaxLength(5);

		this.heightField = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), 50, 100, 50, 25);
		this.heightField.setText(TowerDefense.getHeight() + "");
		this.heightField.setBorderColor(Color.gray);
		this.heightField.setBackgroundColor(Color.lightGray);
		this.heightField.setMaxLength(5);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
		this.widthField.render(container, graphics);
		this.heightField.render(container, graphics);
	}

	public void deactivate() {
		// this.widthField.deactivate();
		// this.heightField.deactivate();
	}

	public void activate(GameContainer container) {
		// this.widthField = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), 0, 100, 50, 25);
		this.widthField.setText(TowerDefense.getWidth() + "");
		// this.widthField.setBorderColor(Color.gray);
		// this.widthField.setBackgroundColor(Color.lightGray);
		// this.widthField.setMaxLength(5);
		//
		// this.heightField = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), 50, 100, 50, 25);
		this.heightField.setText(TowerDefense.getHeight() + "");
		// this.heightField.setBorderColor(Color.gray);
		// this.heightField.setBackgroundColor(Color.lightGray);
		// this.heightField.setMaxLength(5);
	}

}
