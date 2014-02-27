package engine.graphics;


public abstract class RotatableRenderObject extends RenderObject {

	@Override
	public abstract void draw(float x, float y) ;
	
	public abstract void draw(float x, float y, float rotation, float scale);
	
	

}
