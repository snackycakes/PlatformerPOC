package platformer.framework;

public abstract class Collision {
	public enum CollisionType {LOWER, UPPER, LEFT, RIGHT, DIAGLOWERLEFT, DIAGLOWERRIGHT, DIAGUPPERLEFT, DIAGUPPERRIGHT}
	protected CollisionType collisionType;
	
	public Collision(CollisionType collisionType) {
		this.collisionType = collisionType;
	}

	public CollisionType getCollisionType() {
		return collisionType;
	}
	
	public void setCollisionType(CollisionType collisionType) {
		this.collisionType = collisionType;
	}	
}
