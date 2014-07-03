package platformer.game;

import platformer.framework.AnimatedSprite;
import platformer.framework.HitBox;
import platformer.framework.Pawn;
import platformer.framework.OrderedPair;
import platformer.framework.StaticSprite;

public class Mario extends Pawn {
	
	protected boolean isFacingRight = true;
	
	public Mario() {
		super();
	}

	public Mario(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Mario(OrderedPair position) {
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
		marioWalkingRight.addHitBox(new HitBox(position, new OrderedPair(2, 0), Assets.TILESIZE - 2, Assets.TILESIZE - 1));
		movingRight = marioWalkingRight;
		
		StaticSprite marioSlidingRight = new StaticSprite(Assets.Sprites.SmallMarioSlidingRight, position);
		marioSlidingRight.addHitBox(new HitBox(position, new OrderedPair(0, 0), Assets.TILESIZE, Assets.TILESIZE));
		slidingRight = marioSlidingRight;		
		
		AnimatedSprite marioWalkingLeft = new AnimatedSprite(position);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft1, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft2, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft3, 100);
		marioWalkingLeft.addFrame(Assets.Sprites.SmallMarioWalkingLeft2, 100);
		marioWalkingLeft.addHitBox(new HitBox(position, new OrderedPair(0, 0), Assets.TILESIZE - 2, Assets.TILESIZE - 1));
		movingLeft = marioWalkingLeft;
		
		StaticSprite marioSlidingLeft = new StaticSprite(Assets.Sprites.SmallMarioSlidingLeft, position);
		marioSlidingLeft.addHitBox(new HitBox(position, new OrderedPair(0, 0), Assets.TILESIZE, Assets.TILESIZE));
		slidingLeft = marioSlidingLeft;
		
		StaticSprite marioStandingLeft = new StaticSprite(Assets.Sprites.SmallMarioStandingLeft, position);
		marioStandingLeft.addHitBox(new HitBox(position, new OrderedPair(0, 0), Assets.TILESIZE, Assets.TILESIZE));
		standingLeft = marioStandingLeft;
		
		StaticSprite marioStandingRight = new StaticSprite(Assets.Sprites.SmallMarioStandingRight, position);
		marioStandingRight.addHitBox(new HitBox(position, new OrderedPair(0, 0), Assets.TILESIZE, Assets.TILESIZE));
		standingRight = marioStandingRight;
		
		activeSpriteContainer = movingRight;		
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		
		if (isMovingRight) {
			isFacingRight = true;
			
			if (velocity.getValueX() < 0) {
				activeSpriteContainer = slidingLeft;
			} else {
				activeSpriteContainer = movingRight;
			}
		} else if (isMovingLeft) {
			isFacingRight = false;
			
			if (velocity.getValueX() > 0) {
				activeSpriteContainer = slidingRight;
			} else {
				activeSpriteContainer = movingLeft;	
			}
		}
		
		if (velocity.getValueX() == 0 && canJump) {
			if (isFacingRight) {
				activeSpriteContainer = standingRight;
			} else {
				activeSpriteContainer = standingLeft;
			}
		}
		
		activeSpriteContainer.updateHitBoxes(desiredPosition);
	}	
}
