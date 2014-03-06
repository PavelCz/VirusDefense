package engine;

public class Waypoint extends Entity {
	final static int RIGHT = 0;
	final static int UP = 1;
	final static int LEFT = 2;
	final static int DOWN = 3;
	private int nextDirection;
	private Waypoint nextWaypoint;

	public Waypoint(float x, float y, int nextDirection) {
		super(x, y);
		this.nextDirection = nextDirection;
		this.nextWaypoint = null;
	}

	public int getDirection() {
		return this.nextDirection;
	}

	public Waypoint getNextWaypoint() {
		return nextWaypoint;
	}

	public void add(Waypoint nextWaypoint) {
		if (this.nextWaypoint == null) {
			this.nextWaypoint = nextWaypoint;
		} else {
			this.nextWaypoint.add(nextWaypoint);
		}
	}

}
