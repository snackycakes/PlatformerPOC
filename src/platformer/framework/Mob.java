package platformer.framework;

import java.util.ArrayList;

public abstract class Mob extends Node {
	protected Position desiredPosition;
	protected Velocity velocity;
	protected Force appliedForce;
	protected boolean desiredPositionAdjusted = false;
		
	protected void init() {
		appliedForce = new Force(0, 0);
		velocity = new Velocity();
		desiredPosition = new Position();
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

	public Mob(Position position) {
		super(position);
		init();
	}
	
	@Override
	public void update(long elapsedTime) {
		desiredPositionAdjusted = false;
		
		if (appliedForce.getForceX() > 0) {
			velocity.setSpeedX(Math.max(velocity.getSpeedX(), appliedForce.getForceX()));
		} else if (appliedForce.getForceX() < 0) {
			velocity.setSpeedX(Math.min(velocity.getSpeedX(), appliedForce.getForceX()));
		}
		velocity.setSpeedY(Math.max(velocity.getSpeedY(), appliedForce.getForceY()));		
		
		appliedForce.setForce(0, 0);
				
		this.desiredPosition.copy(position);		
		this.desiredPosition.addVelocity(velocity);
		
		if (activeSpriteContainer != null) {
			activeSpriteContainer.update(elapsedTime, desiredPosition);
		}
	}
	
	public void applyForce(Force force) {
		appliedForce.addValue(force);
	}
	
	public void applyForce(float forceX, float forceY) {
		appliedForce.addValue(new Force(forceX, forceY));
	}
	
	public Position getDesiredPosition() {
		return desiredPosition;
	}

	public void setDesiredPosition(Position desiredPosition) {
		desiredPositionAdjusted = true;
		this.desiredPosition = desiredPosition;
	}
	
	public int getDesiredPositionX() {
		return desiredPosition.getxPos();
	}
	
	public void setDesiredPositionX(int xPos) {
		desiredPositionAdjusted = true;
		this.desiredPosition.setxPos(xPos);
	}
	
	public int getDesiredPositionY() {
		return desiredPosition.getyPos();
	}
	
	public void setDesiredPositionY(int yPos) {
		desiredPositionAdjusted = true;
		this.desiredPosition.setyPos(yPos);
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}
	
	public void setVelocity(float speedX, float speedY) {
		this.velocity.setSpeedX(speedX);
		this.velocity.setSpeedY(speedY);
	}
	
	public void setVelocityX(float speedX) {
		this.velocity.setSpeedX(speedX);
	}
	
	public void setVelocityY(float speedY) {
		this.velocity.setSpeedY(speedY);
	}
	
	public void commitDesiredPosition() {
		this.position.copy(desiredPosition);
		if (desiredPositionAdjusted) {
			if (activeSpriteContainer != null) {
				activeSpriteContainer.updateHitBoxes(position);
			}
		}
	}
}
