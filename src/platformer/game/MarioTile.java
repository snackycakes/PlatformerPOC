package platformer.game;

import platformer.framework.HitBox;
import platformer.framework.Position;
import platformer.framework.Sprite;
import platformer.framework.SpriteContainer;
import platformer.framework.Tile;

public class MarioTile extends Tile {

	protected void init() {
		this.activeSpriteContainer.addHitBox(new HitBox(position, new Position(0,0), Assets.TILESIZE, Assets.TILESIZE));
	}

	public MarioTile(SpriteContainer activeSpriteContainer) {
		super(activeSpriteContainer);
		init();
	}

	public MarioTile(SpriteContainer activeSpriteContainer, Position position) {
		super(activeSpriteContainer, position);
		init();
	}

	public MarioTile(SpriteContainer activeSpriteContainer, int xPos, int yPos) {
		super(activeSpriteContainer, xPos, yPos);
		init();
	}

	public MarioTile(Sprite activeSprite) {
		super(activeSprite);
		init();
	}

	public MarioTile(Sprite activeSprite, Position position) {
		super(activeSprite, position);
		init();
	}

	public MarioTile(Sprite activeSprite, int xPos, int yPos) {
		super(activeSprite, xPos, yPos);
		init();
	}

}
