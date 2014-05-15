package towerDefense;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import engine.GameComponent;
import engine.Level;
import engine.LevelHandler;
import engine.graphics.Sprite;
import engine.gui.Button;
import engine.gui.Clickable;

public class ChooseLevel extends GameComponent {

	private Button button, left, right;
	private int page, lastPage;

	private Level currentLevel;

	private LevelHandler levelHandler = new LevelHandler();

	public ChooseLevel(TowerDefense game) {
		super(game);
		this.page = 0;
		this.levelHandler.add("level1.txt", game.getGameplay());
		this.levelHandler.add("level4.txt", game.getGameplay());
		this.levelHandler.add("level2.txt", game.getGameplay());
		this.levelHandler.add("level3.txt", game.getGameplay());

		this.currentLevel = this.levelHandler.get(this.page);
		Sprite currentPreviewPicture = this.currentLevel.getPreviewPicture();
		Sprite leftSprite = new Sprite("left.png");
		Sprite rightSprite = new Sprite("right.png");

		float leftX = TowerDefense.getWidth() / 4 - leftSprite.getWidth() / 2;
		float leftY = TowerDefense.getHeight() / 2 - leftSprite.getHeight() / 2;
		float rightX = TowerDefense.getWidth() - leftX;
		float rightY = leftY;
		float buttonX = TowerDefense.getWidth() / 2 - currentPreviewPicture.getWidth() / 2;
		float buttonY = TowerDefense.getHeight() / 2 - currentPreviewPicture.getHeight() / 2;

		this.button = new Button(buttonX, buttonY, currentPreviewPicture, currentPreviewPicture, game.getGameplay());
		this.left = new Button(leftX, leftY, leftSprite, leftSprite, game.getGameplay());
		this.right = new Button(rightX, rightY, rightSprite, rightSprite, game.getGameplay());

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
	public void update(GameContainer container, int delta) {
		this.mouseEvents(container, delta);
		this.button.setUnclickedButton(this.currentLevel.getPreviewPicture());
	}

	private void mouseEvents(GameContainer container, int delta) {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

			float x = input.getMouseX();
			float y = input.getMouseY();

			for (Clickable clickable : this.clickables) {
				if (clickable.collides((int) x, (int) y, 1f)) {
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

						this.game.setMode(TowerDefense.MODE_GAME);
					}

				}
			}

		}
	}

}
