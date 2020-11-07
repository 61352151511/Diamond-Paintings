package main.handlers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.DiamondPaintings;
import main.geminfo.ColorManager;
import main.geminfo.Gem;
import main.geminfo.Painting;

public class RenderHandler {
	
	private static Graphics graphics = null;
	
	public static BufferStrategy getBufferStrategy() {
		BufferStrategy bufferStrategy = DiamondPaintings.getInstance().getBufferStrategy();
		
		if (bufferStrategy == null) {
			DiamondPaintings.getInstance().createBufferStrategy(3);
			return null;
		}
		
		return bufferStrategy;
	}
	
	public static boolean isBufferStrategySetup() {
		return DiamondPaintings.getInstance().getBufferStrategy() != null;
	}
	
	public static Graphics getGraphics() {
		if (!isBufferStrategySetup()) {
			getBufferStrategy();
			return null;
		}
		
		if (graphics == null) graphics = getBufferStrategy().getDrawGraphics();
		
		return graphics;
	}
	
	public static void finishRender() {
		getGraphics().dispose();
		getBufferStrategy().show();
		graphics = null;
	}
	
	public static void renderPainting(Painting painting) {
		int drawnGems = 0;
		int numFit = 0;
		int startX = 20;
		
		for (Gem gem : painting.getGems()) {
			if (numFit < 1) {
				if ((20 + (20 * drawnGems)) > (DiamondPaintings.getInstance().getHeight() - 20)) {
					startX = 420;
					numFit = drawnGems;
				}
			}
			
			int y = (20 * (drawnGems - numFit));
			
			boolean darkGem = ColorManager.isDark(ColorManager.getColorForCode(gem.getCode()));
			
			if (darkGem) {
				getGraphics().setColor(ColorManager.getColorForCode(-1).brighter());
				getGraphics().fillRoundRect(startX - 12, 6 + y, 397, 20, 3, 3);
			}
			String str = gem.getCode() + " (" + gem.getIdentifier() + ") - " + gem.getPiecesCompleted() + "/" + gem.getNumPieces();
			// getGraphics().setColor(drawnGems == painting.getSelectedGem() ? ColorManager.getSelectedColor() : ColorManager.getColorForCode(gem.getCode()));
			if (drawnGems == painting.getSelectedGem()) {
				getGraphics().setColor(darkGem ? Color.RED.darker() : Color.GREEN.darker());
				getGraphics().drawString(">", startX - 3 - getGraphics().getFontMetrics().stringWidth(">"), 20 + y);
			}
			//
			getGraphics().setColor(ColorManager.getColorForCode(gem.getCode()));
			getGraphics().drawString(str, startX, 20 + y);
			getGraphics().setColor(ColorManager.getColorForCode(gem.getCode()).darker());
			getGraphics().drawRoundRect(startX + 125, 8 + y, 200, 15, 5, 5);
			getGraphics().setColor(ColorManager.getColorForCode(gem.getCode()));
			int w = (int) Math.floor((200D / gem.getNumPieces()) * gem.getPiecesCompleted());
			if (gem.getNumPieces() == gem.getPiecesCompleted()) w = 200;
			getGraphics().fillRoundRect(startX + 125, 8 + y, w, 15, 5, 5);
			getGraphics().setColor(gem.getPercentageComplete() == "100" ? Color.GREEN : ColorManager.getColorForCode(-1));
			getGraphics().drawString(gem.getPercentageComplete() + "%", startX + 345, 20 + y);
			drawnGems ++;
		}
		
		String str = "TOTAL - " + painting.getTotalCompleted() + "/" + painting.getTotalPieces();
		getGraphics().setColor(Color.YELLOW);
		getGraphics().drawString(str, startX, 20 + (20 * (drawnGems - numFit)));
		getGraphics().setColor(Color.YELLOW.darker());
		getGraphics().drawRoundRect(startX + 125, 8 + (20 * (drawnGems - numFit)), 200, 15, 5, 5);
		getGraphics().setColor(Color.YELLOW);
		int w = (int) Math.floor((200D / painting.getTotalPieces()) * painting.getTotalCompleted());
		if (painting.getTotalCompleted() == painting.getTotalPieces()) w = 200;
		getGraphics().fillRoundRect(startX + 125, 8 + (20 * (drawnGems - numFit)), w, 15, 5, 5);
		getGraphics().setColor(painting.getPercentageComplete() == "100" ? Color.GREEN : ColorManager.getColorForCode(-1));
		getGraphics().drawString(painting.getPercentageComplete() + "%", startX + 345, 20 + (20 * (drawnGems - numFit)));
	}
}