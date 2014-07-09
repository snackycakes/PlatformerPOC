package platformer.framework;

public abstract class Node {
	protected OrderedPair position;
	protected SpriteContainer activeSpriteContainer;
	protected SpriteContainer prevSpriteContainer = new SpriteContainer();
	protected SpriteContainer activationSpriteContainer;
	protected SpriteContainer destructionSpriteContainer;
	protected boolean stopsMovement = true;
	protected boolean canActivate = true;
	protected boolean canDestroy = false;
	
	public Sprite getActiveSprite() {
		return activeSpriteContainer.getSprite();
	}
	
	public SpriteContainer getActiveSpriteContainer() {
		return activeSpriteContainer;
	}

	public void setActiveSpriteContainer(SpriteContainer activeSpriteContainer) {
		this.activeSpriteContainer = activeSpriteContainer;
	}
	
	public void setActiveSpriteContainer(Sprite activeSprite) {
		this.activeSpriteContainer = new StaticSprite(activeSprite, position);
	}
	
	public Node() {
		position = new OrderedPair();
	}
	
	public Node(OrderedPair position) {
		this.position = position;
	}
	
	public Node(int xPos, int yPos) {
		this.position = new OrderedPair(xPos, yPos);
	}
	
	public void update(long elapsedTime) {
		this.prevSpriteContainer.copy(this.activeSpriteContainer);
	}

	public OrderedPair getPosition() {
		return position;
	}

	public void setPosition(OrderedPair position) {
		this.position = position;
	}
	
	public void setPosition(float xCoord, float yCoord) {
		this.position.setPosition(xCoord, yCoord);
	}
	
	public int getPositionX() {
		return this.position.getPosX();
	}
	
	public int getPositionY() {
		return this.position.getPosY();
	}
	
	public float getCoordX() {
		return this.position.getValueX();
	}
	
	public float getCoordY() {
		return this.position.getValueY();
	}
	
	public boolean isStopsMovement() {
		return stopsMovement;
	}
	
	public void setStopsMovement(boolean stopsMovement) {
		this.stopsMovement = stopsMovement;
	}

	public SpriteContainer getPrevSpriteContainer() {
		return prevSpriteContainer;
	}

	public SpriteContainer getActivationSpriteContainer() {
		return activationSpriteContainer;
	}
	
	public void setActivationSpriteConatiner(SpriteContainer activationSpriteContainer) {
		this.activationSpriteContainer = activationSpriteContainer;
	}

	public void setDestructionSpriteConatiner(SpriteContainer destructionSpriteContainer) {
		this.destructionSpriteContainer = destructionSpriteContainer;
	}

	public void setCanActivate(boolean canActivate) {
		this.canActivate = canActivate;
	}
	
	public boolean canActivate() {
		return canActivate;
	}
	
	public void activateNode() {
		if (canActivate) {
			if (activationSpriteContainer != null) {
				activeSpriteContainer = activationSpriteContainer;
			}
			canActivate = false;
		}
	}
	
	public void setCanDestroy(boolean canDestroy) {
		this.canDestroy = canDestroy;
	}
	
	public boolean canDestroy() {
		return canDestroy;
	}
	
	public void destroyNode() {
		if (canDestroy) {
			if (destructionSpriteContainer != null) {
				activeSpriteContainer = destructionSpriteContainer;
			}
			stopsMovement = false;
			canDestroy = false;
		}
	}
}
