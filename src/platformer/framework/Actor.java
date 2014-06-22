package platformer.framework;

public abstract class Actor extends Mob {
	
	protected SpriteContainer standingLeft;
	protected SpriteContainer standingRight;
	protected SpriteContainer movingLeft;
	protected SpriteContainer movingRight;
	protected SpriteContainer jumping;

	public Actor() {
		super();
	}

	public Actor(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Actor(Position position) {
		super(position);
	}
}
