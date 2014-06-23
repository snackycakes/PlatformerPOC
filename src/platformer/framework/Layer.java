package platformer.framework;

import java.util.ArrayList;

import platformer.framework.Collision.CollisionType;

public class Layer {
	protected int depth;
	
	protected ArrayList<Pawn> pawns = new ArrayList<Pawn>();
	protected ArrayList<Actor> actors = new ArrayList<Actor>();
	protected Tile tiles[][] = new Tile[200][200];
	protected Size tileSize = new Size(0, 0);
	protected float gravity = 0f;
	
	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	
	public void addPawn(Pawn pawn) {
		pawns.add(pawn);
	}
	
	public Size getTileSize() {
		return tileSize;
	}

	public void setTileSize(Size tileSize) {
		this.tileSize = tileSize;
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
	
	public void update(long elapsedTime) {		
		for (int pawnIdx = 0; pawnIdx < pawns.size(); pawnIdx++) {
			Pawn pawn = pawns.get(pawnIdx);
			updateMob(pawn, elapsedTime);
		}
		
		for (int actorIdx = 0; actorIdx < actors.size(); actorIdx++) {
			Actor actor = actors.get(actorIdx);
			updateMob(actor, elapsedTime);
		}
	}
	
	public void updateMob(Mob mob, long elapsedTime) {
		mob.update(elapsedTime);
		applyGravity(mob);
		
		// check and respond to tile collisions
		ArrayList<TileCollision> tileCollisions = checkTileCollisions(mob);
		for (int colIndex = 0; colIndex < tileCollisions.size(); colIndex++) {
			TileCollision tileCollision = tileCollisions.get(colIndex);
			
			switch (tileCollision.getCollisionType()) {
				case LOWER:
					mob.getDesiredPosition().copy(mob.getPosition());
					mob.setVelocityY(0);
					break;
				case UPPER:
					break;
				case LEFT:
					break;
				case RIGHT:
					break;
				case DIAGLOWERLEFT:
					break;
				case DIAGLOWERRIGHT:
					break;
				case DIAGUPPERLEFT:
					break;
				case DIAGUPPERRIGHT:
					break;
				default:
					break;
			}
		}
		
		mob.commitDesiredPosition();
	}
	
	public void applyGravity(Mob mob) {
		mob.desiredPosition.addToPositionY(gravity);
	}
	
	public Position getTileArrayPosition(HitBox hitBox) {
		int xPos = ((hitBox.getxPos() + hitBox.sizeX / 2)) / tileSize.width;
		int yPos = ((hitBox.getyPos() + hitBox.sizeY / 2)) / tileSize.height;
		return new Position(xPos, yPos);
	}
	
	public ArrayList<TileCollision> checkTileCollisions(Mob mob) {
		ArrayList<TileCollision> tileCollisions = new ArrayList<TileCollision>();
		
		for (int hbIndex = 0; hbIndex < mob.getActiveSpriteContainer().getHitBoxes().size(); hbIndex++) {
			HitBox hitBox = mob.getActiveSpriteContainer().getHitBoxes().get(hbIndex);
			Position hitBoxTilePos =  getTileArrayPosition(hitBox);
			int hitBoxTileWidth = (int)Math.ceil((double)hitBox.getSizeX() / tileSize.width);
			int hitBoxTileHeight = (int)Math.ceil((double)hitBox.getSizeY() / tileSize.height);
			
			// check collision with tiles directly underneith hitbox
			int yPos = hitBoxTilePos.getyPos() + hitBoxTileHeight;
			for (int xPos = hitBoxTilePos.getxPos(); xPos < hitBoxTilePos.getxPos() + hitBoxTileWidth; xPos++) {
				Tile tile = tiles[xPos][yPos];
				if (tile != null) {
					for (int tilehbIndex = 0; tilehbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); tilehbIndex++) {
						HitBox tileHitBox = tile.getActiveSpriteContainer().getHitBoxes().get(tilehbIndex);
						if (hitBox.checkCollision(tileHitBox)){
							tileCollisions.add(new TileCollision(CollisionType.LOWER, tile));
							break;
						}
					}
				}					
			}

		}
		
		return tileCollisions;
	}
	
	public void update(int elapsedTime) {}
}
