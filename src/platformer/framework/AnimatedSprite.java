package platformer.framework;

import java.awt.Image;
import java.util.ArrayList;

public class AnimatedSprite extends SpriteContainer {

	private ArrayList<AnimFrame> frames;
	private int currentFrame;
	private long animTime; // long takes up more memory than int but can hold
							// more accurate numbers.
	private long totalDuration;

	public AnimatedSprite(OrderedPair parentPosition) {
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0;

		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}

	public synchronized void addFrame(Sprite sprite, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(sprite.getSpriteImage(), totalDuration));
	}

	@Override
	public synchronized void update(long elapsedTime, OrderedPair parentPosition)  {
		super.update(elapsedTime, parentPosition);
		
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}

			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;
			}
		}
	}

	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	private class AnimFrame {

		Image image;
		long endTime;

		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}

	@Override
	public synchronized Sprite getSprite() {
		if (frames.size() == 0) {
			return null;
		} else {
			return new Sprite(getFrame(currentFrame).image);
		}
	}
}
