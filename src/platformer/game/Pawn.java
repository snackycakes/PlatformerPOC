package platformer.game;

import platformer.framework.Mob;
import platformer.framework.Position;
import platformer.framework.SpriteAsset;

public class Pawn extends MarioMob {

	public Pawn() {
		super();
	}

	public Pawn(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Pawn(Position position) {
		super(position);
	}

	@Override
	public SpriteAsset GetSpriteAsset() {
		return super.GetSpriteAsset();
	}
	
	
}
