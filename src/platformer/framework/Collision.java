package platformer.framework;
import platformer.framework.Collision.CollisionType;

public class Collision {
	public HitBox getActiveHitBox() {
		return activeHitBox;
	}

	public void setActiveHitBox(HitBox activeHitBox) {
		this.activeHitBox = activeHitBox;
	}

	public Node getCollisionNode() {
		return collisionNode;
	}

	public void setCollisionNode(Node collisionNode) {
		this.collisionNode = collisionNode;
	}

	public HitBox getCollisionHitBox() {
		return collisionHitBox;
	}

	public void setCollisionHitBox(HitBox collisionHitBox) {
		this.collisionHitBox = collisionHitBox;
	}

	public enum CollisionType {LOWER, UPPER, LEFT, RIGHT, DIAGLOWERLEFT, DIAGLOWERRIGHT, DIAGUPPERLEFT, DIAGUPPERRIGHT}
	protected CollisionType collisionType;
	protected HitBox activeHitBox;
	protected Node collisionNode;
	protected HitBox collisionHitBox;
	
	public Collision(CollisionType collisionType) {
		this.collisionType = collisionType;
	}
	
	public Collision(CollisionType collisionType, HitBox activeHitBox, Node collisionNode, HitBox collisionHitBox) {
		this.collisionType = collisionType;
		this.collisionNode = collisionNode;
		this.activeHitBox = activeHitBox;
		this.collisionHitBox = collisionHitBox;
	}

	public CollisionType getCollisionType() {
		return collisionType;
	}
	
	public void setCollisionType(CollisionType collisionType) {
		this.collisionType = collisionType;
	}
}
