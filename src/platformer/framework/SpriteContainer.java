package platformer.framework;

import java.util.ArrayList;

public class SpriteContainer {
	protected ArrayList<HitBox> hitBoxes = new ArrayList<HitBox>();
	
	public Sprite getSprite() {return null;}
	
	public void update(long elapsedTime, OrderedPair parentPosition) {
		updateHitBoxes(parentPosition);
	}
	
	public void updateHitBoxes(OrderedPair parentPosition) {
		for (int index = 0; index < hitBoxes.size(); index++) {
			HitBox hitBox = hitBoxes.get(index);
			if (hitBox != null) {
				hitBox.update(parentPosition);
			}
		}
	}

	public ArrayList<HitBox> getHitBoxes() {
		return hitBoxes;
	}

	public void addHitBox(HitBox hitBox) {
		hitBoxes.add(hitBox);
	}
	
	public void copy(SpriteContainer spriteContainer) {
		this.hitBoxes = new ArrayList<HitBox>();
		for (HitBox hitBox : spriteContainer.hitBoxes) {
			OrderedPair hitBoxPosition = new OrderedPair();
			hitBoxPosition.copy(hitBox.getPosition());
			hitBoxes.add(new HitBox(hitBoxPosition, hitBox.sizeX, hitBox.sizeY));
		}
	}
}
