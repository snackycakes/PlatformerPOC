package platformer.framework;

import java.awt.Rectangle;

public class HitBox implements HitArea {
	protected Rectangle boundingBox;
	protected Position boxOffset = new Position();
	protected Position position = new Position();
	protected int sizeX, sizeY;
	
	private void init() {		
		this.boundingBox = new Rectangle(this.position.getxPos(), this.position.getyPos(), this.sizeX, this.sizeY);
	}
	
	public HitBox(Position parentPosition, Position boxOffset, int sizeX, int sizeY) {
		this.boxOffset = boxOffset;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.updatePosition(parentPosition);
		init();
	}
	
	public void updatePosition(Position parentPosition)
	{
		this.position.setPosition(parentPosition.getxCoord() + this.boxOffset.getxCoord(), parentPosition.getyCoord() + this.boxOffset.getyCoord());
	}

	@Override
	public boolean checkCollision(HitArea hitArea) {
		boolean returnValue = false;
		
		if (hitArea instanceof HitBox) {
			returnValue = this.boundingBox.intersects(((HitBox) hitArea).boundingBox);
			}
		return returnValue;
	}

	@Override
	public void update(Position parentPosition) {
		this.updatePosition(parentPosition);
		this.boundingBox.setLocation(this.position.getxPos(), this.position.getyPos());
	}
	
	public int getxPos () {
		return position.getxPos();
	}
	
	public int getyPos () {
		return position.getyPos();
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
	

}
