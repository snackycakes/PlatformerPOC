package platformer.framework;

public class Force {
	private float forceX;
	private float forceY;

	public Force() {
		setForce(0f, 0f);
	}

	public Force(float forceX, float forceY) {
		setForce(forceX, forceY);
	}

	public void setForce(float forceX, float forceY) {
		this.forceX = forceX;
		this.forceY = forceY;
	}

	public void addToForceX(float forceX) {
		this.forceY += forceX;
	}

	public void addToForceY(float forceY) {
		this.forceY += forceY;
	}

	public float getForceX() {
		return forceX;
	}

	public void setForceX(float forceX) {
		this.forceX = forceX;
	}

	public float getForceY() {
		return forceY;
	}

	public void setForceY(float forceY) {
		this.forceY = forceY;
	}

	public void copy(Force force) {
		this.setForce(force.forceX, force.forceY);
	}
	
	public void addValue(Force force) {
		forceX += force.forceX;
		forceY += force.forceY;
	}
}
