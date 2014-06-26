package platformer.game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import platformer.framework.SpriteSheet;


public class StartingClass extends Applet implements Runnable, KeyListener {
	
	private Graphics second;
	private Image image;	
	private MarioGame marioGame = new MarioGame();

	@Override
	public void init() {
		setSize(768, 720);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Platformer Prototype");
	}

	@Override
	public void start() {
		marioGame.init();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		AffineTransform transform = ((Graphics2D)g).getTransform();
		((Graphics2D)g).scale(3, 3);
		
		marioGame.render(g, this);
		
		((Graphics2D)g).setTransform(transform);
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stubs
		super.destroy();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			marioGame.update(10);
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		marioGame.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		marioGame.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
