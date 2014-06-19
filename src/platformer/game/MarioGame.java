package platformer.game;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import platformer.framework.AnimatedSprite;
import platformer.framework.Game;
import platformer.framework.SpriteSheet;

public class MarioGame extends Game {
	private SpriteSheet marioSheet;
	private Pawn pawn;

	@Override
	public void init() {		
		try {
			marioSheet = new SpriteSheet(ImageIO.read(new File("data/smb_mario_sheet.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Assets.Sprites.SmallMarioStandingRight = marioSheet.getSprite(211, 0, 13, 16);
		Assets.Sprites.SmallMarioWalkingRight1 = marioSheet.getSprite(241, 0, 13, 16);
		Assets.Sprites.SmallMarioWalkingRight2 = marioSheet.getSprite(272, 0, 13, 16);
		Assets.Sprites.SmallMarioWalkingRight3 = marioSheet.getSprite(300, 0, 13, 16);		
		Assets.Sprites.SmallMarioStandingLeft = marioSheet.getSprite(181, 0, 13, 16);
		Assets.Sprites.SmallMarioWalkingLeft1 = marioSheet.getSprite(150, 0, 13, 16);
		Assets.Sprites.SmallMarioWalkingLeft2 = marioSheet.getSprite(121, 0, 13, 16);
		Assets.Sprites.SmallMarioWalkingLeft3 = marioSheet.getSprite(89, 0, 13, 16);

		AnimatedSprite marioWalking = new AnimatedSprite();
		marioWalking.addFrame(Assets.Sprites.SmallMarioWalkingRight1, 100);
		marioWalking.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		marioWalking.addFrame(Assets.Sprites.SmallMarioWalkingRight3, 100);
		marioWalking.addFrame(Assets.Sprites.SmallMarioWalkingRight2, 100);
		
		pawn = new Pawn();
		pawn.addSpriteAsset(MarioMob.SpriteAssetTypes.DEFAULT.getValue(), marioWalking);
	}

	@Override
	public void render(Graphics g, ImageObserver observer) {
		g.drawImage(pawn.GetSpriteAsset().getSprite().getSpriteImage(), 0, 0, observer);
	}

	@Override
	public void update(long elapsedTime) {
		pawn.update(elapsedTime);
	}
}
