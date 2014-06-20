package platformer.framework;

public abstract class Node {
	Position position = new Position(0,0);
	
	protected void Init() {}
	
	public Node() {
		this.position = new Position(0,0);
		Init();
	}
	
	public Node(Position position) {
		this.position = position;
		Init();
	}
	
	public Node(int xPos, int yPos) {
		this.setPosition(xPos, yPos);
		Init();
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
