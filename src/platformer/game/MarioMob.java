package platformer.game;

import platformer.framework.Mob;
import platformer.framework.Position;
import platformer.framework.SpriteAsset;

public class MarioMob extends Mob {
	
	public MarioMob() {
		super();
	}

	public MarioMob(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public MarioMob(Position position) {
		super(position);
	}

	public enum SpriteAssetTypes {
        DEFAULT(1), STANDINGLEFT(2), STANDINGRIGHT(3), WALKINGLEFT(4), JUMPING(5);

        private int value;

        private SpriteAssetTypes(int value) {
                this.value = value;
        }
        
        public int getValue() {
        	return this.value;
        }
	};

	@Override
	public SpriteAsset GetSpriteAsset() {
		return spriteAssets.get(SpriteAssetTypes.DEFAULT.getValue());
	}
	

}
