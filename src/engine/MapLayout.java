package engine;

import engine.graphics.Sprite;

public class MapLayout {
	private int[][] path;
	private Waypoint waypoints;
	private Sprite picture;
	private int tileLength;
	private int numberTilesWidth, numberTilesHeight;

	public MapLayout(String mapLayoutPath, String backgroundPath, int tileLength, String picture) {
		MapLayoutFromImage mapLayout = new MapLayoutFromImage(mapLayoutPath);
		this.picture = new Sprite(picture);
		this.path = mapLayout.getPath();
		this.waypoints = mapLayout.getStartingPoint();
		this.numberTilesWidth = this.path[0].length;
		this.numberTilesHeight = this.path.length;
		this.tileLength = tileLength;
	}

	public int[][] getPath() {
		return this.path;
	}

	public Waypoint getWaypoints() {
		return this.waypoints;
	}

	public int getTileLength() {
		return this.tileLength;
	}

	public int getNumberTilesWidth() {
		return this.numberTilesWidth;
	}

	public int getNumberTilesHeight() {
		return this.numberTilesHeight;
	}

	public Sprite getPicture() {
		return picture;
	}
	
	

}
