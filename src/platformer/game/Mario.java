// Test comment using steven's account

package platformer.game;

import platformer.framework.AnimatedSprite;
import platformer.framework.HitBox;
import platformer.framework.Pawn;
import platformer.framework.Position;

public class Mario extends Pawn {
	
	public Mario() {
		super();
	}

	public Mario(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Mario(Position position) {
		super(position);
	}

	@Override
	protected void init() {
		super.init();
		
		AnimatedSprite marioWalkingRight = new AnimatedSprite(position);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight1, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight3, 100);
		marioWalkingRight.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		marioWalkingRight.addHitBox(new HitBox(position, new Position(2, 0), Assets.TILESIZE - 2, Assets.TILESIZE - 1));
		movingRight = marioWalkingRight;
		
		AnimatedSprite marioWalkingLeft = new AnimatedSprite(position);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft1, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft2, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft3, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft2, 100);
		marioWalkingLeft.addHitBox(new HitBox(position, new Position(0, 0), Assets.TILESIZE - 2, Assets.TILESIZE - 1));
		movingLeft = marioWalkingLeft;
		
		activeSpriteContainer = movingRight;		
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		
		if (isMovingRight) {
			activeSpriteContainer = movingRight;
		} else if (isMovingLeft) {
			activeSpriteContainer = movingLeft;
		}
	}	
}
