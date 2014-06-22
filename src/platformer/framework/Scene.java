package platformer.framework;

import java.util.ArrayList;

public class Scene {
	protected int cameraPosition = 0;
	protected ArrayList<Layer> layers = new ArrayList<Layer>();
	protected float gravity = 1;
	
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
	
	public void update(long elapsedTime) {		
		for (int layerIdx = 0; layerIdx < layers.size(); layerIdx++) {
			Layer layer = layers.get(layerIdx);
			
			for (int pawnIdx = 0; pawnIdx < layer.pawns.size(); pawnIdx++) {
				Pawn pawn = layer.pawns.get(pawnIdx);
				updateMob(pawn, elapsedTime);
			}
			
			for (int actorIdx = 0; actorIdx < layer.actors.size(); actorIdx++) {
				Actor actor = layer.actors.get(actorIdx);
				updateMob(actor, elapsedTime);
			}
		}
	}
	
	public void updateMob(Mob mob, long elapsedTime) {
		mob.update(elapsedTime);
		applyGravity(mob);
		mob.commitDesiredPosition();
	}
	
	public void applyGravity(Mob mob) {
		mob.desiredPosition.addToPositionY(gravity);
	}
	
}
