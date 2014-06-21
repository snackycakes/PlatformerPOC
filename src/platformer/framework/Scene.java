package platformer.framework;

import java.util.ArrayList;

public abstract class Scene {
	protected int cameraPosition = 0;
	protected ArrayList<Layer> layers = new ArrayList<Layer>();
	
	public ArrayList<Layer> getLayers() {
		return layers;
	}
	
	public void addLayer(Layer layer) {
		this.layers.add(layer);
	}
	
	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}

	public int getCameraPosition() {
		return cameraPosition;
	}

	public void setCameraPosition(int cameraPosition) {
		this.cameraPosition = cameraPosition;
	}	
}
