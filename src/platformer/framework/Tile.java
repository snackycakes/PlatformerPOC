package platformer.framework;

public class Tile {
	protected Position position;
	protected SpriteContainer activeSpriteContainer;
	
	protected void init() {
	}
	
	public Sprite getActiveSprite() {
		return activeSpriteContainer.getSprite();
	}
	
	public SpriteContainer getActiveSpriteContainer() {
		return activeSpriteContainer;
	}

	public void setActiveSpriteContainer(SpriteContainer activeSpriteContainer) {
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public void setActiveSpriteContainer(Sprite activeSprite) {
		this.activeSpriteContainer = new StaticSprite(activeSprite, position);
	}
	
	public Tile(SpriteContainer activeSpriteContainer) {
		position = new Position(0,0);
		this.activeSpriteContainer = activeSpriteContainer;
		init();
	}
	
	public Tile(SpriteContainer activeSpriteContainer, Position position) {
		this.position = position;
		this.activeSpriteContainer = activeSpriteContainer;
		init();
	}
	
	public Tile(SpriteContainer activeSpriteContainer, int xPos, int yPos) {
		this.position = new Position(xPos, yPos);
		this.activeSpriteContainer = activeSpriteContainer;
		init();
	}
	
	public Tile(Sprite activeSprite) {
		this.position = new Position(0,0);
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
		init();
	}
	
	public Tile(Sprite activeSprite, Position position) {
		this.position = position;
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
		init();
	}
	
	public Tile(Sprite activeSprite, int xPos, int yPos) {
		this.position = new Position(xPos, yPos);
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
		init();
	}
}
