package platformer.framework;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public abstract class Game {
	public void init() {}	
	public void render(Graphics g, ImageObserver observer) {}
	public void update(long elapsedTime) {}
}
