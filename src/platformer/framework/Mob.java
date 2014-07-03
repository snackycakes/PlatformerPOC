package platformer.framework;

import java.util.ArrayList;

public abstract class Mob extends Node {
	protected OrderedPair desiredPosition;
	protected OrderedPair velocity;
	protected Force appliedForce;
	protected boolean desiredPositionAdjusted = false;
		
	protected void init() {
		appliedForce = new Force(0, 0);
		velocity = new OrderedPair();
		desiredPosition = new OrderedPair();
		desiredPosition.copy(position);
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
	}
	
	public void applyForce(Force force) {
		appliedForce.addValue(force);
	}
	
	public void applyForce(float forceX, float forceY) {
		appliedForce.addValue(new Force(forceX, forceY));
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
	
	public void commitDesiredPosition() {
		this.position.copy(desiredPosition);
		if (desiredPositionAdjusted) {
			if (activeSpriteContainer != null) {
				activeSpriteContainer.updateHitBoxes(position);
			}
		}
	}
	
	public void collisionOccurred(Collision collision) {
	}
}
