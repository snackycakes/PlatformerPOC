package platformer.framework;

public abstract class Actor extends Mob {
	
	protected SpriteAsset standingLeft;
	protected SpriteAsset standingRight;
	protected SpriteAsset movingLeft;
	protected SpriteAsset movingRight;
	protected SpriteAsset jumping;

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
