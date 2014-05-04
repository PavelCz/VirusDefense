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

	protected SoundHandler soundHandler = new SoundHandler();;
	protected List<GUI> guiElements;
	protected List<Clickable> clickables;

	protected TowerDefense game;
	private Clickable wasClicked;

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
	
	private void initSounds() {
		this.soundHandler.addWav("press");
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
					clickable.onClick();
					this.wasClicked = clickable;
				}
			}

			

		}
		// checks if mouse button was released again after being pressed
		if (this.wasClicked != null && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

			this.wasClicked.onRelease();
			this.wasClicked = null;

		}
	}

	private void releaseAllClickables() {
		for (Clickable clickable : this.clickables) {
			clickable.onRelease();
		}
	}

}
