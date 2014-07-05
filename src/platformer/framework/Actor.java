package platformer.framework;

public abstract class Actor extends Mob {
	
	protected SpriteContainer standingLeft;
	protected SpriteContainer standingRight;
	protected SpriteContainer movingLeft;
	protected SpriteContainer movingRight;
	protected SpriteContainer jumpingLeft;
	protected SpriteContainer jumpingRight;
	protected SpriteContainer slidingRight;
	protected SpriteContainer slidingLeft;
	
	protected float movementForce = 2f;
	protected float movementAccl = .12f;
	protected float jumpForce = -3.6f;
	protected float jumpInitialForce = -2.8f;
	protected float jumpAcceleration = -2f;
	protected float jumpMaxFrames = 20f;
	protected float jumpFrameCount = 0f;
	
	protected boolean isMovingRight = false;
	protected boolean isMovingLeft = false;
	protected boolean isJumping = false;

	public Actor() {
		super();
	}

	public Actor(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Actor(OrderedPair position) {
		super(position);
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		if (isJumping && grounded) {
			this.isJumping = true;
			grounded = false;
			jumpFrameCount = 0;
		} else if (!isJumping) {
			this.isJumping = false;
			jumpFrameCount = 0;
		}
	}

	@Override
	public void update(long elapsedTime) {
		if (isMovingRight) {
			applyForce("movement", 0, 0, movementForce, 0, movementAccl, 0);
		}
		if (isMovingLeft) {
			applyForce("movement", 0, 0, -movementForce, 0, -movementAccl, 0);
		}
		if (isJumping) {
			if (jumpFrameCount == 0) {
				applyForce("jump", 0, 0, 0, jumpForce, 0, jumpInitialForce);
			} else {
				applyForce("jump", 0, 0, 0, jumpForce, 0, jumpAcceleration);
			}
			jumpFrameCount++;
			if (jumpFrameCount >= jumpMaxFrames) {
				isJumping = false;
				jumpFrameCount = 0;			
			}
		}
		
		OrderedPair maxVelocity = new OrderedPair (0, 0);
		OrderedPair minVelocity = new OrderedPair (0, 0);
		
		for (Force force : appliedForces.values()) {
			maxVelocity.setValueX(Math.max(force.getMaxVelocityX(), maxVelocity.getValueX()));
			maxVelocity.setValueY(Math.max(force.getMaxVelocityY(), maxVelocity.getValueY()));
			minVelocity.setValueX(Math.min(force.getMaxVelocityX(), minVelocity.getValueX()));
			minVelocity.setValueY(Math.min(force.getMaxVelocityY(), minVelocity.getValueY()));
		}
		
		for (Force force : appliedForces.values()) {
			if (force.getMaxVelocityX() > 0) {
				velocity.setValueX(Math.min(velocity.getValueX() + force.getAccelerationX(), maxVelocity.getValueX()));
			} else if (force.getMaxVelocityX() < 0) {
				velocity.setValueX(Math.max(velocity.getValueX() + force.getAccelerationX(), minVelocity.getValueX()));
			}
			
			if (force.getMaxVelocityY() > 0) {
				velocity.setValueY(Math.min(velocity.getValueY() + force.getAccelerationY(), maxVelocity.getValueY()));
			} else if (force.getMaxVelocityY() < 0) {
				velocity.setValueY(Math.max(velocity.getValueY() + force.getAccelerationY(), minVelocity.getValueY()));
			}	
		}
		
		appliedForces.clear();
				
		this.desiredPosition.copy(position);		
		this.desiredPosition.add(velocity);
		
		if (activeSpriteContainer != null) {
			activeSpriteContainer.update(elapsedTime, desiredPosition);
		}
		
		super.update(elapsedTime);
	}

	@Override
	public void collisionUpdate(Collision collision) {
		if (collision == null) {
			grounded = false;
		} else {
			switch (collision.collisionType) {
				case LOWER:
				case DIAGLOWERLEFT:
				case DIAGLOWERRIGHT:
					if (collision.collisionNode.isStopsMovement() && !isJumping) {
						grounded = true;
					}
					break;
				default:
					break;
			}	
		}
	}

	public SpriteContainer getJumpingLeft() {
		return jumpingLeft;
	}

	public void setJumpingLeft(SpriteContainer jumpingLeft) {
		this.jumpingLeft = jumpingLeft;
	}

	public SpriteContainer getJumpingRight() {
		return jumpingRight;
	}

	public void setJumpingRight(SpriteContainer jumpingRight) {
		this.jumpingRight = jumpingRight;
	}

	public float getMovementForce() {
		return movementForce;
	}

	public void setMovementForce(float movementForce) {
		this.movementForce = movementForce;
	}

	public float getMovementAccl() {
		return movementAccl;
	}

	public void setMovementAccl(float movementAccl) {
		this.movementAccl = movementAccl;
	}

	public float getJumpForce() {
		return jumpForce;
	}

	public void setJumpForce(float jumpForce) {
		this.jumpForce = jumpForce;
	}

	public float getJumpFrameCount() {
		return jumpFrameCount;
	}

	public void setJumpFrameCount(float jumpFrameCount) {
		this.jumpFrameCount = jumpFrameCount;
	}

	public float getJumpInitialForce() {
		return jumpInitialForce;
	}

	public void setJumpInitialForce(float jumpInitialForce) {
		this.jumpInitialForce = jumpInitialForce;
	}

	public float getJumpAcceleration() {
		return jumpAcceleration;
	}

	public void setJumpAcceleration(float jumpAcceleration) {
		this.jumpAcceleration = jumpAcceleration;
	}

	public float getJumpMaxFrames() {
		return jumpMaxFrames;
	}

	public void setJumpMaxFrames(float jumpMaxFrames) {
		this.jumpMaxFrames = jumpMaxFrames;
	}
	
	
	
}
