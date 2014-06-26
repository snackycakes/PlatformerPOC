package platformer.framework;

import java.util.ArrayList;

import platformer.framework.Collision.CollisionType;

public class Layer {
	protected int depth;
	
	protected ArrayList<Pawn> pawns = new ArrayList<Pawn>();
	protected ArrayList<Actor> actors = new ArrayList<Actor>();
	protected Tile tiles[][] = new Tile[200][200];
	protected Size tileSize = new Size(0, 0);
	protected float gravity = 2f;
	protected float friction = 2f;
	
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
		// gravity logic
		mob.applyForce(0, gravity);
		
		//place holder for friction
		if (mob.getVelocity().getSpeedX() >= 0) {
			mob.applyForce(Math.max(mob.getVelocity().getSpeedX() - friction, 0f), 0f);
		} else if (mob.getVelocity().getSpeedX() < 0) {
			mob.applyForce(Math.min(mob.getVelocity().getSpeedX() + friction, 0f), 0f);
		}
		
		mob.update(elapsedTime);
		
		// check and respond to tile collisions
		ArrayList<Collision> tileCollisions = checkTileCollisions(mob);
		for (int colIndex = 0; colIndex < tileCollisions.size(); colIndex++) {
			Collision tileCollision = tileCollisions.get(colIndex);
			
			switch (tileCollision.getCollisionType()) {
				case LOWER:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionY(mob.getDesiredPositionY() - ((tileCollision.getActiveHitBox().getyPos() + tileCollision.getActiveHitBox().getSizeY()) - tileCollision.getCollisionHitBox().getyPos()));
					}
					break;
				case UPPER:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionY(mob.getDesiredPositionY() + ((tileCollision.getCollisionHitBox().getyPos() + tileCollision.getCollisionHitBox().getSizeY()) - tileCollision.getActiveHitBox().getyPos()));
					}
					break;
				case RIGHT:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionX(mob.getDesiredPositionX() - ((tileCollision.getActiveHitBox().getxPos() + tileCollision.getActiveHitBox().getSizeX()) - tileCollision.getCollisionHitBox().getxPos()));
					}
					break;
				case LEFT:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionX(mob.getDesiredPositionX() + ((tileCollision.getCollisionHitBox().getxPos() + tileCollision.getCollisionHitBox().getSizeX()) - tileCollision.getActiveHitBox().getxPos()));
					}
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
	
	public Position getTileArrayPosition(HitBox hitBox) {
		int xPos = ((hitBox.getxPos() + hitBox.sizeX / 2)) / tileSize.width;
		int yPos = ((hitBox.getyPos() + hitBox.sizeY / 2)) / tileSize.height;
		return new Position(xPos, yPos);
	}
	
	public ArrayList<Collision> checkTileCollisions(Mob mob) {
		ArrayList<Collision> tileCollisions = new ArrayList<Collision>();
		
		for (int hbIndex = 0; hbIndex < mob.getActiveSpriteContainer().getHitBoxes().size(); hbIndex++) {
			HitBox hitBox = mob.getActiveSpriteContainer().getHitBoxes().get(hbIndex);
			Position hitBoxTilePos =  getTileArrayPosition(hitBox);
			int hitBoxTileWidth = (int)Math.ceil((double)hitBox.getSizeX() / tileSize.width);
			int hitBoxTileHeight = (int)Math.ceil((double)hitBox.getSizeY() / tileSize.height);
			
			// check collision with tiles directly underneath hitbox
			int yPos = hitBoxTilePos.getyPos() + hitBoxTileHeight;
			for (int xPos = hitBoxTilePos.getxPos(); xPos < hitBoxTilePos.getxPos() + hitBoxTileWidth; xPos++) {
				Tile tile = tiles[xPos][yPos];
				if (tile != null) {
					for (int tilehbIndex = 0; tilehbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); tilehbIndex++) {
						HitBox tileHitBox = tile.getActiveSpriteContainer().getHitBoxes().get(tilehbIndex);
						if (hitBox.checkCollision(tileHitBox)){
							tileCollisions.add(new Collision(CollisionType.LOWER, hitBox, tile, tileHitBox));
							break;
						}
					}
				}					
			}
			
			// check collision with tiles directly above hitbox
			yPos = hitBoxTilePos.getyPos() - 1;
			for (int xPos = hitBoxTilePos.getxPos(); xPos < hitBoxTilePos.getxPos() + hitBoxTileWidth; xPos++) {
				Tile tile = tiles[xPos][yPos];
				if (tile != null) {
					for (int tilehbIndex = 0; tilehbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); tilehbIndex++) {
						HitBox tileHitBox = tile.getActiveSpriteContainer().getHitBoxes().get(tilehbIndex);
						if (hitBox.checkCollision(tileHitBox)){
							tileCollisions.add(new Collision(CollisionType.UPPER, hitBox, tile, tileHitBox));
							break;
						}
					}
				}					
			}
			
			// check collision with tiles directly underneath hitbox
			int xPos = hitBoxTilePos.getxPos() + hitBoxTileWidth;
			for (yPos = hitBoxTilePos.getyPos(); yPos < hitBoxTilePos.getyPos() + hitBoxTileWidth; yPos++) {
				Tile tile = tiles[xPos][yPos];
				if (tile != null) {
					for (int tilehbIndex = 0; tilehbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); tilehbIndex++) {
						HitBox tileHitBox = tile.getActiveSpriteContainer().getHitBoxes().get(tilehbIndex);
						if (hitBox.checkCollision(tileHitBox)){
							tileCollisions.add(new Collision(CollisionType.RIGHT, hitBox, tile, tileHitBox));
							break;
						}
					}
				}					
			}
			
			// check collision with tiles left of the hitbox
			xPos = hitBoxTilePos.getxPos() - 1;
			for (yPos = hitBoxTilePos.getyPos(); yPos < hitBoxTilePos.getyPos() + hitBoxTileWidth; yPos++) {
				Tile tile = tiles[xPos][yPos];
				if (tile != null) {
					for (int tilehbIndex = 0; tilehbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); tilehbIndex++) {
						HitBox tileHitBox = tile.getActiveSpriteContainer().getHitBoxes().get(tilehbIndex);
						if (hitBox.checkCollision(tileHitBox)){
							tileCollisions.add(new Collision(CollisionType.LEFT, hitBox, tile, tileHitBox));
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
