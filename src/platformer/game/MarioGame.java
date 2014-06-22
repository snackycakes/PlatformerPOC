package platformer.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformer.framework.AnimatedSprite;
import platformer.framework.Game;
import platformer.framework.HitBox;
import platformer.framework.Layer;
import platformer.framework.Pawn;
import platformer.framework.Scene;
import platformer.framework.SpriteSheet;
import platformer.framework.Tile;

public class MarioGame extends Game {
	private Mario playerMario;
	
	public Scene scene;
	public Layer actionLayer;
	

	@Override
	public void init() {			
		LoadAssets();
		
		scene = new Scene();
		actionLayer = new Layer();
		actionLayer.setDepth(0);
		
		for (int xCount = 0; xCount < 50; xCount++) {
			actionLayer.setTile(new MarioTile(Assets.Sprites.TileBrownFloor, xCount * Assets.TILESIZE, 14 * Assets.TILESIZE), xCount, 14);	
		}
		
		playerMario = new Mario(50, 50);
		//playerMario.setVelocity(1f, 1f);
		
		actionLayer.addPawn(playerMario);
		
		scene.addLayer(actionLayer);
	}

	@Override
	public void update(long elapsedTime) {
		scene.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics g, ImageObserver observer) {
		
		// TODO:  Only render what is in view
		for (int xIndex = 0; xIndex < Assets.MAXLAYERWIDTH; xIndex++) {
			for (int yIndex = 0; yIndex < Assets.MAXLAYERHEIGHT; yIndex++) {
				Tile tile = actionLayer.getTile(xIndex, yIndex);
				if (tile != null) {
					g.drawImage(tile.getActiveSprite().getSpriteImage(), xIndex * Assets.TILESIZE, yIndex * Assets.TILESIZE, observer);
					
					/*
					// Test code to display hit boxes
					for (int hbIndex = 0; hbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); hbIndex++) {
						HitBox hitBox = tile.getActiveSpriteContainer().getHitBoxes().get(hbIndex);
						g.setColor(Color.YELLOW);
						g.drawRect(hitBox.getxPos(), hitBox.getyPos(), hitBox.getSizeX(), hitBox.getSizeY());
					}
					*/
				}
			}	
		}
		
		for (int index = 0; index < actionLayer.getPawns().size(); index++) {
			Pawn pawn = actionLayer.getPawns().get(index);
			g.drawImage(pawn.getActiveSprite().getSpriteImage(), playerMario.getPositionX(), playerMario.getPositionY(), observer);
			
			/*
			// Test code to display hit boxes
			for (int hbIndex = 0; hbIndex < pawn.getActiveSpriteContainer().getHitBoxes().size(); hbIndex++) {
				HitBox hitBox = pawn.getActiveSpriteContainer().getHitBoxes().get(hbIndex);
				g.setColor(Color.YELLOW);
				g.drawRect(hitBox.getxPos(), hitBox.getyPos(), hitBox.getSizeX(), hitBox.getSizeY());
			}
			*/
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
