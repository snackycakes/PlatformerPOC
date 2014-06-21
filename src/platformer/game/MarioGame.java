package platformer.game;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformer.framework.AnimatedSprite;
import platformer.framework.Game;
import platformer.framework.SpriteSheet;
import platformer.framework.Tile;

public class MarioGame extends Game {
	private Mario playerMario;
	private final int MAXLAYERWIDTH = 100;
	private final int MAXLAYERHEIGHT = 20;
	private final int TILESIZE = 16;
	
	public MarioLevel level;
	public MarioActionLayer actionLayer;
	

	@Override
	public void init() {			
		LoadAssets();
		
		level = new MarioLevel();
		actionLayer = new MarioActionLayer();
		actionLayer.setDepth(0);
		
		for (int xCount = 0; xCount < 50; xCount++) {
			actionLayer.setTile(new MarioTile(Assets.Sprites.TileBrownFloor), xCount, 14);	
		}
		
		playerMario = new Mario(50, 50);
		actionLayer.addPawn(playerMario);
	}

	@Override
	public void update(long elapsedTime) {
		playerMario.setPosition(playerMario.getCoordX() + 1f, playerMario.getCoordY());
		playerMario.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics g, ImageObserver observer) {
		
		for (int index = 0; index < actionLayer.getPawns().size(); index++) {
			g.drawImage(actionLayer.getPawns().get(index).getActiveSpriteAsset().getSprite().getSpriteImage(), playerMario.getPositionX(), playerMario.getPositionY(), observer);
		}
		
		// TODO:  Only render what is in view
		for (int xIndex = 0; xIndex < MAXLAYERWIDTH; xIndex++) {
			for (int yIndex = 0; yIndex < MAXLAYERHEIGHT; yIndex++) {
				Tile tile = actionLayer.getTile(xIndex, yIndex);
				if (tile != null) {
					g.drawImage(tile.getActiveSpriteAsset().getSprite().getSpriteImage(), xIndex * TILESIZE, yIndex * TILESIZE, observer);
				}
			}	
		}
	}
	
	public void LoadAssets() {
		SpriteSheet marioSheet = null;
		
		try {
			marioSheet = new SpriteSheet(ImageIO.read(new File("images/smb_mario_sheet.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assets.Sprites.SmallMarioStandingRight = marioSheet.getSprite(210, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingRight1 = marioSheet.getSprite(239, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingRight2 = marioSheet.getSprite(268, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingRight3 = marioSheet.getSprite(299, 0, 16, 16);		
		Assets.Sprites.SmallMarioStandingLeft = marioSheet.getSprite(180, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingLeft1 = marioSheet.getSprite(149, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingLeft2 = marioSheet.getSprite(120, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingLeft3 = marioSheet.getSprite(89, 0, 16, 16);
		
		marioSheet = null;
		
		SpriteSheet tileSheet = null;
		
		try {
			tileSheet = new SpriteSheet(ImageIO.read(new File("images/smb_tiles.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assets.Sprites.TileBrownFloor = tileSheet.getSprite(385, 144, 16, 16);
	}
}
