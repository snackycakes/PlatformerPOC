package platformer.framework;

public interface HitArea {
	public boolean checkCollision (HitArea hitArea);
	public void update(Position parentPosition);
}
