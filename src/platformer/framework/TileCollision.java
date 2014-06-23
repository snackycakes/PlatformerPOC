package platformer.framework;

public class TileCollision extends Collision {
	private Tile tile;

	public TileCollision(CollisionType collisionType) {
		super(collisionType);
	}

	public TileCollision(CollisionType collisionType, Tile tile) {
		super(collisionType);
		this.tile = tile;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
}
