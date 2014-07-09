package platformer.framework;

import java.util.ArrayList;

import platformer.framework.Collision.CollisionType;

public class Layer {
	protected int depth;
	
	protected ArrayList<Mob> mobs = new ArrayList<Mob>();
	protected Tile tiles[][] = new Tile[200][200];
	protected OrderedPair tileSize = new OrderedPair(0, 0);
	protected float gravity = 4f;
	protected float gravityAccl = 0.3f;
	protected float frictionGround = 0.06f;
	protected float frictionAir = 0.00f;
	
	public void update(long elapsedTime) {			
		for (int mobIndex = 0; mobIndex < mobs.size(); mobIndex++) {
			updateMob(mobs.get(mobIndex), elapsedTime);
		}
	}

	public void updateMob(Mob mob, long elapsedTime) {
		// gravity logic
		mob.applyForce("gravity", new Force(0, gravity, 0, gravityAccl));
		float friction = 0;
		if (mob.isGrounded()) {
			friction = frictionGround;
		} else {
			friction = frictionAir;
		}
		
		//friction logic
		if (mob.getVelocity().getValueX() >= 0f) {
			if (mob.getVelocity().getValueX() - friction >= 0f) {
				mob.applyForce("friction", new Force(-friction, 0, -friction, 0));
			} else {
				mob.applyForce("friction", new Force(-mob.getVelocity().getValueX(), 0, -mob.getVelocity().getValueX(), 0));
				//mob.getVelocity().setValueX(0f);
			}
		} else{
			if (mob.getVelocity().getValueX() + friction <= 0f) {
				mob.applyForce("friction", new Force(friction, 0, friction, 0));
			} else {
				mob.applyForce("friction", new Force(-mob.getVelocity().getValueX(), 0, -mob.getVelocity().getValueX(), 0));
				//mob.getVelocity().setValueX(0f);
			}
		}
		
		mob.update(elapsedTime);
		
		// check and resolve tile collisions
		checkTileCollisions(mob);
		
		mob.commitDesiredPosition();
	}
	
	public float getFrictionGround() {
		return frictionGround;
	}

	public void setFrictionGround(float frictionGround) {
		this.frictionGround = frictionGround;
	}

	public float getFrictionAir() {
		return frictionAir;
	}

	public void setFrictionAir(float frictionAir) {
		this.frictionAir = frictionAir;
	}

	public OrderedPair getTileArrayPosition(HitBox hitBox) {
		int xPos = ((hitBox.getPosX() + hitBox.sizeX / 2)) / tileSize.getPosX();
		int yPos = ((hitBox.getPosY() + hitBox.sizeY / 2)) / tileSize.getPosY();
		return new OrderedPair(xPos, yPos);
	}
	
	public ArrayList<Collision> checkTileCollisions(Mob mob) {
		ArrayList<Collision> tileCollisions = new ArrayList<Collision>();
		
		for (int hbIndex = 0; hbIndex < mob.getActiveSpriteContainer().getHitBoxes().size(); hbIndex++) {
			int xPos, yPos;
			HitBox hitBox = mob.getActiveSpriteContainer().getHitBoxes().get(hbIndex);
			OrderedPair hitBoxTilePos =  getTileArrayPosition(hitBox);
			int hitBoxTileWidth = (int)Math.ceil((double)hitBox.getSizeX() / tileSize.getPosX());
			int hitBoxTileHeight = (int)Math.ceil((double)hitBox.getSizeY() / tileSize.getPosY());
			boolean tileCollision = false;
			
			// check collision with tiles directly above hitbox
			yPos = hitBoxTilePos.getPosY() - 1;
			for (xPos = hitBoxTilePos.getPosX(); xPos < hitBoxTilePos.getPosX() + hitBoxTileWidth; xPos++) {
				tileCollision = resolveCollision(tileCollisions, CollisionType.UPPER, mob, hitBox, xPos, yPos);
				if (tileCollision) break;
			}
			
			// check collision with tiles to the right of the hitbox
			xPos = hitBoxTilePos.getPosX() + hitBoxTileWidth;
			for (yPos = hitBoxTilePos.getPosY(); yPos < hitBoxTilePos.getPosY() + hitBoxTileHeight; yPos++) {
				tileCollision = resolveCollision(tileCollisions, CollisionType.RIGHT, mob, hitBox, xPos, yPos);
				if (tileCollision) break;
			}
			
			// check collision with tiles left of the hitbox
			xPos = hitBoxTilePos.getPosX() - 1;
			for (yPos = hitBoxTilePos.getPosY(); yPos < hitBoxTilePos.getPosY() + hitBoxTileHeight; yPos++) {
				tileCollision = resolveCollision(tileCollisions, CollisionType.LEFT, mob, hitBox, xPos, yPos);
				if (tileCollision) break;
			}
			
			// check lower left diag collision
			yPos = hitBoxTilePos.getPosY() + hitBoxTileHeight;
			xPos = hitBoxTilePos.getPosX() - 1;
			tileCollision = resolveCollision(tileCollisions, CollisionType.DIAGLOWERLEFT, mob, hitBox, xPos, yPos);
			
			// check lower right diag collision
			yPos = hitBoxTilePos.getPosY() + hitBoxTileHeight;
			xPos = hitBoxTilePos.getPosX() + hitBoxTileWidth;
			tileCollision = resolveCollision(tileCollisions, CollisionType.DIAGLOWERRIGHT, mob, hitBox, xPos, yPos);
			
			// check upper left diag collision
			yPos = hitBoxTilePos.getPosY() - 1;
			xPos = hitBoxTilePos.getPosX() - 1;
			tileCollision = resolveCollision(tileCollisions, CollisionType.DIAGUPPERLEFT, mob, hitBox, xPos, yPos);
			
			// check upper right diag collision
			yPos = hitBoxTilePos.getPosY() - 1;
			xPos = hitBoxTilePos.getPosX() + hitBoxTileWidth;
			tileCollision = resolveCollision(tileCollisions, CollisionType.DIAGUPPERRIGHT, mob, hitBox, xPos, yPos);
			
			// check collision with tiles directly underneath hitbox
			yPos = hitBoxTilePos.getPosY() + hitBoxTileHeight;
			for (xPos = hitBoxTilePos.getPosX(); xPos < hitBoxTilePos.getPosX() + hitBoxTileWidth; xPos++) {
				tileCollision = resolveCollision(tileCollisions, CollisionType.LOWER, mob, hitBox, xPos, yPos);
				if (tileCollision) break;
			}
		}
		
		boolean isMobGrounded = false;		
		for (Collision tileCollision : tileCollisions) {
			if (tileCollision.getCollisionNode().isStopsMovement() && tileCollision.collisionType == CollisionType.LOWER) {
				isMobGrounded = true;
				break;
			}
		}
		mob.setGrounded(isMobGrounded);		
		
		return tileCollisions;
	}
	
	public boolean resolveCollision(ArrayList<Collision> tileCollisions, CollisionType collisionType, Mob mob, HitBox hitBox, int tilePosX, int tilePosY) {
		boolean returnValue = false;
		Collision tileCollision = null;
		
		if (tilePosX >= 0 && tilePosY >= 0) {
			Tile tile = tiles[tilePosX][tilePosY];		
			if (tile != null) {
				for (HitBox tileHitBox : tile.getActiveSpriteContainer().getHitBoxes()) {
					// resolve a lower diagonal collision to either left, right, up, or down.
					if ((collisionType == CollisionType.DIAGLOWERLEFT || collisionType == CollisionType.DIAGLOWERRIGHT || collisionType == CollisionType.DIAGUPPERLEFT || collisionType == CollisionType.DIAGUPPERRIGHT) && mob.prevSpriteContainer != null) {
						// check previous hitbox location to determine desired collision for this update						
						for (HitBox prevHitBox : mob.prevSpriteContainer.getHitBoxes()) {
							if (collisionType == CollisionType.DIAGLOWERLEFT || collisionType == CollisionType.DIAGLOWERRIGHT) {
								if ((prevHitBox.getPosition().getPosY() + prevHitBox.getSizeY() > tileHitBox.getPosY())) {
									if (collisionType == CollisionType.DIAGLOWERLEFT) {
										collisionType = CollisionType.LEFT;
									} else {
										collisionType = CollisionType.RIGHT;
									}
								} else {
									collisionType = CollisionType.LOWER;
								}
							} else {
								if (prevHitBox.getPosition().getPosY() < tileHitBox.getPosY() + tileHitBox.getSizeY()) {
									if (collisionType == CollisionType.DIAGUPPERLEFT) {
										collisionType = CollisionType.LEFT;
									} else {
										collisionType = CollisionType.RIGHT;
									}
								} else {
									collisionType = CollisionType.UPPER;
								}
							}
						}
					}
					
					if (hitBox.checkCollision(tileHitBox)) {
						tileCollision = new Collision(collisionType, hitBox, tile, tileHitBox);
						tileCollisions.add(tileCollision);
						returnValue = true;
						break;
					}
				}
			}
		}
		
		if (tileCollision != null) {
			
			switch (tileCollision.getCollisionType()) {
				case LOWER:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionY(mob.getDesiredPositionY() - ((tileCollision.getActiveHitBox().getPosY() + tileCollision.getActiveHitBox().getSizeY()) - tileCollision.getCollisionHitBox().getPosY()));
					}
					break;
				case UPPER:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionY(mob.getDesiredPositionY() + ((tileCollision.getCollisionHitBox().getPosY() + tileCollision.getCollisionHitBox().getSizeY()) - tileCollision.getActiveHitBox().getPosY()));
						mob.setVelocityY(0);
					}
					break;
				case LEFT:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionX(mob.getDesiredPositionX() + ((tileCollision.getCollisionHitBox().getPosX() + tileCollision.getCollisionHitBox().getSizeX()) - tileCollision.getActiveHitBox().getPosX()));
						mob.setVelocityX(0);
					}
					break;
				case RIGHT:
					if (tileCollision.getCollisionNode().isStopsMovement()) {
						mob.setDesiredPositionX(mob.getDesiredPositionX() - ((tileCollision.getActiveHitBox().getPosX() + tileCollision.getActiveHitBox().getSizeX()) - tileCollision.getCollisionHitBox().getPosX()));
						mob.setVelocityX(0);
					}
					break;
				default:
					break;
			}
			
			mob.getActiveSpriteContainer().updateHitBoxes(mob.getDesiredPosition());
			mob.collisionUpdate(tileCollision);
		}
		
		return returnValue;
	}
	
	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	
	public OrderedPair getTileSize() {
		return tileSize;
	}

	public void setTileSize(OrderedPair tileSize) {
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
	
	public float getGravityAccl() {
		return gravityAccl;
	}

	public void setGravityAccl(float gravityAccl) {
		this.gravityAccl = gravityAccl;
	}
}
