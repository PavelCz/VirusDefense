package engine;

import engine.graphics.Sprite;

public class PathTiler {
	private Sprite corner1, corner2, corner3, corner4, horizontal, vertical, start, end;
	private Sprite[][] path;

	public PathTiler(int width, int height, Waypoint waypoint) {
		this.corner1 = new Sprite("VeinCorner.png");
		this.corner2 = new Sprite("VeinCorner2.png");
		this.corner3 = new Sprite("VeinCorner3.png");
		this.corner4 = new Sprite("VeinCorner4.png");
		this.horizontal = new Sprite("VeinHoriz.png");
		this.vertical = new Sprite("VeinVert.png");
		this.start = new Sprite("VeinVertStart.png");
		this.end = new Sprite("VeinVertEnd.png");
	}
}
