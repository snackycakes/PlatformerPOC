package platformer.framework;

import java.util.HashMap;

public abstract class Mob extends Node {
	
	protected HashMap<Integer, SpriteAsset> spriteAssets = new HashMap<Integer, SpriteAsset>();
	protected SpriteAsset activeSpriteAsset = null;

	public SpriteAsset GetSpriteAsset() {
		return activeSpriteAsset; }
	
	public void addSpriteAsset(Integer assetKey, SpriteAsset spriteAsset) {
		this.spriteAssets.put(assetKey, spriteAsset);
		this.activeSpriteAsset = spriteAsset;
	}

	public HashMap<Integer, SpriteAsset> getSpriteAssets() {
		return spriteAssets;
	}

	public void setSpriteAssets(HashMap<Integer, SpriteAsset> spriteAssets) {
		this.activeSpriteAsset = null;
		this.spriteAssets = spriteAssets;
	}

	@Override
	public void update(long elapsedTime) {
		if (activeSpriteAsset != null) {
			activeSpriteAsset.update(elapsedTime);
		}
	}
	
	
}
