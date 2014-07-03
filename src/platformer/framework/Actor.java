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
	
	protected float movementForce = 2;
	protected float jumpForce = -9;
	protected float jumpFrameCount = 0;
	protected float jumpInitialForce = -5;
	protected float jumpAcceleration = -.8f;
	protected float accelerationUp = -.8f;
	protected float accelerationDown = .3f;
	protected float jumpMaxFrames = 10;
	
	protected boolean canJump = false;
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
		if (isJumping && canJump) {
			this.isJumping = true;
			canJump = false;
			jumpFrameCount = 0;
		} else if (!isJumping) {
			this.isJumping = false;
			jumpFrameCount = 0;
		}
	}

	@Override
	public void update(long elapsedTime) {
		if (isMovingRight) {
			applyForce(movementForce, 0);
		}
		if (isMovingLeft) {
			applyForce(-movementForce, 0);
		}
		if (isJumping) {
			applyForce(0, jumpForce);
			jumpFrameCount++;
			if (jumpFrameCount >= jumpMaxFrames) {
				isJumping = false;
				jumpFrameCount = 0;			
			}
			
			if (jumpFrameCount == 1) {
				accelerationUp = jumpInitialForce;
			} else {
				accelerationUp = jumpAcceleration;
			}
		}
		
		desiredPositionAdjusted = false;
		
		if (appliedForce.getForceX() >= 0) {
			velocity.setValueX(Math.min(velocity.getValueX() + 1.3f, appliedForce.getForceX()));
		} else if (appliedForce.getForceX() < 0) {
			velocity.setValueX(Math.max(velocity.getValueX() - 1.3f, appliedForce.getForceX()));
		}
		
		if (appliedForce.getForceY() >= 0) {
			velocity.setValueY(Math.min(velocity.getValueY() + accelerationDown, appliedForce.getForceY()));
		} else {
			velocity.setValueY(Math.max(velocity.getValueY() + accelerationUp, appliedForce.getForceY()));
		}

		//velocity.setValueY(Math.max(velocity.getValueY(), appliedForce.getForceY()));		
		
		appliedForce.setForce(0, 0);
				
		this.desiredPosition.copy(position);		
		this.desiredPosition.add(velocity);
		
		if (activeSpriteContainer != null) {
			activeSpriteContainer.update(elapsedTime, desiredPosition);
		}
		
		super.update(elapsedTime);
	}

	@Override
	public void collisionOccurred(Collision collision) {
		switch (collision.collisionType) {
		case LOWER:
		case DIAGLOWERLEFT:
		case DIAGLOWERRIGHT:
			if (collision.collisionNode.isStopsMovement() && !isJumping) {
				canJump = true;
			}
			break;
		default:
			break;
		}
	}
	
	
	
}
