package engine;

public class MapLayout {
	private int[][] path;
	private Waypoint waypoints;
	
	private Background mapBackground;
	private int tileLength;
	private int numberTilesWidth, numberTilesHeight;
	
	public MapLayout(String mapLayoutPath, String backgroundPath, int tileLength) {
		MapLayoutFromImage mapLayout= new MapLayoutFromImage(mapLayoutPath);
		this.path = mapLayout.getPath();
		this.waypoints = mapLayout.getStartingPoint();
		this.mapBackground = new Background(1f, backgroundPath);
		this.numberTilesWidth = this.path[0].length;
		this.numberTilesHeight = this.path.length;
		this.tileLength = tileLength;
	}
	
	public void drawBackground() {
		this.mapBackground.draw();
	}

	public int[][] getPath() {
		return path;
	}

	public Waypoint getWaypoints() {
		return waypoints;
	}

	public Background getMapBackground() {
		return mapBackground;
	}

	public int getTileLength() {
		return tileLength;
	}

	public int getNumberTilesWidth() {
		return numberTilesWidth;
	}

	public int getNumberTilesHeight() {
		return numberTilesHeight;
	}
	
	
}
