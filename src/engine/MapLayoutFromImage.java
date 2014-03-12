package engine;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

// @formatter:off
/**
 * @author Pavel
 * 
 *         Color:	Black		Red			White			Green
 *         RGB:		(0,0,0)		(1,0,0)		(1,1,1)			(0,1,0)
 *         Short:	Path		Start		T-Area			NoBuild
 *         Rule:	Only for 	starting	Build Towers	Neither Towers
 *         			Enemies		point		here			nor enemies
 *         Number:	0			(0)			1				2
 *         (in path Array)
 *         
 *         the starting point can only be connected to one path
 *
 */
// @formatter:on
public class MapLayoutFromImage {
	private Image image;
	private Color[][] colors;
	private int[][] path; // 0 = Pfad, 1 = TowerYes, 2 = TowerNo
	private Waypoint startingPoint;

	public MapLayoutFromImage(String imagePath) {

		try {
			this.image = new Image("./data/" + imagePath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setColorArray();
		this.setPath();
		this.setWaypoints();

	}

	private void setColorArray() {
		this.colors = new Color[this.image.getHeight()][this.image.getWidth()];
		for (int y = 0; y < colors.length; ++y) {
			for (int x = 0; x < colors[0].length; ++x) {
				colors[y][x] = this.image.getColor(x, y);
			}
		}
	}

	/**
	 * generates the path from the image and gets the coordinates of the starting point for enemies
	 */
	private void setPath() {
		this.path = new int[this.colors.length][this.colors[0].length];
		for (int y = 0; y < colors.length; ++y) {
			for (int x = 0; x < colors[0].length; ++x) {
				Color currentColor = this.colors[y][x];
				System.out.println(currentColor);
				// currentColor has no Blue value
				if (currentColor.getBlue() / 255 <= 0.2f) {
					// currentColor has a green value AND NO blue value => NoBuild
					if (currentColor.getGreen() / 255 >= 0.8f) {
						this.path[y][x] = 2;
					} else {// currentColor has no blue, no green => Start or Path => walkable for enemies
						this.path[y][x] = 0;

						// currentColor has no blue value, no green value, but red value => Start
						if (currentColor.getRed() / 255 >= 0.8f) {
							System.out.println("JO");
							this.startingPoint = new Waypoint(x * 50 + 50 / 2, y * 50 + 50 / 2);
						} else { // currentColor has no blue, no green, no red value => Path
							//
						}
					}

				} else {// currentColor has blue value => T-Area
					this.path[y][x] = 1;
}
			}
		}

	}

	private void setWaypoints() {
		int currentX = (int) this.startingPoint.getX() / 50;
		int currentY = (int) this.startingPoint.getY() / 50;
		int[][] path = this.path.clone();

		int relativePositionOfNextPath = this.relativePositionOfNextCoordinate(path, currentX, currentY);
		this.startingPoint.setDirection(relativePositionOfNextPath);

		int relativePositionOfPreviousPath;
		relativePositionOfPreviousPath = relativePositionOfNextPath;

		while (currentX >= 0 && currentY >= 0 && currentX < this.path.length && currentY < this.path.length
				&& this.path[currentY][currentX] < 1) {
			path[currentY][currentX] = 5;
			relativePositionOfNextPath = this.relativePositionOfNextCoordinate(path, currentX, currentY);
			if (relativePositionOfNextPath != relativePositionOfPreviousPath) {
				this.startingPoint.add(new Waypoint(currentX * 50 + 50 / 2, currentY * 50 + 50 / 2, relativePositionOfNextPath));
			}
			if (relativePositionOfNextPath == Waypoint.RIGHT) {
				++currentX;
			} else if (relativePositionOfNextPath == Waypoint.DOWN) {
				++currentY;
			} else if (relativePositionOfNextPath == Waypoint.LEFT) {
				--currentX;
			} else if (relativePositionOfNextPath == Waypoint.UP) {
				--currentY;
			} else {// == 6
				currentX = -1; // break
			}

			relativePositionOfPreviousPath = relativePositionOfNextPath;

		}

	}

	private int relativePositionOfNextCoordinate(int[][] path, int currentX, int currentY) {
		if (currentX + 1 < path[0].length && path[currentY][currentX + 1] < 1) {
			return Waypoint.RIGHT;
		} else if (currentY + 1 < path.length && path[currentY + 1][currentX] < 1) {
			return Waypoint.DOWN;
		} else if (currentX - 1 >= 0 && path[currentY][currentX - 1] < 1) {
			return Waypoint.LEFT;
		} else if (currentY - 1 >= 0 && path[currentY - 1][currentX] < 1) {
			return Waypoint.UP;
		} else {
			return 5;
		}
	}

	public int[][] getPath() {
		return path;
	}

	public Waypoint getStartingPoint() {
		return startingPoint;
	}

	public Color getColors(int x, int y) {
		return this.colors[y][x];
	}

}
