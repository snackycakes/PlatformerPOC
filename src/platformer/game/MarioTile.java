package platformer.game;

import platformer.framework.Position;
import platformer.framework.SpriteAsset;
import platformer.framework.Tile;

public class MarioTile extends Tile {

	public MarioTile(SpriteAsset activeSpriteAsset, int xPos, int yPos) {
		super(activeSpriteAsset, xPos, yPos);
		// TODO Auto-generated constructor stub
	}

	public MarioTile(SpriteAsset activeSpriteAsset, Position position) {
		super(activeSpriteAsset, position);
		// TODO Auto-generated constructor stub
	}

	public MarioTile(SpriteAsset activeSpriteAsset) {
		super(activeSpriteAsset);
		// TODO Auto-generated constructor stub
	}
}
