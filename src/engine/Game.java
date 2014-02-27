package engine;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import engine.gui.Button;
import engine.gui.GUI;
import engine.gui.StaticText;

public class Game extends BasicGame {
	private List<Drawable> drawables;
	private List<GUI> guiElements;
	private boolean showFPS;
	private boolean mouseWasClicked;
	Button button1, button2;

	public Game() {
		super("TowerDefense");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.drawables = new ArrayList<Drawable>();
		this.guiElements = new ArrayList<GUI>();
		this.mouseWasClicked = false;
		this.showFPS = false;

		button1 = new Button(300, 300, "./data/button1.png", "./data/button2.png");
		button2 = new Button(200, 300, "./data/button1.png", "./data/button2.png");

		// add all objects that need to be drawn to the respectable arrays
		// entities
		this.drawables.add(new TestEntity(10, 10, 180, "./data/A.bmp"));

		// GUI
		this.guiElements.add(this.button1);
		this.guiElements.add(this.button2);
		this.guiElements.add(new StaticText(100, 100, 10, Color.green, "Hello World"));

		container.setShowFPS(this.showFPS);

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {

		for (Drawable entity : this.drawables) {
			entity.draw();
		}
		for (GUI guiElement : this.guiElements) {
			guiElement.draw();
		}

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

		this.mouseEvents(container, delta);

	}

	private void mouseEvents(GameContainer container, int delta) {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			float x = input.getMouseX();
			float y = input.getMouseY();
			if (button1.checkCollision(x, y)) {
				button1.onClick();
			}
			if (button2.checkCollision(x, y)) {
				button2.onClick();
			}
			this.mouseWasClicked = true;

		}
		// checks if mouse button was released again after being pressed
		if (this.mouseWasClicked && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

			this.mouseWasClicked = false;

			button1.onRelease();
			button2.onRelease();
		}
	}

}
