package platformer.framework;

public class Actor extends Mob {
	
	protected SpriteAsset standingLeft;
	protected SpriteAsset standingRight;
	protected SpriteAsset movingLeft;
	protected SpriteAsset movingRight;

	public Actor() {
	}

	public Actor(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Actor(Position position) {
		super(position);
	}
}
