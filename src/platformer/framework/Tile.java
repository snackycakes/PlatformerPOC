package platformer.framework;

public class Tile extends Node {
	
	public Tile(SpriteContainer activeSpriteContainer) {
		position = new Position(0,0);
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public Tile(SpriteContainer activeSpriteContainer, Position position) {
		this.position = position;
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public Tile(SpriteContainer activeSpriteContainer, int xPos, int yPos) {
		this.position = new Position(xPos, yPos);
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public Tile(Sprite activeSprite) {
		this.position = new Position(0,0);
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
	}
	
	public Tile(Sprite activeSprite, Position position) {
		this.position = position;
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
	}
	
	public Tile(Sprite activeSprite, int xPos, int yPos) {
		this.position = new Position(xPos, yPos);
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
