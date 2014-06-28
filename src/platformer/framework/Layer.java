package platformer.framework;

import java.util.ArrayList;

import platformer.framework.Collision.CollisionType;

public class Layer {
	protected int depth;
	
	protected ArrayList<Mob> mobs = new ArrayList<Mob>();
	protected Tile tiles[][] = new Tile[200][200];
	protected Size tileSize = new Size(0, 0);
	protected float gravity = 2f;
	protected float friction = 0.2f;
	
	public void update(long elapsedTime) {			
		for (int mobIndex = 0; mobIndex < mobs.size(); mobIndex++) {
			updateMob(mobs.get(mobIndex), elapsedTime);
		}
	}
	
	public void updateMob(Mob mob, long elapsedTime) {
		// gravity logic
		mob.applyForce(0, gravity);
		
		//friction logic
		if (mob.getVelocity().getSpeedX() >= 0) {
			if (mob.getVelocity().getSpeedX() - friction >= 0) {
				mob.applyForce(-friction, 0f);
			} else {
				mob.applyForce(-mob.getVelocity().getSpeedX(), 0f);
			}
		} else{
			if (mob.getVelocity().getSpeedX() + friction <= 0) {
				mob.applyForce(friction, 0f);
			} else {
				mob.applyForce(-mob.getVelocity().getSpeedX(), 0f);
			}
		}
		
		mob.update(elapsedTime);
		
		// check and respond to tile collisions
		ArrayList<Collision> tileCollisions = checkTileCollisions(mob);
		for (int colIndex = 0; colIndex < tileCollisions.size(); colIndex++) {
			Collision tileCollision = tileCollisions.get(colIndex);
			
			switch (tileCollision.getCollisionType()) {
				case LOWER:
				case DIAGLOWERLEFT:
				case DIAGLOWERRIGHT:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setVelocityY(0);
						mob.setDesiredPositionY(mob.getDesiredPositionY() - ((tileCollision.getActiveHitBox().getyPos() + tileCollision.getActiveHitBox().getSizeY()) - tileCollision.getCollisionHitBox().getyPos()));
					}
					break;
				case DIAGUPPERLEFT:
				case DIAGUPPERRIGHT:
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
			boolean tileCollsion = false;
			
			// check collision with tiles directly underneath hitbox
			int yPos = hitBoxTilePos.getyPos() + hitBoxTileHeight;
			for (int xPos = hitBoxTilePos.getxPos(); xPos < hitBoxTilePos.getxPos() + hitBoxTileWidth; xPos++) {
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.LOWER, hitBox, xPos, yPos);
				if (tileCollsion) break;
			}
			
			if (!tileCollsion) {
				// check lower left diag collision
				yPos = hitBoxTilePos.getyPos() + hitBoxTileHeight;
				int xPos = hitBoxTilePos.getxPos() - 1;
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.DIAGLOWERLEFT, hitBox, xPos, yPos);
				
				// check lower right diag collision
				yPos = hitBoxTilePos.getyPos() + hitBoxTileHeight;
				xPos = hitBoxTilePos.getxPos() + hitBoxTileWidth;
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.DIAGLOWERRIGHT, hitBox, xPos, yPos);
			}
			
			// check collision with tiles directly above hitbox
			yPos = hitBoxTilePos.getyPos() - 1;
			for (int xPos = hitBoxTilePos.getxPos(); xPos < hitBoxTilePos.getxPos() + hitBoxTileWidth; xPos++) {
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.UPPER, hitBox, xPos, yPos);
				if (tileCollsion) break;
			}
			
			if (!tileCollsion) {
				// check upper left diag collision
				yPos = hitBoxTilePos.getyPos() - 1;
				int xPos = hitBoxTilePos.getxPos() - 1;
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.DIAGUPPERLEFT, hitBox, xPos, yPos);
				
				// check upper right diag collision
				yPos = hitBoxTilePos.getyPos() - 1;
				xPos = hitBoxTilePos.getxPos() + hitBoxTileWidth;
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.DIAGUPPERRIGHT, hitBox, xPos, yPos);
			}
			
			// check collision with tiles directly underneath hitbox
			int xPos = hitBoxTilePos.getxPos() + hitBoxTileWidth;
			for (yPos = hitBoxTilePos.getyPos(); yPos < hitBoxTilePos.getyPos() + hitBoxTileHeight; yPos++) {
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.RIGHT, hitBox, xPos, yPos);
				if (tileCollsion) break;
			}
			
			// check collision with tiles left of the hitbox
			xPos = hitBoxTilePos.getxPos() - 1;
			for (yPos = hitBoxTilePos.getyPos(); yPos < hitBoxTilePos.getyPos() + hitBoxTileHeight; yPos++) {
				tileCollsion = checkTileCollision(tileCollisions, CollisionType.LEFT, hitBox, xPos, yPos);
				if (tileCollsion) break;
			}

		}
		
		return tileCollisions;
	}
	
	public boolean checkTileCollision(ArrayList<Collision> tileCollisions, CollisionType collisionType, HitBox hitBox, int tilePosX, int tilePosY) {
		boolean returnValue = false;
		
		if (tilePosX >= 0 && tilePosY >= 0) {
			Tile tile = tiles[tilePosX][tilePosY];		
			if (tile != null) {
				for (int tilehbIndex = 0; tilehbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); tilehbIndex++) {
					HitBox tileHitBox = tile.getActiveSpriteContainer().getHitBoxes().get(tilehbIndex);
					if (hitBox.checkCollision(tileHitBox)){
						tileCollisions.add(new Collision(collisionType, hitBox, tile, tileHitBox));
						returnValue = true;
						break;
					}
				}
			}
		}
		
		return returnValue;
	}
	
	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
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
	
	public ArrayList<Mob> getMobs() {
		return mobs;
	}
	
	public void addMob(Mob mob) {
		mobs.add(mob);
	}
}
