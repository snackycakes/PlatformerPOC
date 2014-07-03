package platformer.framework;

public class Force {

	OrderedPair initVelocity = new OrderedPair();
	OrderedPair maxVelocity = new OrderedPair();
	OrderedPair acceleration = new OrderedPair();

	public Force() {
		setForce(0, 0, 0, 0, 0 ,0);
	}

	public Force(float maxVelocityX, float maxVelocityY) {
		setForce(maxVelocityX, maxVelocityY, maxVelocityX, maxVelocityY, maxVelocityX, maxVelocityY);
	}
	
	public Force(float initVelocityX, float initVelocityY, float maxVelocityX, float maxVelocityY, float acclX, float acclY) {
		setForce(initVelocityX, initVelocityY, maxVelocityX, maxVelocityY, acclX, acclY);
	}
	
	public void setForce(float initVelocityX, float initVelocityY, float maxVelocityX, float maxVelocityY, float acclX, float acclY) {
		this.initVelocity.setValueX(initVelocityX);
		this.initVelocity.setValueY(initVelocityY);
		this.maxVelocity.setValueX(maxVelocityX);
		this.maxVelocity.setValueY(maxVelocityY);
		this.acceleration.setValueX(acclX);
		this.acceleration.setValueY(acclY);
	}

	public void setMaxVelocity(float maxVelocityX, float maxVelocityY) {
		this.maxVelocity.setValueX(maxVelocityX);
		this.maxVelocity.setValueY(maxVelocityY);		
	}

	public void addToMaxVelocityX(float maxVelocityX) {
		this.maxVelocity.addToValueX(maxVelocityX);
	}

	public void addToMaxVelocityY(float maxVelocityY) {
		this.maxVelocity.addToValueY(maxVelocityY);
	}

	public float getMaxVelocityX() {
		return maxVelocity.getValueX();
	}

	public void setMaxVelocityX(float maxVelocityX) {
		this.maxVelocity.setValueX(maxVelocityX);
	}

	public float getMaxVelocityY() {
		return maxVelocity.getValueY();
	}

	public void setMaxVelocityY(float maxVelocityY) {
		this.maxVelocity.setValueY(maxVelocityY);
	}
	
	public void setAcceleration(float acclX, float acclY) {
		this.acceleration.setValueX(acclX);
		this.acceleration.setValueY(acclY);		
	}
	
	public float getAccelerationX() {
		return acceleration.getValueX();
	}

	public void setAccelerationX(float acclX) {
		this.acceleration.setValueX(acclX);
	}
	
	public float getAccelerationY() {
		return acceleration.getValueY();
	}

	public void setAccelerationY(float acclY) {
		this.acceleration.setValueX(acclY);
	}
	
	public void setInitVelocity(float initVelocityX, float initVelocityY) {
		this.initVelocity.setValueX(initVelocityX);
		this.initVelocity.setValueY(initVelocityY);		
	}
	
	public float getInitVelocityX() {
		return initVelocity.getValueX();
	}

	public void setInitVelocityX(float initVelocityX) {
		this.initVelocity.setValueX(initVelocityX);
	}
	
	public float getInitVelocityY() {
		return initVelocity.getValueY();
	}

	public void setInitVelocityY(float initVelocityY) {
		this.acceleration.setValueX(initVelocityY);
	}

	public void copy(Force force) {
		this.maxVelocity.copy(force.maxVelocity);
		this.acceleration.copy(force.acceleration);
	}
	
	public void add(Force force) {
		this.maxVelocity.add(force.maxVelocity);
		this.acceleration.add(force.acceleration);
	}
}
