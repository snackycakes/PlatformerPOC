package platformer.game;

import platformer.framework.AnimatedSprite;
import platformer.framework.Pawn;
import platformer.framework.Position;

public class Mario extends Pawn {

	@Override
	protected void Init() {
		super.Init();
		
		AnimatedSprite marioWalkingRight = new AnimatedSprite();
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight1, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight3, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		movingRight = marioWalkingRight;
		
		AnimatedSprite marioWalkingLeft = new AnimatedSprite();
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft1, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft2, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft3, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft2, 100);
		movingLeft = marioWalkingLeft;
		
		activeSpriteAsset = movingRight;		
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
