package platformer.framework;

public class Tile extends Node {
	
	public Tile(SpriteContainer activeSpriteContainer) {
		position = new OrderedPair(0,0);
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public Tile(SpriteContainer activeSpriteContainer, OrderedPair position) {
		this.position = position;
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public Tile(SpriteContainer activeSpriteContainer, int xPos, int yPos) {
		this.position = new OrderedPair(xPos, yPos);
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public Tile(Sprite activeSprite) {
		this.position = new OrderedPair(0,0);
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
	}
	
	public Tile(Sprite activeSprite, OrderedPair position) {
		this.position = position;
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
	}
	
	public Tile(Sprite activeSprite, int xPos, int yPos) {
		this.position = new OrderedPair(xPos, yPos);
		this.activeSpriteContainer = new StaticSprite(activeSprite, this.position);
	}

	public OrderedPair getPosition() {
		return position;
	}

	public void setPosition(OrderedPair position) {
		this.position = position;
	}
}
