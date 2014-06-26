package platformer.framework;

import java.util.ArrayList;

public class Scene {
	protected Position cameraPosition = new Position(0, 0);
	protected ArrayList<Layer> layers = new ArrayList<Layer>();
	protected final int VIEWABLEWIDTH = 256;
	protected final int CAMERASCROLLX = 128;	

	public ArrayList<Layer> getLayers() {
		return layers;
	}
	
	public Position getCameraPosition() {
		return cameraPosition;
	}

	public void setCameraPosition(Position cameraPosition) {
		this.cameraPosition = cameraPosition;
	}

	public void addLayer(Layer layer) {
		this.layers.add(layer);
	}
	
	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}
	
	public void update(long elapsedTime) {		
		for (int layerIdx = 0; layerIdx < layers.size(); layerIdx++) {
			Layer layer = layers.get(layerIdx);
			layer.update(elapsedTime);
		}
		adjustCamera(elapsedTime);
	}
	
	public void adjustCamera(long elapsedTime) {
		// this code is temporary, shouldn't assume pawn will be at these indexes. 
		Pawn pawn = layers.get(0).pawns.get(0);
		int pawnOffset = pawn.getPositionX() - cameraPosition.getxPos();
		if (pawn.getPositionX() - cameraPosition.getxPos() > CAMERASCROLLX) {
			cameraPosition.addToPositionX(pawnOffset - CAMERASCROLLX);
		}
	}
}
