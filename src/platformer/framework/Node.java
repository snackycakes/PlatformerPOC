package platformer.framework;

public abstract class Node {
	protected Position position;
	protected SpriteContainer activeSpriteContainer;
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
		position = new Position();
	}
	
	public Node(Position position) {
		this.position = position;
	}
	
	public Node(int xPos, int yPos) {
		this.position = new Position(xPos, yPos);
	}
	
	public void update(long elapsedTime) {}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setPosition(float xCoord, float yCoord) {
		this.position.setPosition(xCoord, yCoord);
	}
	
	public int getPositionX() {
		return this.position.getxPos();
	}
	
	public int getPositionY() {
		return this.position.getyPos();
	}
	
	public float getCoordX() {
		return this.position.getxCoord();
	}
	
	public float getCoordY() {
		return this.position.getyCoord();
	}
	
	public boolean isStopsMovement() {
		return stopsMovement;
	}
	
	public void setStopsMovement(boolean stopsMovement) {
		this.stopsMovement = stopsMovement;
	}
}
