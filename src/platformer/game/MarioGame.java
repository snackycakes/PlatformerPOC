package platformer.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformer.framework.Game;
import platformer.framework.HitBox;
import platformer.framework.Layer;
import platformer.framework.Mob;
import platformer.framework.OrderedPair;
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
		playerMario = new Mario(50, 50);
		scene.setPawn(playerMario);
		
		actionLayer = new Layer();
		actionLayer.setDepth(0);
		actionLayer.setGravity(4f);
		actionLayer.setTileSize(new OrderedPair(Assets.TILESIZE, Assets.TILESIZE));
		actionLayer.addMob(playerMario);
		MarioTileLoader.LoadTilesFromFile(actionLayer);	
		
		scene.addLayer(actionLayer);
	}

	@Override
	public void update(long elapsedTime) {
		scene.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics g, ImageObserver observer) {
		boolean displayHitBoxes = false;
		boolean displayTileGrid = false;
		boolean displayMetrics = true;
		
		// TODO:  Only render what is in view
		for (int xIndex = 0; xIndex < Assets.MAXLAYERWIDTH; xIndex++) {
			for (int yIndex = 0; yIndex < Assets.MAXLAYERHEIGHT; yIndex++) {
				// Test code to display tile grid
				if (displayTileGrid) {
					g.setColor(Color.YELLOW);
					g.drawRect((xIndex * Assets.TILESIZE) - scene.getCameraPosition().getPosX(), yIndex * Assets.TILESIZE, Assets.TILESIZE, Assets.TILESIZE);	
				}
				
				Tile tile = actionLayer.getTile(xIndex, yIndex);
				if (tile != null) {
					g.drawImage(tile.getActiveSprite().getSpriteImage(), (xIndex * Assets.TILESIZE) - scene.getCameraPosition().getPosX(), yIndex * Assets.TILESIZE, observer);
					
					// Test code to display hit boxes
					if (displayHitBoxes) {
						for (int hbIndex = 0; hbIndex < tile.getActiveSpriteContainer().getHitBoxes().size(); hbIndex++) {
							HitBox hitBox = tile.getActiveSpriteContainer().getHitBoxes().get(hbIndex);
							g.setColor(Color.YELLOW);
							g.drawRect(hitBox.getPosX() - scene.getCameraPosition().getPosX(), hitBox.getPosY(), hitBox.getSizeX(), hitBox.getSizeY());
						}
					}

				}
			}	
		}
		
		for (int index = 0; index < actionLayer.getMobs().size(); index++) {
			Mob mob = actionLayer.getMobs().get(index);
			g.drawImage(mob.getActiveSprite().getSpriteImage(), playerMario.getPositionX() - scene.getCameraPosition().getPosX(), playerMario.getPositionY(), observer);
			
			// Test code to display hit boxes
			if (displayHitBoxes) {
				for (int hbIndex = 0; hbIndex < mob.getActiveSpriteContainer().getHitBoxes().size(); hbIndex++) {
					HitBox hitBox = mob.getActiveSpriteContainer().getHitBoxes().get(hbIndex);
					g.setColor(Color.YELLOW);
					g.drawRect(hitBox.getPosX() - scene.getCameraPosition().getPosX(), hitBox.getPosY(), hitBox.getSizeX(), hitBox.getSizeY());
				}
			}
		}
		
		if (displayMetrics) {
			
			int xPos = 0;
			int yPos = 5;
			int rowDivide = 5;
			int colDivide = 60;
			
			g.setColor(new Color(200, 200, 200));
			g.setFont(new Font("Dante",1,5));
			
			yPos += rowDivide;
			g.drawString("VelocityX: " + Float.toString(playerMario.getVelocity().getValueX()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("PositionY: " + Float.toString(playerMario.getPositionY()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("Grounded: " + Boolean.toString(playerMario.isGrounded()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("IsJumping: " + Boolean.toString(playerMario.isJumping()), xPos, yPos);
			
			xPos += colDivide;
			yPos = 5;
			
			yPos += rowDivide;
			g.drawString("Gravity: " + Float.toString(actionLayer.getGravity()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("GravityAccl: " + Float.toString(actionLayer.getGravityAccl()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("FrictionGround: " + Float.toString(actionLayer.getFrictionGround()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("FrictionAir: " + Float.toString(actionLayer.getFrictionAir()), xPos, yPos);

			
			xPos += colDivide;
			yPos = 5;
			
			yPos += rowDivide;
			g.drawString("JumpForce: " + Float.toString(playerMario.getJumpForce()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("JumpInitialForce: " + Float.toString(playerMario.getJumpInitialForce()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("JumpAcceleration: " + Float.toString(playerMario.getJumpAcceleration()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("JumpMaxFrames: " + Float.toString(playerMario.getJumpMaxFrames()), xPos, yPos);
			
			xPos += colDivide;
			yPos = 5;
			
			yPos += rowDivide;
			g.drawString("MovementForce: " + Float.toString(playerMario.getMovementForce()), xPos, yPos);
			yPos += rowDivide;
			g.drawString("MovementAccl: " + Float.toString(playerMario.getMovementAccl()), xPos, yPos);
			
		}
	}
	
	public void LoadAssets() {
		SpriteSheet marioSheet = null;
		
		// Mario Sprite Assets
		
		try {
			marioSheet = new SpriteSheet(ImageIO.read(new File("images/smb_mario_sheet.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assets.Sprites.SmallMarioStandingRight = marioSheet.getSprite(210, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingRight1 = marioSheet.getSprite(239, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingRight2 = marioSheet.getSprite(268, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingRight3 = marioSheet.getSprite(299, 0, 16, 16);	
		Assets.Sprites.SmallMarioSlidingRight = marioSheet.getSprite(59, 0, 16, 16);
		Assets.Sprites.SmallMarioJumpingRight = marioSheet.getSprite(360, 0, 16, 16);
		Assets.Sprites.SmallMarioStandingLeft = marioSheet.getSprite(180, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingLeft1 = marioSheet.getSprite(149, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingLeft2 = marioSheet.getSprite(120, 0, 16, 16);
		Assets.Sprites.SmallMarioWalkingLeft3 = marioSheet.getSprite(89, 0, 16, 16);
		Assets.Sprites.SmallMarioSlidingLeft = marioSheet.getSprite(330, 0, 16, 16);
		Assets.Sprites.SmallMarioJumpingLeft = marioSheet.getSprite(29, 0, 16, 16);
		
		marioSheet = null;
		
		// Tile Sprite Assets
		
		SpriteSheet tileSheet = null;
		
		try {
			tileSheet = new SpriteSheet(ImageIO.read(new File("images/smb_tiles.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assets.Sprites.TileBrownFloor = tileSheet.getSprite(385, 144, 16, 16);
		Assets.Sprites.TileBrownFloorHalf = tileSheet.getSprite(385, 144, 16, 8);
		Assets.Sprites.TileBlueFloor = tileSheet.getSprite(405, 144, 16, 16);
		//public static Sprite TileGreyFloor;
		//public static Sprite FlagGreen;
		//public static Sprite FlagBlue;
		//public static Sprite FlagPoleGreen;
		//public static Sprite FlagPoleBlack;
		//public static Sprite FlagPoleRed;
		//public static Sprite FlagPoleGrey;
		//public static Sprite PulleyRed;
		//public static Sprite PulleyGrey;
		//public static Sprite Coin1;
		//public static Sprite Coin2;
		//public static Sprite Coin3;
		//public static Sprite Coin4;
		//public static Sprite Coin5;
		//public static Sprite Coin6;
		//public static Sprite Coin7;
		//public static Sprite Coin8;
		//public static Sprite Coin9;
		//public static Sprite Anchor;
		//public static Sprite Chain;
		//public static Sprite Axe1;
		//public static Sprite Axe2;
		//public static Sprite Axe3;
		//public static Sprite MushroomRed;
		//public static Sprite MushroomGreen;
		//public static Sprite Princess;
		//public static Sprite Toad;
		//public static Sprite Flower1;
		//public static Sprite Flower2;
		//public static Sprite Flower3;
		//public static Sprite Flower4;
		//public static Sprite Flower5;
		//public static Sprite Flower6;
		//public static Sprite Flower7;
		//public static Sprite Flower8;
		//public static Sprite Star1;
		//public static Sprite Star2;
		//public static Sprite Star3;
		//public static Sprite Star4;
		//public static Sprite Star5;
		//public static Sprite CloudLakitu;
		//public static Sprite BlockCoin1;
		//public static Sprite BlockCoin2;
		//public static Sprite BlockCoin3;
		//public static Sprite PlatformLong;
		//public static Sprite PlatformMed;
		//public static Sprite PlatformShort;
		//public static Sprite Beanstalk;
		//public static Sprite BowserFlame1;
		//public static Sprite BowserFlame2;
		//public static Sprite Fireball;
		//public static Sprite SpringLarge;
		//public static Sprite SpringMed;
		Assets.Sprites.HillSmall = tileSheet.getSprite(260, 18, 50, 21);
		Assets.Sprites.HillLarge = tileSheet.getSprite(313, 3, 83, 36);	
		//public static Sprite HillSmall;
		Assets.Sprites.CloudBlueSmall = tileSheet.getSprite(260, 18, 50, 21);
		Assets.Sprites.CloudBlueLarge = tileSheet.getSprite(313, 3, 83, 36);
		//public static Sprite CloudRedSmall;
		//public static Sprite CloudRedLarge;
		//public static Sprite BushSmall;
		Assets.Sprites.BushLarge = tileSheet.getSprite(307, 109, 78, 20);
		//public static Sprite TreeGreenLarge;
		//public static Sprite TreeGreenSmall;
		//public static Sprite TreeWhiteLarge;
		//public static Sprite TreeWhiteSmall;
		//public static Sprite FireSmall;
		//public static Sprite FireMed;
		//public static Sprite FireLarge;
		Assets.Sprites.ItemBlock1 = tileSheet.getSprite(385, 45, 16, 16);
		//public static Sprite ItemBlock2;
		Assets.Sprites.ItemBlock3 = tileSheet.getSprite(427, 45, 16, 16);
		//public static Sprite ItemBlockEmpty;
		Assets.Sprites.BrownBrick = tileSheet.getSprite(469, 45, 16, 16);
		//public static Sprite BrownBrickPiece;
		//public static Sprite UGItemBlock1;
		//public static Sprite UGItemBlock2;
		//public static Sprite UGItemBlock3;
		//public static Sprite UGItemBlockEmpty;
		//public static Sprite UGBrick;
		//public static Sprite UGBrickPiece;
		//public static Sprite CastleItemBlock1;
		//public static Sprite CastleItemBlock2;
		//public static Sprite CastleItemBlock3;
		//public static Sprite CastleItemBlockEmpty;
		//public static Sprite CastleBrick;
		//public static Sprite CastleBrickPiece;
		//....
		//Add missing Sprites
		//....
		Assets.Sprites.GreenPipe = tileSheet.getSprite(117, 228, 28, 28);
		Assets.Sprites.GreenPipeTop = tileSheet.getSprite(76, 208, 32, 48);
		//public static Sprite GreenPipeIntersect;
		//public static Sprite GreyPipe;
		//public static Sprite GreyPipeTop;
		//public static Sprite DarkGreyPipe;
		//public static Sprite DarkGreyPipeTop;
		//public static Sprite PurplePipe;
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				break;
	
			case KeyEvent.VK_DOWN:
				break;
	
			case KeyEvent.VK_LEFT:
				playerMario.setMovingLeft(true);
				break;
	
			case KeyEvent.VK_RIGHT:
				playerMario.setMovingRight(true);
				break;
	
			case KeyEvent.VK_SPACE:
	      			playerMario.setJumping(true);
				break;
	
			case KeyEvent.VK_CONTROL:
				break;
			
			case KeyEvent.VK_G:
				if (e.isShiftDown())
					actionLayer.setGravity(actionLayer.getGravity() - .1f);
				else
					actionLayer.setGravity(actionLayer.getGravity() + .1f);
				break;
				
			case KeyEvent.VK_H:
				if (e.isShiftDown())
					actionLayer.setGravityAccl(actionLayer.getGravityAccl() - .01f);
				else
					actionLayer.setGravityAccl(actionLayer.getGravityAccl() + .01f);
				break;
				
			case KeyEvent.VK_F:
				if (e.isShiftDown())
					actionLayer.setFrictionGround(actionLayer.getFrictionGround() - .01f);
				else
					actionLayer.setFrictionGround(actionLayer.getFrictionGround() + .01f);
				break;
				
			case KeyEvent.VK_A:
				if (e.isShiftDown())
					actionLayer.setFrictionAir(actionLayer.getFrictionAir() - .01f);
				else
					actionLayer.setFrictionAir(actionLayer.getFrictionAir() + .01f);
				break;
				
			case KeyEvent.VK_J:
				if (e.isShiftDown())
					playerMario.setJumpForce(playerMario.getJumpForce() - .1f);
				else
					playerMario.setJumpForce(playerMario.getJumpForce() + .1f);
				break;
				
			case KeyEvent.VK_U:
				if (e.isShiftDown())
					playerMario.setJumpInitialForce(playerMario.getJumpInitialForce() - .1f);
				else
					playerMario.setJumpInitialForce(playerMario.getJumpInitialForce() + .1f);
				break;
				
			case KeyEvent.VK_K:
				if (e.isShiftDown())
					playerMario.setJumpAcceleration(playerMario.getJumpAcceleration() - .1f);
				else
					playerMario.setJumpAcceleration(playerMario.getJumpAcceleration() + .1f);
				break;
				
			case KeyEvent.VK_L:
				if (e.isShiftDown())
					playerMario.setJumpMaxFrames(playerMario.getJumpMaxFrames() - 1f);
				else
					playerMario.setJumpMaxFrames(playerMario.getJumpMaxFrames() + 1f);
				break;
				
			case KeyEvent.VK_M:
				if (e.isShiftDown())
					playerMario.setMovementForce(playerMario.getMovementForce() - 1f);
				else
					playerMario.setMovementForce(playerMario.getMovementForce() + 1f);
				break;
				
			case KeyEvent.VK_N:
				if (e.isShiftDown())
					playerMario.setMovementAccl(playerMario.getMovementAccl() - 1f);
				else
					playerMario.setMovementAccl(playerMario.getMovementAccl() + 1f);
				break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;

		case KeyEvent.VK_DOWN:
			break;

		case KeyEvent.VK_LEFT:
			playerMario.setMovingLeft(false);
			break;

		case KeyEvent.VK_RIGHT:
			playerMario.setMovingRight(false);
			break;

		case KeyEvent.VK_SPACE:
			playerMario.setJumping(false);
			break;
			
		case KeyEvent.VK_CONTROL:
			break;
		}
	}
}
