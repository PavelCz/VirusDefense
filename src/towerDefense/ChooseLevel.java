package towerDefense;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import engine.GameComponent;
import engine.Level;
import engine.LevelHandler;
import engine.graphics.Sprite;
import engine.gui.Button;
import engine.gui.Clickable;
import engine.gui.GoToMenuButton;

public class ChooseLevel extends GameComponent {

	private Button button, left, right;
	private int page, lastPage;

	private Level currentLevel;

	private LevelHandler levelHandler = new LevelHandler();

	public ChooseLevel(TowerDefense game, GameContainer container) {
		super(game);
		this.page = 0;
		this.levelHandler.add("level1.txt", game.getGameplay());
		this.levelHandler.add("level4.txt", game.getGameplay());
		this.levelHandler.add("level2.txt", game.getGameplay());
		this.levelHandler.add("level3.txt", game.getGameplay());

		this.currentLevel = this.levelHandler.get(this.page);
		Sprite currentPreviewPicture = this.currentLevel.getPreviewPicture();
		Sprite leftSprite = new Sprite("left.png", 2.5f);
		Sprite rightSprite = new Sprite("right.png", 2.5f);

		float leftX = TowerDefense.getWidth() / 4 - leftSprite.getWidth() / 2;
		float leftY = TowerDefense.getHeight() / 2 - leftSprite.getHeight() / 2;
		float rightX = TowerDefense.getWidth() - leftX;
		float rightY = leftY;
		float buttonX = TowerDefense.getWidth() / 2 - currentPreviewPicture.getWidth() / 2;
		float buttonY = TowerDefense.getHeight() / 2 - currentPreviewPicture.getHeight() / 2;

		this.button = new Button(buttonX, buttonY, currentPreviewPicture, currentPreviewPicture, game.getGameplay(), false);
		this.left = new Button(leftX, leftY, leftSprite, new Sprite("leftClicked.png", 2.5f), game.getGameplay(), false);
		this.right = new Button(rightX, rightY, rightSprite, new Sprite("rightClicked.png", 2.5f), game.getGameplay(), false);

		GoToMenuButton back = new GoToMenuButton(0, 0, "Back", this.game);
		back.setX(0);
		back.setY(TowerDefense.getHeight() - back.getTextHeight() * 2);
		this.clickables.add(back);
		this.guiElements.add(back);

		this.clickables.add(this.button);
		this.clickables.add(this.left);
		this.clickables.add(this.right);
		this.guiElements.add(this.button);
		this.guiElements.add(this.left);
		this.guiElements.add(this.right);
		this.lastPage = this.levelHandler.getLength() - 1;

		this.lastPage = this.levelHandler.getLength() - 1;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {

		this.mouseEvents(container, delta);
		this.button.setUnclickedButton(this.currentLevel.getPreviewPicture());
		this.button.setClickedButton(this.currentLevel.getPreviewPicture());
	}

	private void mouseEvents(GameContainer container, int delta) {
		Input input = container.getInput();
		float x = input.getMouseX();
		float y = input.getMouseY();
		super.updateHovering(x, y);
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			this.mouseWasClicked = true;

			for (Clickable clickable : this.clickables) {
				clickable.update(x, y, container);
			}

		} else if (this.mouseWasClicked && !input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			this.mouseWasClicked = false;
			for (Clickable clickable : this.clickables) {
				if (!clickable.isStayClicked()) {
					if (clickable.isClicked() && clickable.collides((int) x, (int) y, Gameplay.GLOBAL_GUI_SCALE)) {
						clickable.onRelease();
						if (clickable == this.left) {
							--this.page;
							if (this.page < 0) {
								this.page = this.lastPage;
							}
							this.currentLevel = this.levelHandler.get(this.page);
							this.currentLevel = this.levelHandler.get(this.page);
						} else if (clickable == this.right) {
							this.page += 1;
							if (this.page > this.lastPage) {
								this.page = 0;
							}
							this.currentLevel = this.levelHandler.get(this.page);
						} else if (clickable == this.button) {
							this.game.setLevel(this.currentLevel);
							this.game.initGameplay(container);
							this.game.getGameplay().setPlayerName(this.game.getPlayerName());
							this.game.setMode(TowerDefense.MODE_GAME);
						}
					} else if (clickable.isClicked()) {
						clickable.setClicked(false);
					}
				}
			}
		}
	}

}
