package engine.graphics;


public abstract class RotatableRenderObject extends RenderObject {

	@Override
	public abstract void draw(float xCoordinate, float yCoordinate) ;
	
	public abstract void draw(float xCoordinate, float yCoordinate, float rotation, float scale);
	
	

}
