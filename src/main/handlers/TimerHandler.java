package main.handlers;

import main.DiamondPaintings;

public class TimerHandler {
	private static boolean isRunning = false;
	
	private static long startedAt = 0;
	
	public static void start() {
		if (isRunning) return;
		
		isRunning = true;
		startedAt = System.currentTimeMillis();
	}
	
	public static void stop() {
		if (!isRunning) return;
		DiamondPaintings.getInstance().getSelectedPainting().addTimeWorked(getElapsedTime());
		isRunning = false;
	}
	
	public static void toggle() {
		if (isRunning) {
			stop();
		} else {
			start();
		}
	}
	
	public static boolean isRunning() {
		return isRunning;
	}
	
	public static long getElapsedTime() {
		return (isRunning ? (System.currentTimeMillis() - startedAt) : 0);
	}
	
	public static String formatTimeString(long time) {
		int totalHours = (int) (time / (3600000.0D));
		int totalMinutes = (int) ((time - (totalHours * 3600000)) / 60000.0D);
		int totalSeconds = (int) ((time - (totalHours * 3600000) - (totalMinutes * 60000)) / 1000.0D);
		return String.valueOf((totalHours < 10 ? "0" : "") + totalHours + ":" + (totalMinutes < 10 ? "0" : "") + totalMinutes + ":" + (totalSeconds < 10 ? "0" : "") + totalSeconds);
	}
}