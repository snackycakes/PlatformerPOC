package platformer.framework;

public class Velocity {
	private float speedX;
	private float speedY;

	public Velocity() {
		setVelocity(0f,0f);
	}
	
	public Velocity(float speedX, float speedY) {
		setVelocity(speedX, speedY);
	}
	
	public void setVelocity(float speedX, float speedY) {
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public void addToVelocityX(float speedX) {
		this.speedY += speedX;
	}
	
	public void addToVelocityY(float speedY) {
		this.speedY += speedY;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public void copy(Velocity velocity) {
		this.setVelocity(velocity.speedX, velocity.speedY);
	}
	
	public void addForce(Force force) {
		this.speedX = this.speedX + force.getForceX();
		this.speedY = this.speedY + force.getForceY();
	}
}
