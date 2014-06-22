package platformer.framework;

import java.util.ArrayList;

public class Layer {
	protected int depth;
	
	protected ArrayList<Pawn> pawns = new ArrayList<Pawn>();
	protected ArrayList<Actor> actors = new ArrayList<Actor>();
	protected Tile tiles[][] = new Tile[200][200];
	
	public void addPawn(Pawn pawn) {
		pawns.add(pawn);
	}
	
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setTile(Tile tile, int xPos, int yPos) {
		tiles[xPos][yPos] = tile;
	}
	
	public Tile getTile(int xPos, int yPos) {
		return tiles[xPos][yPos];
	}
	
	public ArrayList<Pawn> getPawns(){
		return pawns;
	}
	
	public ArrayList<Actor> getActors(){
		return actors;
	}
	
	public void update(int elapsedTime) {}
}
