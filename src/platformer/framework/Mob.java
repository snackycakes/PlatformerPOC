package platformer.framework;

import java.util.HashMap;

public abstract class Mob extends Node {
	
	protected SpriteAsset activeSpriteAsset = null;

	public Mob() {
		super();
	}

	public Mob(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public Mob(Position position) {
		super(position);
	}	
	
	public SpriteAsset getActiveSpriteAsset() {
		return activeSpriteAsset;
	}

	public void setActiveSpriteAsset(SpriteAsset activeSpriteAsset) {
		this.activeSpriteAsset = activeSpriteAsset;
	}

	@Override
	public void update(long elapsedTime) {
		if (activeSpriteAsset != null) {
			activeSpriteAsset.update(elapsedTime);
		}
	}
	
	
}
