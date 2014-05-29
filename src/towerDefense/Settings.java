package towerDefense;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

import engine.GameComponent;
import engine.gui.Clickable;
import engine.gui.ClickableText;
import engine.gui.GoToMenuButton;
import engine.gui.StaticText;

public class Settings extends GameComponent {

	private TextField widthField;
	private TextField heightField;
	private ClickableText apply;
	private StaticText warning;
	private GoToMenuButton back;
	private ClickableText fullscreen;
	private StaticText supportedResolutions;

	public Settings(TowerDefense game, GameContainer container) {
		super(game);

		this.back = new GoToMenuButton(0, 0, "Back", this.game);
		this.clickables.add(this.back);
		this.guiElements.add(this.back);

		this.back.setX(0);
		this.back.setY(TowerDefense.getHeight() - this.back.getHeight() * 2);

		int fieldsX = 0;
		int fieldsY = 100;
		int fieldWidth = 50;
		this.widthField = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), fieldsX, fieldsY,
				fieldWidth, 25);
		this.widthField.setText(TowerDefense.getWidth() + "");
		this.widthField.setBorderColor(Color.gray);
		this.widthField.setBackgroundColor(Color.lightGray);
		this.widthField.setMaxLength(4);
		this.widthField.setCursorPos(this.widthField.getWidth());
		fieldsX += fieldWidth;

		this.heightField = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), fieldsX, fieldsY,
				fieldWidth, 25);
		this.heightField.setText(TowerDefense.getHeight() + "");
		this.heightField.setBorderColor(Color.gray);
		this.heightField.setBackgroundColor(Color.lightGray);
		this.heightField.setMaxLength(4);
		this.heightField.setCursorPos(this.heightField.getWidth());
		fieldsX += fieldWidth + 5;

		this.apply = new ClickableText(fieldsX, fieldsY, "Apply", Gameplay.GLOBAL_GUI_SCALE, game.getGameplay(), false);
		this.clickables.add(this.apply);
		this.guiElements.add(this.apply);

		fieldsX += this.apply.getWidth() + 10;

		this.warning = new StaticText(fieldsX, fieldsY, Color.red, "Please enter a number.");
		this.warning.setVisible(false);
		fieldsX = 0;
		fieldsY += this.widthField.getHeight();
		this.fullscreen = new ClickableText(fieldsX, fieldsY, "Toggle fullscreen", Gameplay.GLOBAL_GUI_SCALE, game.getGameplay(),
				false);
		this.clickables.add(this.apply);
		this.guiElements.add(this.apply);

		String supportedDisplayModes = new String();
		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			ArrayList<String> modesList = new ArrayList<String>();
			String currentMode = new String();
			for (DisplayMode displayMode : modes) {
				currentMode = displayMode.getWidth() + " x " + displayMode.getHeight();
				if (!modesList.contains(currentMode)) {
					modesList.add(currentMode);
				}
			}
			System.out.println(modesList);
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
		this.widthField.render(container, graphics);
		this.heightField.render(container, graphics);
		this.warning.draw();
		this.fullscreen.draw();
	}

	@Override
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		float x = input.getMouseX();
		float y = input.getMouseY();
		super.updateHovering(x, y);
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			if (this.apply.collides((int) x, (int) y, 1f)) {
				String newWidthString = this.widthField.getText();
				String newHeightString = this.heightField.getText();
				try {
					int newWidth = Integer.parseInt(newWidthString);
					int newHeight = Integer.parseInt(newHeightString);
					this.warning.setVisible(false);
					if (newWidth >= 800 && newHeight >= 600) {

						try {
							AppGameContainer gameContainer = (AppGameContainer) container;
							gameContainer.setDisplayMode(newWidth, newHeight, TowerDefense.isFULLSCREEN());
							TowerDefense.updateDimensions(container);
							this.back.setX(0);
							this.back.setY(TowerDefense.getHeight() - this.back.getHeight() * 2);
							this.game.reinitMenu(container);
							this.game.reinitChooseLevel(container);
						} catch (SlickException e) {
							this.warning.setText("Not a supported fullscreen resolution.");
							this.warning.setVisible(true);
						}

					} else {
						this.warning.setText("Minimum is 800 x 600");
						this.warning.setVisible(true);
					}

				} catch (NumberFormatException nfe) {
					this.warning.setText("Please enter a number.");
					this.warning.setVisible(true);
				}

			} else if (this.fullscreen.collides((int) x, (int) y, 1f)) {
				if (TowerDefense.isFULLSCREEN()) {
					try {
						container.setFullscreen(false);
						TowerDefense.setFULLSCREEN(false);
					} catch (SlickException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					try {
						container.setFullscreen(true);
						TowerDefense.setFULLSCREEN(true);
						this.warning.setVisible(false);
					} catch (SlickException e) {
						this.warning.setText("Not a supported fullscreen resolution.");
						this.warning.setVisible(true);
					}
				}
			}
			this.mouseWasClicked = true;
			for (Clickable clickable : this.clickables) {
				clickable.update(x, y, container);
			}

		} else {
			if (this.mouseWasClicked) {
				this.mouseWasClicked = false;
				for (Clickable clickable : this.clickables) {
					if (!clickable.isStayClicked()) {
						if (clickable.isClicked()) {
							clickable.onRelease();
						}
					}
				}
			}
		}

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
