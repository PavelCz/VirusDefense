package engine;

public class Camera extends Entity {

	public Camera(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void addX(float amount) {
		this.x += amount;
	}

	public void addY(float amount) {
		this.y += amount;
	}
	
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	

}
