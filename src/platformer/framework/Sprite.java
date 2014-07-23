package platformer.framework;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	
	private Image spriteImage;
	
	public Sprite() {
		this.spriteImage = new BufferedImage(0, 0, 0);
	}
	
	public Sprite(Image spriteImage) {
		this.spriteImage = spriteImage;
	}

	public Image getSpriteImage() {
		return spriteImage;
	}

	public void setSpriteImage(Image spriteImage) {
		this.spriteImage = spriteImage;
	}
}
