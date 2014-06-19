package platformer.framework;

import java.awt.Image;

public class Sprite implements SpriteAsset {
	
	private Image spriteImage;
	
	public Sprite(Image spriteImage) {
		super();
		this.spriteImage = spriteImage;
	}

	@Override
	public Sprite getSprite() {
		return this;
	}

	public Image getSpriteImage() {
		return spriteImage;
	}

	public void setSpriteImage(Image spriteImage) {
		this.spriteImage = spriteImage;
	}

	@Override
	public void update(long elapsedTime) {		
	}
}
