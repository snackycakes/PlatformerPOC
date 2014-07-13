package platformer.framework;

import java.util.HashMap;

public abstract class Mob extends Node {
	protected OrderedPair desiredPosition;
	protected OrderedPair velocity;
	protected boolean desiredPositionAdjusted = false;
	protected HashMap<String, Force> appliedForces;
	protected boolean grounded = false;	

	protected void init() {
		velocity = new OrderedPair();
		desiredPosition = new OrderedPair();
		desiredPosition.copy(position);
		appliedForces = new HashMap<String, Force>();
	}

	public Mob() {
		super();
		init();
	}

	public Mob(int xPos, int yPos) {
		super(xPos, yPos);
		init();
	}

	public Mob(OrderedPair position) {
		super(position);
		init();
	}
	
	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	public void applyForce(String forceName, Force force) {
		appliedForces.put(forceName, force);
	}
	
	public void applyForce(String forceName, float maxVelocityX, float maxVelocityY, float acclX, float acclY) {
		appliedForces.put(forceName, new Force(maxVelocityX, maxVelocityY, acclX, acclY));
	}
	
	public OrderedPair getDesiredPosition() {
		return desiredPosition;
	}

	public void setDesiredPosition(OrderedPair desiredPosition) {
		desiredPositionAdjusted = true;
		this.desiredPosition = desiredPosition;
	}
	
	public int getDesiredPositionX() {
		return desiredPosition.getPosX();
	}
	
	public void setDesiredPositionX(int xPos) {
		desiredPositionAdjusted = true;
		this.desiredPosition.setPosX(xPos);
	}
	
	public int getDesiredPositionY() {
		return desiredPosition.getPosY();
	}
	
	public void setDesiredPositionY(int yPos) {
		desiredPositionAdjusted = true;
		this.desiredPosition.setPosY(yPos);
	}

	public OrderedPair getVelocity() {
		return velocity;
	}

	public void setVelocity(OrderedPair velocity) {
		this.velocity = velocity;
	}
	
	public void setVelocity(float speedX, float speedY) {
		this.velocity.setValueX(speedX);
		this.velocity.setValueY(speedY);
	}
	
	public void setVelocityX(float speedX) {
		this.velocity.setValueX(speedX);
	}
	
	public void setVelocityY(float speedY) {
		this.velocity.setValueY(speedY);
	}
	
	public boolean isGrounded() {
		return grounded;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	public void commitDesiredPosition() {
		this.position.copy(desiredPosition);
	}
	
	public void collisionUpdate(TileContainer tileContainer) {
	}
	
	
}
