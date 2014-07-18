package platformer.framework;

public class Pawn extends Actor {
	
	protected OrderedPair minBoundary = new OrderedPair(0, 0);

	public Pawn() {
		// TODO Auto-generated constructor stub
	}

	public Pawn(int xPos, int yPos) {
		super(xPos, yPos);
		// TODO Auto-generated constructor stub
	}

	public Pawn(OrderedPair position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	public OrderedPair getMinBoundary() {
		return minBoundary;
	}

	public void setMinBoundary(OrderedPair minBoundary) {
		this.minBoundary = minBoundary;
	}

}
