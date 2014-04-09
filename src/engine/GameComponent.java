package engine;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import engine.gui.Clickable;
import engine.gui.GUI;

public abstract class GameComponent {

	protected List<GUI> guiElements;
	protected List<Clickable> clickables;

	protected TowerDefense game;
	private boolean mouseWasClicked;

	public GameComponent(TowerDefense game) {
		this.game = game;
	}

	private void renderGUI() {
		for (GUI guiElement : this.guiElements) {
			guiElement.draw();
		}
	}

	public void init(GameContainer container) throws SlickException {
		this.guiElements = new ArrayList<GUI>();
		this.clickables = new ArrayList<Clickable>();
	}

	public void update(GameContainer container, int delta) throws SlickException {
		this.updateClickables(container, delta);
	}

	public void render(GameContainer container, Graphics graphics) throws SlickException {
		this.renderGUI();
	}

	private void updateClickables(GameContainer container, int delta) {
		for (Clickable clickable : this.clickables) {
			clickable.update(container);
		}
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			float x = input.getMouseX();
			float y = input.getMouseY();

			boolean buttonWasPressed = false;
			for (Clickable clickable : this.clickables) {
				if (clickable.collides((int) x, (int) y)) {
					buttonWasPressed = true;
					this.releaseAllClickables();
					clickable.onClick();

				}
			}

			this.mouseWasClicked = true;

		}
		// checks if mouse button was released again after being pressed
		if (this.mouseWasClicked && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

			this.mouseWasClicked = false;
			this.releaseAllClickables();

		}
	}

	private void releaseAllClickables() {
		for (Clickable clickable : this.clickables) {
			clickable.onRelease();
		}
	}

}
