package platformer.framework;

public class TileContainer {
	public enum PositionType {LOWER, UPPER, LEFT, RIGHT, DIAGLOWERLEFT, DIAGLOWERRIGHT, DIAGUPPERLEFT, DIAGUPPERRIGHT}
	
	private PositionType relativePosition;
	private HitBox relativeHitBox;
	private Tile tile;
	
	public TileContainer(PositionType relativePosition, HitBox relativeHitBox, Tile tile) {
		super();
		this.relativePosition = relativePosition;
		this.tile = tile;
		this.relativeHitBox = relativeHitBox;
	}

	public PositionType getRelativePosition() {
		return relativePosition;
	}

	public void setRelativePosition(PositionType relativePosition) {
		this.relativePosition = relativePosition;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public HitBox getRelativeHitBox() {
		return relativeHitBox;
	}

	public void setRelativeHitBox(HitBox relativeHitBox) {
		this.relativeHitBox = relativeHitBox;
	}	
}
