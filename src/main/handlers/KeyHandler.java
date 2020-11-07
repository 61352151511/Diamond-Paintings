package main.handlers;

import java.awt.event.KeyEvent;

import main.DiamondPaintings;

public class KeyHandler {
	
	private static boolean isShiftPressed = false;
	private static boolean isAltPressed = false;
	
	public static void onKeyPress(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_SHIFT) isShiftPressed = true;
		if (event.getKeyCode() == KeyEvent.VK_ALT) isAltPressed = true;
		
		if (event.getKeyCode() == KeyEvent.VK_UP) DiamondPaintings.getInstance().getSelectedPainting().decreaseSelectedGem();
		if (event.getKeyCode() == KeyEvent.VK_DOWN) DiamondPaintings.getInstance().getSelectedPainting().increaseSelectedGem();
		
		if (event.getKeyChar() == '+') {
			int modifierAmount = 1;
			if (event.isShiftDown() && !event.isAltDown()) modifierAmount = 10;
			if (event.isAltDown() && !event.isShiftDown()) modifierAmount = 50;
			if (event.isShiftDown() && event.isAltDown()) modifierAmount = 100;
			
			DiamondPaintings.getInstance().getSelectedPainting().increasePiecesCompleted(modifierAmount);
		}
		
		if (event.getKeyChar() == '-') {
			int modifierAmount = 1;
			if (event.isShiftDown() && !event.isAltDown()) modifierAmount = 10;
			if (event.isAltDown() && !event.isShiftDown()) modifierAmount = 50;
			if (event.isShiftDown() && event.isAltDown()) modifierAmount = 100;
			
			DiamondPaintings.getInstance().getSelectedPainting().decreasePiecesCompleted(modifierAmount);
		}
		
		if (event.getKeyCode() == KeyEvent.VK_SPACE) TimerHandler.toggle();
	}

	public static void onKeyRelease(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_SHIFT) isShiftPressed = false;
		if (event.getKeyCode() == KeyEvent.VK_ALT) isAltPressed = false;
	}

	public static boolean isShiftPressed() {
		return isShiftPressed;
	}

	public static boolean isAltPressed() {
		return isAltPressed;
	}
}