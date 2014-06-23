package platformer.game;

import platformer.framework.Tile;

public class MarioTileLoader {
	public static void LoadTilesFromFile(Tile[][] outputArray) {
		/* Instructions to complete this method:
		 * 1.  Go into the MarioGame.Init() and Assets.java file and add sprite definitions for all the different tiles.  The sprite sheet is in this project and is called
		 * 		smb_tiles.png.  Start by looking at the sprite sheet and coming up with names for each of the tiles.  Add the names to Assets.java.  Once that is
		 * 		complete go into MarioGame.Init() and define the location of each tile in the sprite sheet.  I did the first one for you, just add a line for each tile:
		 * 				Assets.Sprites.TileBrownFloor = tileSheet.getSprite(385, 144, 16, 16);
		 * 2.  Once the sprites are defined, loop through a tile definition text file (probably easiest to use the same approach in the robot tutorial), and
		 * 		add the tiles to the outputArray from this function.  The example below is a test I made to add some default tiles.  In order to make a tile object
		 * 		you'll need a sprite object (defined in step 1) and also the tile pixel position (which can be calculated by multipying the tile index by the tile size.
		 * 		Let me know if you have questions or need a hand.  - Bobby		 */		
		
		/* Sample code below creates a row of 50 tiles 14 tiles down from the top.  This code should be replaced with a tile mapping loaded from a file. */
		for (int xCount = 0; xCount < 50; xCount++) {
			outputArray[xCount][14] = new MarioTile(Assets.Sprites.TileBrownFloor, xCount * Assets.TILESIZE, 14 * Assets.TILESIZE);
		}
	}
}
