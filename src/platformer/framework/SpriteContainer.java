package platformer.framework;

import java.util.ArrayList;

public abstract class SpriteContainer {
	protected ArrayList<HitBox> hitBoxes = new ArrayList<HitBox>();
	
	public abstract Sprite getSprite();
	
	public void update(long elapsedTime, Position parentPosition) {
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
	
}
