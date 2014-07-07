package platformer.framework;

import java.awt.Rectangle;

public class HitBox {
	protected OrderedPair position = new OrderedPair();
	protected Rectangle boundingBox;
	protected OrderedPair boxOffset = new OrderedPair();
	protected int sizeX, sizeY;
	
	private void init() {		
		this.boundingBox = new Rectangle(this.position.getPosX(), this.position.getPosY(), this.sizeX, this.sizeY);
	}
	
	public OrderedPair getBoxOffset() {
		return boxOffset;
	}
	
	public HitBox(OrderedPair position, int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.position = position;
		init();
	}

	public HitBox(OrderedPair parentPosition, OrderedPair boxOffset, int sizeX, int sizeY) {
		this.boxOffset = boxOffset;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.updatePosition(parentPosition);
		init();
	}
	
	public void updatePosition(OrderedPair parentPosition)
	{
		this.position.setPosition(parentPosition.getValueX() + this.boxOffset.getValueX(), parentPosition.getValueY() + this.boxOffset.getValueY());
	}

	public boolean checkCollision(HitBox hitBox) {
		boolean returnValue = false;		
		returnValue = this.boundingBox.intersects(((HitBox) hitBox).boundingBox);
		return returnValue;
	}

	public void update(OrderedPair parentPosition) {
		this.updatePosition(parentPosition);
		this.boundingBox.setLocation(this.position.getPosX(), this.position.getPosY());
	}
	
	public void align(OrderedPair parentPosition) {
		this.updatePosition(parentPosition);
		this.boundingBox.setLocation(this.position.getPosX(), this.position.getPosY());
	}
	
	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
	public OrderedPair getPosition() {
		return position;
	}

	public int getPosX () {
		return position.getPosX();
	}
	
	public int getPosY () {
		return position.getPosY();
	}
}
