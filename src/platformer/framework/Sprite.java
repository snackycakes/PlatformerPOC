package platformer.framework;

import java.awt.Image;

public class Sprite {
	
	private Image spriteImage;
	
	public Sprite(Image spriteImage) {
		super();
		this.spriteImage = spriteImage;
	}

	public Image getSpriteImage() {
		return spriteImage;
	}

	public void setSpriteImage(Image spriteImage) {
		this.spriteImage = spriteImage;
	}
}
