package platformer.game;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

import platformer.framework.Layer;
import platformer.framework.OrderedPair;
import platformer.framework.Sprite;
import platformer.framework.SpriteContainer;
import platformer.framework.StaticSprite;
import platformer.framework.Tile;
import platformer.game.Assets.Sprites;

public class MarioTileLoader {
	
	public static void LoadTilesFromFile(Layer layer) {
		/* Instructions to complete this method:
		 * 1.  Go into the MarioGame.Init() and Assets.java file and add sprite definitions for all the different tiles.  The sprite sheet is in this project and is called
		 * 		smb_tiles.png.  Start by looking at the sprite sheet and coming up with names for each of the tiles.  Add the names to Assets.java.  Once that is
		 * 		complete go into MarioGame.Init() and define the location of each tile in the sprite sheet.  I did the first one for you, just add a line for each tile:
		 * 				Assets.Sprites.TileBrownFloor = tileSheet.getSprite(385, 144, 16, 16);
		 * 2.  Once the sprites are defined, loop through a tile definition text file (probably easiest to use the same approach in the robot tutorial), and
		 * 		add the tiles to the layer passed into the function.  The example below is a test I made to add some default tiles.  In order to make a tile object
		 * 		you'll need a sprite object (defined in step 1) and also the tile pixel position (which can be calculated by multipying the tile index by the tile size.
		 * 		Let me know if you have questions or need a hand.  - Bobby		 */		
		
		try{
			loadMap("images/SMB1.txt", layer);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void loadMap(String filename, Layer layer) throws IOException{
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		//reads until EOF
		while(true){
			String line = reader.readLine();
			if(line == null){
				reader.close();
				break;
			}
			if(!line.startsWith("!")){
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height = lines.size();
		
		//iterates through file, finds chars, and creates tiles
		for(int j = 0; j<15; j++){
			String line = (String)lines.get(j);
			for(int i = 0; i<width; i++){
				if(i < line.length()){
					char ch = line.charAt(i);
					
					/*switch(ch){
					case '0': setTile(layer, Sprites.TileBrownFloor, i, j);
					case '1': setTile(layer, Sprites.TileBrownFloorHalf, i, j);
					//case '2':
					//case '3':
					//case '4':
					//case '5':
					//case '6':
					//case '7':
					//case '8':
					//case '9':
					//case 'A':
					//case 'B':
					//case 'C':
					//case 'D':
					//case 'E':
					//case 'F':
					//case 'G': 
					//case 'H':
					//case 'I':
					//case 'J':
					//case 'K':
					//case 'L':
					//case 'M':
					//case 'N':
					//case 'O':
					//case 'P':
					//case 'Q':
					//case 'R':
					//case 'S':
					//case 'T':
					//case 'U':
					//case 'V':
					//case 'W':
					//case 'Y':
					//case 'Z':
					}*/
					
					if(ch == 'G'){
						MarioTile tile = new MarioTile(Assets.Sprites.TileBrownFloor, i * Assets.TILESIZE, j * Assets.TILESIZE);
						layer.setTile(tile, i, j);
					}			
					else if(ch == 'B'){
						MarioBreakableTile tile = new MarioBreakableTile(Assets.Sprites.BrownBrick, i * Assets.TILESIZE, j * Assets.TILESIZE);
						tile.setDestructible(true);
						layer.setTile(tile, i, j);
					}
					else if(ch == 'I'){
						MarioTile tile = new MarioTile(Assets.Sprites.ItemBlock3, i * Assets.TILESIZE, j * Assets.TILESIZE);
						layer.setTile(tile, i, j);
					}
					else if(ch == 'W'){
						MarioTile tile = new MarioTile(Assets.Sprites.GreenPipe, i * Assets.TILESIZE, j * Assets.TILESIZE);
						layer.setTile(tile, i, j);
					}
					else if(ch == 'T'){
						MarioTile tile = new MarioTile(Assets.Sprites.GreenPipeTop, i * Assets.TILESIZE, j * Assets.TILESIZE);
						layer.setTile(tile, i, j);
					}
					else if(ch == 'H'){
						MarioTile tile = new MarioTile(Assets.Sprites.HillLarge, i * Assets.TILESIZE, j * Assets.TILESIZE);
						tile.setStopsMovement(false);
						layer.setTile(tile, i, j);
					}
					else if(ch == '0'){
						MarioTile tile = new MarioTile(Assets.Sprites.TileBrownFloorHalf, i * Assets.TILESIZE, j * Assets.TILESIZE);
						layer.setTile(tile, i, j);
					}
					else if(ch == '1'){
						MarioTile tile = new MarioTile(Assets.Sprites.BushLarge, i * Assets.TILESIZE, j * Assets.TILESIZE);
						tile.setStopsMovement(false);
						layer.setTile(tile, i, j);
					}
					else if(ch == '2'){
						MarioTile tile = new MarioTile(Assets.Sprites.HillSmall, i * Assets.TILESIZE, j * Assets.TILESIZE);
						tile.setStopsMovement(false);
						layer.setTile(tile, i, j);
					}
					else if(ch == '3'){
						MarioTile tile = new MarioTile(Assets.Sprites.CloudBlueSmall, i * Assets.TILESIZE, j * Assets.TILESIZE);
						tile.setStopsMovement(false);
						layer.setTile(tile, i, j);
					}
					else if(ch == '4'){
						MarioTile tile = new MarioTile(Assets.Sprites.CloudBlueLarge, i * Assets.TILESIZE, j * Assets.TILESIZE);
						tile.setStopsMovement(false);
						layer.setTile(tile, i , j);
					}
				}
			}
		}
	}
	
	public static void setTile(Layer layer, Sprite tileSprite, int xPos, int yPos){
		MarioTile tile = new MarioTile(tileSprite, xPos * Assets.TILESIZE, yPos * Assets.TILESIZE);
		layer.setTile(tile, xPos, yPos);
	}
}
