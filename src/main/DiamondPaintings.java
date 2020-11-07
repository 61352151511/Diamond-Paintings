package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import main.geminfo.Painting;
import main.handlers.KeyHandler;
import main.handlers.RenderHandler;
import main.handlers.SaveHandler;
import main.handlers.TimerHandler;
import main.paintings.NorthernLights;
import main.paintings.TwoKittens;

public class DiamondPaintings extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	
	private static DiamondPaintings INSTANCE;
	
	private Painting selectedPainting;
	
	private Thread thread;
	private boolean running = false;
	private BufferedImage img;
	
	public DiamondPaintings() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		this.selectedPainting = TwoKittens.getInstance();
		
		INSTANCE = this;
	}
	
	public static DiamondPaintings getInstance() {
		return INSTANCE;
	}
	
	public Painting getSelectedPainting() {
		return this.selectedPainting;
	}
	
	private void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override public void run() {
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean ticked = false;
		
		while (running) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			
			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;
				tickCount ++;
				if (tickCount % 60 == 0) {
					previousTime += 1000;
				}
			}
			
			if (ticked) {
				render();
			}
		}
	}
	
	private void tick() {
		
	}
	
	private void render() {
		Graphics graphics = RenderHandler.getGraphics();
		if (graphics == null) return;

		graphics.drawImage(img, 0, 0, WIDTH + 10, HEIGHT + 10, null);
		RenderHandler.renderPainting(this.getSelectedPainting());
		String modifierAmount = "(+ / -) 1";
		if (KeyHandler.isShiftPressed() && !KeyHandler.isAltPressed()) modifierAmount = "(+ / -) 10";
		if (KeyHandler.isAltPressed() && !KeyHandler.isShiftPressed()) modifierAmount = "(+ / -) 50";
		if (KeyHandler.isShiftPressed() && KeyHandler.isAltPressed()) modifierAmount = "(+ / -) 100";
		String title = this.getSelectedPainting().getName() + " - " + this.getSelectedPainting().getId();
		graphics.setColor(Color.WHITE);
		graphics.drawString(title, WIDTH - graphics.getFontMetrics().stringWidth(title), 20);
		graphics.setColor(Color.YELLOW);
		graphics.drawString(modifierAmount, WIDTH - graphics.getFontMetrics().stringWidth(modifierAmount), 40);
		String timeElapsed = TimerHandler.formatTimeString(this.getSelectedPainting().getTimeWorked());
		graphics.drawString(timeElapsed, WIDTH - graphics.getFontMetrics().stringWidth(timeElapsed), 60);
		String gpm = "Gems Per Minute: " + this.getSelectedPainting().gemsPerMinute();
		graphics.setColor(Color.WHITE);
		graphics.drawString(gpm, WIDTH - graphics.getFontMetrics().stringWidth(gpm), 80);

		RenderHandler.finishRender();
	}
	
	public static void main(String[] args) {
		DiamondPaintings diamondPaintings = new DiamondPaintings();
		
		diamondPaintings.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent arg0) {}
			@Override public void mouseEntered(MouseEvent arg0) {}
			@Override public void mouseExited(MouseEvent arg0) {}
			@Override public void mousePressed(MouseEvent arg0) {}
			@Override public void mouseReleased(MouseEvent arg0) {}
		});
		
		diamondPaintings.addMouseMotionListener(new MouseMotionListener() {
			@Override public void mouseMoved(MouseEvent e) {}
			@Override public void mouseDragged(MouseEvent e) {}
		});
		
		diamondPaintings.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {
				KeyHandler.onKeyRelease(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				KeyHandler.onKeyPress(e);
			}
		});
		
		final JFrame frame = new JFrame();
		frame.add(diamondPaintings);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Diamond Paintings");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		diamondPaintings.start();
		
		SaveHandler.load();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				if (TimerHandler.isRunning()) TimerHandler.stop();
				SaveHandler.save();
			}
		}));
	}
}