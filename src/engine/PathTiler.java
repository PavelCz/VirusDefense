package engine;

import engine.graphics.Sprite;

public class PathTiler {
	private Sprite corner1, corner2, corner3, corner4, horizontal, vertical, top, bottom, left, right;
	private Sprite[][] tiles;

	public PathTiler(int[][] path) {
		this.corner1 = new Sprite("veins/VeinCorner.png");
		this.corner2 = new Sprite("veins/VeinCorner2.png");
		this.corner3 = new Sprite("veins/VeinCorner3.png");
		this.corner4 = new Sprite("veins/VeinCorner4.png");
		this.horizontal = new Sprite("veins/VeinHoriz.png");
		this.vertical = new Sprite("veins/VeinVert.png");
		this.top = new Sprite("veins/VeinTopEnd.png");
		this.bottom = new Sprite("veins/VeinBottomEnd.png");
		this.left = new Sprite("veins/VeinLeftEnd.png");
		this.right = new Sprite("veins/VeinRightEnd.png");
		this.tiles = new Sprite[path.length][path[0].length];

		for (int y = 0; y < path.length; ++y) {
			for (int x = 0; x < path[0].length; ++x) {
				if (path[y][x] == 5) {
					boolean above = false, below = false, left = false, right = false;
					if (y - 1 >= 0 && path[y - 1][x] == 5) {
						above = true;
					}
					if (y + 1 < path.length && path[y + 1][x] == 5) {
						below = true;
					}
					if (x - 1 >= 0 && path[y][x - 1] == 5) {
						left = true;
					}
					if (x + 1 < path[0].length && path[y][x + 1] == 5) {
						right = true;
					}

					if (below && !above && !right && !left) {
						this.tiles[y][x] = this.top;
					} else if (!below && above && !right && !left) {
						this.tiles[y][x] = this.bottom;
					} else if (!below && !above && right && !left) {
						this.tiles[y][x] = this.left;
					} else if (!below && !above && !right && left) {
						this.tiles[y][x] = this.right;
					} else if (below && above && !right && !left) {
						this.tiles[y][x] = this.vertical;
					} else if (!below && !above && right && left) {
						this.tiles[y][x] = this.horizontal;
					} else if (!below && above && right && !left) {
						this.tiles[y][x] = this.corner1;
					} else if (below && !above && right && !left) {
						this.tiles[y][x] = this.corner2;
					} else if (below && !above && !right && left) {
						this.tiles[y][x] = this.corner3;
					} else if (!below && above && !right && left) {
						this.tiles[y][x] = this.corner4;
					}

				}
			}
		}

	}

	public void render() {
		for (int y = 0; y < this.tiles.length; ++y) {
			for (int x = 0; x < this.tiles[0].length; ++x) {
				if (this.tiles[y][x] != null) {
					this.tiles[y][x].draw(x * 50, y * 50);
				}
			}
		}
	}
}
