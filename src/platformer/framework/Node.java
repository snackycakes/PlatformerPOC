package platformer.framework;

public abstract class Node {
	protected Position position;
	
	protected void init() {
		position = new Position();
	}
	
	public Node() {
		init();
	}
	
	public Node(Position position) {
		init();
		this.position = position;
	}
	
	public Node(int xPos, int yPos) {
		init();
		this.setPosition(xPos, yPos);
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
}
