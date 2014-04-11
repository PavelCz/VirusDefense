package engine;

import engine.graphics.Sprite;

public class PathTiler {
	private Sprite corner1, corner2, corner3, corner4, horizontal, vertical, top, bottom, left, right;
	private Sprite[][] path;

	public PathTiler(int[][] path1) {
		this.corner1 = new Sprite("VeinCorner.png");
		this.corner2 = new Sprite("VeinCorner2.png");
		this.corner3 = new Sprite("VeinCorner3.png");
		this.corner4 = new Sprite("VeinCorner4.png");
		this.horizontal = new Sprite("VeinHoriz.png");
		this.vertical = new Sprite("VeinVert.png");
		this.top = new Sprite("VeinTopEnd.png");
		this.bottom = new Sprite("VeinBottomEnd.png");
		this.left = new Sprite("VeinLeftEnd.png");
		this.right = new Sprite("VeinRightEnd.png");
		this.path = new Sprite[path1.length][path1[0].length];

		for (int y = 0; y < path1.length; ++y) {
			for (int x = 0; x < path1[0].length; ++x) {
				if (path1[y][x] == 5) {
					boolean above = false, below = false, left = false, right = false;
					if (path1[y - 1][x] == 5) {
						above = true;
					}
					if (path1[y + 1][x] == 5) {
						below = true;
					}
					if (path1[y][x - 1] == 5) {
						left = true;
					}
					if (path1[y][x + 1] == 5) {
						right = true;
					}

					if (below && !above && !right && !left) {
						this.path[y][x] = this.top;
					} else if (!below && above && !right && !left) {
						this.path[y][x] = this.bottom;
					} else if (!below && !above && right && !left) {
						this.path[y][x] = this.left;
					} else if (!below && !above && !right && left) {
						this.path[y][x] = this.right;
					} else if (below && above && !right && !left) {
						this.path[y][x] = this.vertical;
					} else if (!below && !above && right && left) {
						this.path[y][x] = this.horizontal;
					} else if (!below && above && right && !left) {
						this.path[y][x] = this.corner1;
					} else if (below && !above && right && !left) {
						this.path[y][x] = this.corner2;
					} else if (below && !above && !right && left) {
						this.path[y][x] = this.corner3;
					} else if (!below && above && !right && left) {
						this.path[y][x] = this.corner4;
					}

				}
			}
		}

	}

	public void render() {
		for (int y = 0; y < this.path.length; ++y) {
			for (int x = 0; x < this.path[0].length; ++x) {
				if (this.path[y][x] != null) {
					this.path[y][x].draw(x * 50, y * 50);
				}
			}
		}
	}
}
