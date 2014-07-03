package platformer.framework;

public class OrderedPair {
	private float valueX;
	private float valueY;

	public OrderedPair() {
		setPosition(0, 0);
	}
	
	public OrderedPair(int xPos, int yPos) {
		setPosition(xPos, yPos);
	}
	
	public OrderedPair(float valueX, float valueY) {
		setPosition(valueX, valueY);
	}
	
	public void setPosition(int posX, int posY) {
		setPosX(posX);
		setPosY(posY);
	}
	
	public void setPosition(float valueX, float valueY) {
		setValueX(valueX);
		setValueY(valueY);
	}

	public int getPosX() {
		return (int)valueX;
	}

	public void setPosX(int xPos) {
		this.valueX = (float)xPos;
	}

	public int getPosY() {
		return (int)valueY;
	}

	public void setPosY(int yPos) {
		this.valueY = (float)yPos;
	}

	public float getValueX() {
		return valueX;
	}

	public void setValueX(float valueX) {
		this.valueX = valueX;
	}

	public float getValueY() {
		return valueY;
	}

	public void setValueY(float valueY) {
		this.valueY = valueY;
	}
	
	public void copy(OrderedPair position) {
		this.setValueX(position.valueX);
		this.setValueY(position.valueY);
	}
	
	public void add(OrderedPair orderedPair) {
		this.setValueX(this.valueX + orderedPair.valueX);
		this.setValueY(this.valueY + orderedPair.valueY);
	}
	
	public void addToValueY(float speedY) {
		this.setValueY(this.valueY + speedY);
	}
	
	public void addToValueX(float speedX) {
		this.setValueX(this.valueX + speedX);
	}
}
