package platformer.framework;

public abstract class Node {
	Position position = new Position(0,0);
	
	public Node() {
		this.position = new Position(0,0);
	}
	
	public Node(Position position) {
		this.position = position;
	}
	
	public Node(int xPos, int yPos) {
		this.position.xPos = xPos;
		this.position.yPos = yPos;
	}
	
	public void update(long elapsedTime) {}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setPosition(int xPos, int yPos) {
		this.position.xPos = xPos;
		this.position.yPos = yPos;
	}
	
	public int getPositionX() {
		return this.position.xPos;
	}
	
	public int getPositionY() {
		return this.position.yPos;
	}
}
