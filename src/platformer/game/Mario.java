package platformer.game;

import platformer.framework.Actor;
import platformer.framework.AnimatedSprite;
import platformer.framework.Mob;
import platformer.framework.Position;
import platformer.framework.SpriteAsset;

public class Mario extends Actor {

	@Override
	protected void Init() {
		super.Init();
		
		AnimatedSprite marioWalkingRight = new AnimatedSprite();
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight1, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight3, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		this.movingRight = marioWalkingRight;
		
		this.activeSpriteAsset = this.movingRight;		
	}

	public Mario() {
		super();
	}

	public Mario(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Mario(Position position) {
		super(position);
	}
}
