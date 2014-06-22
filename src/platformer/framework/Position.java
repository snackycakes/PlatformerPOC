package platformer.framework;

public class Position {
	private int xPos;
	private int yPos;
	private float xCoord;
	private float yCoord;

	public Position() {
		setPosition(0, 0);
	}
	
	public Position(int xPos, int yPos) {
		setPosition(xPos, yPos);
	}
	
	public Position(float xCoord, float yCoord) {
		setPosition(xCoord, yCoord);
	}
	
	public void setPosition(int xPos, int yPos) {
		setxPos(xPos);
		setyPos(yPos);
	}
	
	public void setPosition(float xCoord, float yCoord) {
		setxCoord(xCoord);
		setyCoord(yCoord);
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
		this.xCoord = (float)xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
		this.yCoord = (float)yPos;
	}

	public float getxCoord() {
		return xCoord;
	}

	public void setxCoord(float xCoord) {
		this.xCoord = xCoord;
		this.xPos = (int)xCoord;
	}

	public float getyCoord() {
		return yCoord;
	}

	public void setyCoord(float yCoord) {
		this.yCoord = yCoord;
		this.yPos = (int)yCoord;
	}
	
	public void copy(Position position) {
		this.setxCoord(position.xCoord);
		this.setyCoord(position.yCoord);
	}
	
	public void addVelocity(Velocity velocity) {
		this.setxCoord(this.xCoord + velocity.getSpeedX());
		this.setyCoord(this.yCoord + velocity.getSpeedY());
	}
	
	public void addToPositionY(float speedY) {
		this.setyCoord(this.yCoord + speedY);
	}
	
	public void addToPositionX(float speedX) {
		this.setxCoord(this.xCoord + speedX);
	}
}
