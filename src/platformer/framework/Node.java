package platformer.framework;

public abstract class Node {
	protected OrderedPair position;
	protected SpriteContainer activeSpriteContainer;
	protected SpriteContainer prevSpriteContainer = new SpriteContainer();
	protected boolean stopsMovement = true;
	
	public Sprite getActiveSprite() {
		return activeSpriteContainer.getSprite();
	}
	
	public SpriteContainer getActiveSpriteContainer() {
		return activeSpriteContainer;
	}

	public void setActiveSpriteContainer(SpriteContainer activeSpriteContainer) {
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public void setActiveSpriteContainer(Sprite activeSprite) {
		this.activeSpriteContainer = new StaticSprite(activeSprite, position);
	}
	
	public Node() {
		position = new OrderedPair();
	}
	
	public Node(OrderedPair position) {
		this.position = position;
	}
	
	public Node(int xPos, int yPos) {
		this.position = new OrderedPair(xPos, yPos);
	}
	
	public void update(long elapsedTime) {
		this.prevSpriteContainer.copy(this.activeSpriteContainer);
	}

	public OrderedPair getPosition() {
		return position;
	}

	public void setPosition(OrderedPair position) {
		this.position = position;
	}
	
	public void setPosition(float xCoord, float yCoord) {
		this.position.setPosition(xCoord, yCoord);
	}
	
	public int getPositionX() {
		return this.position.getPosX();
	}
	
	public int getPositionY() {
		return this.position.getPosY();
	}
	
	public float getCoordX() {
		return this.position.getValueX();
	}
	
	public float getCoordY() {
		return this.position.getValueY();
	}
	
	public boolean isStopsMovement() {
		return stopsMovement;
	}
	
	public void setStopsMovement(boolean stopsMovement) {
		this.stopsMovement = stopsMovement;
	}

	public SpriteContainer getPrevSpriteContainer() {
		return prevSpriteContainer;
	}
}
