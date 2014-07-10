package platformer.framework;

import java.util.ArrayList;
import java.util.Iterator;

public class Scene {
	protected OrderedPair cameraPosition = new OrderedPair(0, 0);
	protected ArrayList<Layer> layers = new ArrayList<Layer>();
	protected final int VIEWABLEWIDTH = 256;
	protected final int VIEWABLEHEIGHT = 240;
	protected final int CAMERASCROLLX = 128;
	protected final int VIEWTOLERANCE = 32;
	protected Pawn pawn;

	public void update(long elapsedTime) {
		pawn.setMinBoundary(new OrderedPair(cameraPosition.getPosX(), cameraPosition.getPosY()));
		
		for (Layer layer : layers) {			
			Iterator<Mob> iterator = layer.getMobs().iterator();
			while (iterator.hasNext()) {
			   Mob mob = iterator.next();
			   
				// remove mobs that are permanantly out of view
			   if (mob.getPositionX() < cameraPosition.getPosX() - VIEWTOLERANCE || mob.getPositionY() > cameraPosition.getPosY() + VIEWABLEHEIGHT)
			   {
				   iterator.remove();
			   }
			   
			   // flag mobs not in view
			   if (mob.getPositionX() > cameraPosition.getPosX() + VIEWABLEWIDTH || mob.getPositionY() < cameraPosition.getPosY() - VIEWTOLERANCE)
			   {
				   mob.setInView(false);
				   iterator.remove();
			   }
			}
			
			layer.update(elapsedTime);
		}
		
		adjustCamera(elapsedTime);
	}
	
	public void adjustCamera(long elapsedTime) {
		int pawnOffset = pawn.getPositionX() - cameraPosition.getPosX();
		if (pawn.getPositionX() - cameraPosition.getPosX() > CAMERASCROLLX) {
			cameraPosition.addToValueX(pawnOffset - CAMERASCROLLX);
		}
	}

	public ArrayList<Layer> getLayers() {
		return layers;
	}
	
	public OrderedPair getCameraPosition() {
		return cameraPosition;
	}

	public void setCameraPosition(OrderedPair cameraPosition) {
		this.cameraPosition = cameraPosition;
	}

	public void addLayer(Layer layer) {
		this.layers.add(layer);
	}
	
	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}
	
	public Pawn getPawn() {
		return pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
}
