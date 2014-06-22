package platformer.framework;

import java.awt.Image;

public class StaticSprite extends SpriteContainer {
	private Sprite sprite;
	
	public StaticSprite(Sprite sprite, Position parentPosition) {
		super();
		this.sprite = sprite;
	}

	public StaticSprite(Image spriteImage, Position parentPosition) {
		super();
		sprite.setSpriteImage(spriteImage);
	}
	
	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void update(long elapsedTime, Position parentPosition) {
		super.update(elapsedTime, parentPosition);
	}

	public Image getSpriteImage() {
		return sprite.getSpriteImage();
	}

	public void setSpriteImage(Image spriteImage) {
		sprite.setSpriteImage(spriteImage);
	}
}
