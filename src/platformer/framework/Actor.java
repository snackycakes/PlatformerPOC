package platformer.framework;

public abstract class Actor extends Mob {
	
	protected SpriteContainer standingLeft;
	protected SpriteContainer standingRight;
	protected SpriteContainer movingLeft;
	protected SpriteContainer movingRight;
	protected SpriteContainer jumpingLeft;
	protected SpriteContainer jumpingRight;
	
	protected float movementForce = 2;
	protected float jumpForce = 4;
	
	protected boolean isMovingRight = false;
	protected boolean isMovingLeft = false;
	protected boolean isJumping = false;

	public Actor() {
		super();
	}

	public Actor(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Actor(Position position) {
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
		this.isJumping = isJumping;
	}

	@Override
	public void update(long elapsedTime) {
		if (isMovingRight) {
			applyForce(movementForce, 0);
		} else if (isMovingLeft) {
			applyForce(-movementForce, 0);
		} else if (isJumping) {
			applyForce(0, jumpForce);
		}	
		
		super.update(elapsedTime);
	}
	
	
	
}
