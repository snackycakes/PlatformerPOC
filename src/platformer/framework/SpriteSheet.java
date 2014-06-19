package platformer.framework;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheetImage;

	public SpriteSheet(BufferedImage sheetImage) {
		this.sheetImage = sheetImage;
	}

	public Image getSheetImage() {
		return sheetImage;
	}

	public void setSheetImage(BufferedImage sheetImage) {
		this.sheetImage = sheetImage;
	}
	
	public Sprite getSprite(int locationX, int locationY, int sizeX, int sizeY) {
		return new Sprite(sheetImage.getSubimage(locationX, locationY, sizeX, sizeY));
	}
}
