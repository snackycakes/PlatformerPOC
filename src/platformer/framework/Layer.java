package platformer.framework;

import java.util.ArrayList;

import platformer.framework.TileContainer.PositionType;

public class Layer implements NodeEventListener {
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
		resolveTileCollisionsNew(mob);
		
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

	public OrderedPair getTileIndexe(HitBox hitBox) {
		int xPos = ((hitBox.getPosX() + hitBox.sizeX / 2)) / tileSize.getPosX();
		int yPos = ((hitBox.getPosY() + hitBox.sizeY / 2)) / tileSize.getPosY();
		return new OrderedPair(xPos, yPos);
	}
	
	public ArrayList<TileContainer> getSurroundingTiles (Mob mob) {
		ArrayList<TileContainer> returnValue = new ArrayList<TileContainer>();
		
		for (HitBox hitBox : mob.getActiveSpriteContainer().getHitBoxes()) {
			OrderedPair hitBoxTileIndex =  getTileIndexe(hitBox);
			int hitBoxTileWidth = (int)Math.ceil((double)hitBox.getSizeX() / tileSize.getPosX());
			int hitBoxTileHeight = (int)Math.ceil((double)hitBox.getSizeY() / tileSize.getPosY());
			int xPos, yPos;		
			
			yPos = hitBoxTileIndex.getPosY() + hitBoxTileHeight;
			for (xPos = hitBoxTileIndex.getPosX(); xPos < hitBoxTileIndex.getPosX() + hitBoxTileWidth; xPos++) {
				if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.LOWER, hitBox, tiles[xPos][yPos])); }
			}
			yPos = hitBoxTileIndex.getPosY() - 1;
			for (xPos = hitBoxTileIndex.getPosX(); xPos < hitBoxTileIndex.getPosX() + hitBoxTileWidth; xPos++) {
				if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.UPPER, hitBox, tiles[xPos][yPos])); }
			}
			xPos = hitBoxTileIndex.getPosX() - 1;
			for (yPos = hitBoxTileIndex.getPosY(); yPos < hitBoxTileIndex.getPosY() + hitBoxTileHeight; yPos++) {
				if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.LEFT, hitBox, tiles[xPos][yPos])); }
			}
			xPos = hitBoxTileIndex.getPosX() + hitBoxTileWidth;
			for (yPos = hitBoxTileIndex.getPosY(); yPos < hitBoxTileIndex.getPosY() + hitBoxTileHeight; yPos++) {
				if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.RIGHT, hitBox, tiles[xPos][yPos])); }
			}
			
			yPos = hitBoxTileIndex.getPosY() - 1;
			xPos = hitBoxTileIndex.getPosX() - 1;
			if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.DIAGUPPERLEFT, hitBox, tiles[xPos][yPos])); }
			
			yPos = hitBoxTileIndex.getPosY() - 1;
			xPos = hitBoxTileIndex.getPosX() + hitBoxTileWidth;
			if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.DIAGUPPERRIGHT, hitBox, tiles[xPos][yPos])); }
			
			yPos = hitBoxTileIndex.getPosY() + hitBoxTileHeight;
			xPos = hitBoxTileIndex.getPosX() - 1;
			if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.DIAGLOWERLEFT, hitBox, tiles[xPos][yPos])); }
			
			yPos = hitBoxTileIndex.getPosY() + hitBoxTileHeight;
			xPos = hitBoxTileIndex.getPosX() + hitBoxTileWidth;
			if (xPos >= 0 && yPos >= 0 && tiles[xPos][yPos] != null) { returnValue.add(new TileContainer(PositionType.DIAGLOWERRIGHT, hitBox, tiles[xPos][yPos])); }
		}
		
		return returnValue;
	}
	
	public void resolveTileCollisionsNew(Mob mob) {
		ArrayList<TileContainer> surroundingTiles = getSurroundingTiles(mob);
		boolean isMobGrounded = false;
		
		for (TileContainer tileContainer : surroundingTiles) {		
			for (HitBox tileHitBox : tileContainer.getTile().getActiveSpriteContainer().getHitBoxes()) {
				PositionType resolvePosition;
				
				OrderedPair collisionDepth = null;
				if (tileContainer.getTile().isStopsMovement()) {
					collisionDepth = tileHitBox.checkCollision(tileContainer.getRelativeHitBox());
				}
				
				if (collisionDepth != null) {
					resolvePosition = tileContainer.getRelativePosition();
					
					// resolve a diagonal collision to either left, right, up, or down.
					if ((resolvePosition == PositionType.DIAGLOWERLEFT || resolvePosition == PositionType.DIAGLOWERRIGHT || resolvePosition == PositionType.DIAGUPPERLEFT || resolvePosition == PositionType.DIAGUPPERRIGHT)) {
						// check previous hit box location to determine desired collision for this update						
						for (HitBox prevHitBox : mob.prevSpriteContainer.getHitBoxes()) {
							if (resolvePosition == PositionType.DIAGLOWERLEFT || resolvePosition == PositionType.DIAGLOWERRIGHT) {
								if ((prevHitBox.getPosition().getPosY() + prevHitBox.getSizeY() > tileHitBox.getPosY())) {
									if (resolvePosition == PositionType.DIAGLOWERLEFT) {
										resolvePosition = PositionType.LEFT;
									} else {
										resolvePosition = PositionType.RIGHT;
									}
								} else {
									resolvePosition = PositionType.LOWER;
								}
							} else {
								if (collisionDepth.getPosX() <= collisionDepth.getPosY()) {
									if (resolvePosition == PositionType.DIAGUPPERLEFT) {
										resolvePosition = PositionType.LEFT;
									} else {
										resolvePosition = PositionType.RIGHT;
									}
								} else {
									resolvePosition = PositionType.UPPER;
								}
							}
						}
					}
					
					switch (resolvePosition) {
						case LOWER:
							mob.setDesiredPositionY(mob.getDesiredPositionY() - collisionDepth.getPosY());
							isMobGrounded = true;
							break;
						case UPPER:
							mob.setDesiredPositionY(mob.getDesiredPositionY() + collisionDepth.getPosY());
							mob.setVelocityY(0);						
							if (mob.isPawn() && tileContainer.getTile() != null && tileContainer.getTile().isDestructible()) {
								tileContainer.getTile().destroyNode();
							}
							break;
						case LEFT:
							mob.setDesiredPositionX(mob.getDesiredPositionX() + collisionDepth.getPosX());
							mob.setVelocityX(0);
							break;
						case RIGHT:
							mob.setDesiredPositionX(mob.getDesiredPositionX() - collisionDepth.getPosX());
							mob.setVelocityX(0);
							break;
						default:
							break;					
					}
					
					mob.getActiveSpriteContainer().updateHitBoxes(mob.getDesiredPosition());
					mob.collisionUpdate(tileContainer);
				}
			}
		}
		
		mob.setGrounded(isMobGrounded);
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
		tile.setNodeEventListener(this);
		tiles[xPos][yPos] = tile;
	}
	
	public Tile getTile(int xPos, int yPos) {
		return tiles[xPos][yPos];
	}
	
	public ArrayList<Mob> getMobs() {
		return mobs;
	}
	
	public void addMob(Mob mob) {
		mob.setNodeEventListener(this);
		mobs.add(mob);
	}
	
	public float getGravityAccl() {
		return gravityAccl;
	}

	public void setGravityAccl(float gravityAccl) {
		this.gravityAccl = gravityAccl;
	}

	@Override
	public void SpawnMob(Mob mob) {
		addMob(mob);
	}
}
