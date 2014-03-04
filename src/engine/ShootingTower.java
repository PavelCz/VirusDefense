package engine;

public class ShootingTower extends Tower {
	public ShootingTower(float x, float y) {
		super(x,y, 200, 200, new ShootingWeapon());
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
