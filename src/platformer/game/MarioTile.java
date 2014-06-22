package platformer.game;

import platformer.framework.HitBox;
import platformer.framework.Position;
import platformer.framework.Sprite;
import platformer.framework.SpriteContainer;
import platformer.framework.Tile;

public class MarioTile extends Tile {

	@Override
	protected void init() {
		super.init();
		this.activeSpriteContainer.addHitBox(new HitBox(position, new Position(0,0), Assets.TILESIZE, Assets.TILESIZE));
	}

	public MarioTile(SpriteContainer activeSpriteContainer) {
		super(activeSpriteContainer);
		// TODO Auto-generated constructor stub
	}

	public MarioTile(SpriteContainer activeSpriteContainer, Position position) {
		super(activeSpriteContainer, position);
		// TODO Auto-generated constructor stub
	}

	public MarioTile(SpriteContainer activeSpriteContainer, int xPos, int yPos) {
		super(activeSpriteContainer, xPos, yPos);
		// TODO Auto-generated constructor stub
	}

	public MarioTile(Sprite activeSprite) {
		super(activeSprite);
		// TODO Auto-generated constructor stub
	}

	public MarioTile(Sprite activeSprite, Position position) {
		super(activeSprite, position);
		// TODO Auto-generated constructor stub
	}

	public MarioTile(Sprite activeSprite, int xPos, int yPos) {
		super(activeSprite, xPos, yPos);
		// TODO Auto-generated constructor stub
	}

}
