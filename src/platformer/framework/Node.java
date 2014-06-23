package platformer.framework;

public abstract class Node {
	protected Position position;
	
	protected void init() {
	}
	
	public Node() {
		position = new Position();
		init();
	}
	
	public Node(Position position) {
		this.position = position;
		init();
	}
	
	public Node(int xPos, int yPos) {
		this.position = new Position(xPos, yPos);
		init();
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
