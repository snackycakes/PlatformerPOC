package platformer.framework;

public abstract class Mob extends Node {
	
	protected SpriteContainer activeSpriteContainer;
	protected Position desiredPosition;
	protected Velocity velocity;
		
	@Override
	protected void init() {
		super.init();
		velocity = new Velocity();
		desiredPosition = new Position();
		desiredPosition.copy(position);
	}

	public Mob() {
		super();
	}

	public Mob(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Mob(Position position) {
		super(position);
	}
	
	public Sprite getActiveSprite() {
		return activeSpriteContainer.getSprite();
	}
		
	public SpriteContainer getActiveSpriteContainer() {
		return activeSpriteContainer;
	}

	public void setActiveSpriteContainer(SpriteContainer ActiveSpriteContainer) {
		this.activeSpriteContainer = ActiveSpriteContainer;
	}

	@Override
	public void update(long elapsedTime) {
		if (activeSpriteContainer != null) {
			activeSpriteContainer.update(elapsedTime, desiredPosition);
		}
		
		this.desiredPosition.copy(position);		
		this.desiredPosition.addVelocity(velocity);
	}

	public Position getDesiredPosition() {
		return desiredPosition;
	}

	public void setDesiredPosition(Position desiredPosition) {
		this.desiredPosition = desiredPosition;
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
	}
}
