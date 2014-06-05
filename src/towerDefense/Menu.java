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
import engine.gui.GoToGameButton;
import engine.gui.GoToScoreButton;
import engine.gui.GoToSettingsButton;
import engine.gui.StartClickable;
import engine.gui.StaticText;

public class Menu extends GameComponent {
	private TextField t;
	private StaticText version = new StaticText(0, 0, 10, Color.white, "v0.5");
	private StaticText lostWonMessage;
	private StartClickable startButton;
	private GoToGameButton resumeButton;

	public Menu(TowerDefense game) {
		super(game);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		super.init(container);
		this.lostWonMessage = new StaticText(TowerDefense.getWidth() / 4, 0, 20, Color.red, "");
		this.guiElements.add(this.lostWonMessage);

		this.startButton = new StartClickable(0, 0, this.game, container);
		this.clickables.add(this.startButton);
		this.guiElements.add(this.startButton);
		int y = TowerDefense.getHeight() / 2 - this.startButton.getTextHeight();
		this.startButton.setX(TowerDefense.getWidth() / 2 - this.startButton.getWidth() / 2);
		this.startButton.setY(y);
		y += this.startButton.getTextHeight() + 1;

		GoToSettingsButton settings = new GoToSettingsButton(0, 0, "Settings", this.game);
		this.clickables.add(settings);
		this.guiElements.add(settings);
		settings.setX(TowerDefense.getWidth() / 2 - settings.getWidth() / 2);
		settings.setY(y);
		y += this.startButton.getTextHeight() + 1;

		GoToScoreButton scores = new GoToScoreButton(0, 0, "Highscores", this.game);
		this.clickables.add(scores);
		this.guiElements.add(scores);
		scores.setX(TowerDefense.getWidth() / 2 - scores.getWidth() / 2);
		scores.setY(y);
		y += this.startButton.getTextHeight() + 1;

		ExitClickable e = new ExitClickable(100, 121, this.game);
		this.clickables.add(e);
		this.guiElements.add(e);
		e.setX(TowerDefense.getWidth() / 2 - e.getWidth() / 2);
		e.setY(y);
		y += this.startButton.getTextHeight() + 1;

		this.t = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), 0, 0, 75, 25);
		this.t.setText("Player");
		this.t.setBorderColor(Color.gray);
		this.t.setBackgroundColor(Color.lightGray);
		this.t.setMaxLength(32);
		this.t.setLocation(TowerDefense.getWidth() / 2 - this.t.getWidth() / 2, y);
		this.t.setCursorPos(6);

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			// this.t.deactivate();
		}

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		super.render(container, graphics);
		this.t.render(container, graphics);
		this.version.draw();
	}

	public String getPlayerName() {
		return this.t.getText();
	}

	public void setLost(int score, String name) {
		this.lostWonMessage.setText("You lost, " + name + "!\nYour Score was: " + score + " Points.");
		this.lostWonMessage.setPosition((TowerDefense.getWidth() - this.lostWonMessage.getWidth()) / 2, 0);
		this.lostWonMessage.setColor(Color.red);
	}

	// public void deactivate() {
	// this.t.deactivate();
	// }
	//
	// public void activate(GameContainer container) {
	// this.t = new TextField(container, new TrueTypeFont(new Font("Verdana", Font.PLAIN, 15), true), 0, 100, 75, 25);
	// this.t.setText("Player 1");
	// this.t.setBorderColor(Color.gray);
	// this.t.setBackgroundColor(Color.lightGray);
	// this.t.setMaxLength(32);
	// }

	public void setWon(int score, String name) {
		this.lostWonMessage.setText("You beat the level, " + name + "!\nYour Score was: " + score + " Points.");
		this.lostWonMessage.setPosition((TowerDefense.getWidth() - this.lostWonMessage.getWidth()) / 2, 0);
		this.lostWonMessage.setColor(Color.green);

	}

}
