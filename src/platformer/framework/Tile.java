package platformer.framework;

public abstract class Tile {
	Position position = new Position(0,0);
	protected SpriteAsset activeSpriteAsset;
	
	public SpriteAsset getActiveSpriteAsset() {
		return activeSpriteAsset;
	}

	public void setActiveSpriteAsset(SpriteAsset activeSpriteAsset) {
		this.activeSpriteAsset = activeSpriteAsset;
	}

	protected void Init() {}
	
	public Tile(SpriteAsset activeSpriteAsset) {
		this.activeSpriteAsset = activeSpriteAsset;
		Init();
	}
	
	public Tile(SpriteAsset activeSpriteAsset, Position position) {
		this.activeSpriteAsset = activeSpriteAsset;
		Init();
	}
	
	public Tile(SpriteAsset activeSpriteAsset, int xPos, int yPos) {
		this.activeSpriteAsset = activeSpriteAsset;
		Init();
	}
	
	public void update(long elapsedTime) {}
}
