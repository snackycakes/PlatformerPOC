package platformer.game;

import platformer.framework.Actor;
import platformer.framework.OrderedPair;
import platformer.framework.Sprite;
import platformer.framework.SpriteContainer;

public class MarioBreakableTile extends MarioTile {

	public MarioBreakableTile(Sprite activeSprite, int xPos, int yPos) {
		super(activeSprite, xPos, yPos);
		// TODO Auto-generated constructor stub
	}

	public MarioBreakableTile(Sprite activeSprite, OrderedPair position) {
		super(activeSprite, position);
		// TODO Auto-generated constructor stub
	}

	public MarioBreakableTile(Sprite activeSprite) {
		super(activeSprite);
		// TODO Auto-generated constructor stub
	}

	public MarioBreakableTile(SpriteContainer activeSpriteContainer, int xPos, int yPos) {
		super(activeSpriteContainer, xPos, yPos);
		// TODO Auto-generated constructor stub
	}

	public MarioBreakableTile(SpriteContainer activeSpriteContainer, OrderedPair position) {
		super(activeSpriteContainer, position);
		// TODO Auto-generated constructor stub
	}

	public MarioBreakableTile(SpriteContainer activeSpriteContainer) {
		super(activeSpriteContainer);
		// TODO Auto-generated constructor stub
	}
	
	private void SpawnBreakPiece(int velocityX, int velocityY) {
		Actor breakPiece = new Actor(this.getPositionX(), this.getPositionY());
		breakPiece.setActiveSpriteContainer(Assets.Sprites.BrownBrickPiece);
		breakPiece.setVelocity(velocityX, velocityY);
		nodeEventListener.SpawnMob(breakPiece);
	}

	@Override
	public void destroyNode() {
		super.destroyNode();
		
		SpawnBreakPiece(-1, -3);
		SpawnBreakPiece(1, -3);
		SpawnBreakPiece(-1, -6);
		SpawnBreakPiece(1, -6);
	}
	
	

}
