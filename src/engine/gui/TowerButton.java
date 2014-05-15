package engine.gui;

import org.newdawn.slick.GameContainer;

import towerDefense.Gameplay;
import towerDefense.towers.Tower;
import engine.graphics.Sprite;

public class TowerButton extends Button {
	private Tower tower;

	public TowerButton(float x, float y, String unclickedButtonPath, String clickedButtonPath, Tower tower, Gameplay game) {
		super(x, y, unclickedButtonPath, clickedButtonPath, game);
		this.tower = tower;
	}

	@Override
	public void update(GameContainer container) {
		super.update(container);
		if (this.clicked) {
				int newX = (int) this.x / Gameplay.SIZE;
				int newY = (int) this.y / Gameplay.SIZE;

				if (this.currentTower != null) {
					int[][] path = this.currentLevel.getPath();
					int cost = this.currentTower.getCost();
					if (this.game.currentTowerPlaceable && this.x < Gameplay.INTERFACE_START_X
							&& this.y < path.length * this.currentTileLength && path[newY][newX] == 1
							&& this.towers[newY][newX] == null && this.player.getMoney() - cost >= 0) {
						Tower bufferTower = this.currentTower.clone();
						bufferTower.setX(newX);
						bufferTower.setY(newY);
						bufferTower.getSprite().setAlpha(1f);
						this.towers[newY][newX] = bufferTower;
						this.player.reduceMoney(cost);
						this.currentTower = null;
						this.releaseAllClickables();
						this.game.getSoundHandler().play("place");

					} else {
						this.game.getSoundHandler().play("bad");
					}

				}
			}
			this.mouseWasClicked = true;

		} else if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			this.currentTower = null;
			this.releaseAllClickables();

		}
	}

	@Override
	public void draw() {
		super.draw();
		Sprite s = this.tower.getSprite();
		s.setAlpha(0.8f);
		float scale = 0.9f;
		s.draw(this.x + (this.collisionWidth - s.getWidth() * scale) / 2 * Gameplay.GLOBAL_GUI_SCALE, this.y
				+ (this.collisionHeight - s.getHeight() * scale) / 2 * Gameplay.GLOBAL_GUI_SCALE, scale * Gameplay.GLOBAL_GUI_SCALE);
	}

	@Override
	public void onClick() {
		super.onClick();
		this.game.setCurrentTower(this.tower);
		this.game.getCurrentTower().getSprite().setAlpha(0.5f);

	}

	public Tower getTower() {
		return this.tower;
	}

}
