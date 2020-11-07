package main.geminfo;

import java.math.BigDecimal;
import java.util.ArrayList;

import main.handlers.TimerHandler;

public class Painting {
	private String name;
	private String id;
	private ArrayList<Gem> gems = new ArrayList<Gem>();
	private int selectedGem = 0;
	
	private int totalCompleted = 0;
	private int totalPieces = 0;
	
	private long timeWorked = 0;
	
	public Painting(String name, String id, Gem... gems) {
		this.name = name;
		this.id = id;
		for (Gem gem : gems) {
			this.gems.add(gem);
			this.totalCompleted += gem.getPiecesCompleted();
			this.totalPieces += gem.getNumPieces();
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public ArrayList<Gem> getGems() {
		return this.gems;
	}
	
	public int getSelectedGem() {
		return selectedGem;
	}
	
	public void increaseSelectedGem() {
		selectedGem ++;
		if (selectedGem > (getGems().size() - 1)) selectedGem = 0;
	}
	
	public void decreaseSelectedGem() {
		selectedGem --;
		if (selectedGem < 0) selectedGem = (getGems().size() - 1);
	}
	
	public void increasePiecesCompleted(int increaseAmount) {
		this.gems.get(this.getSelectedGem()).increasePiecesCompleted(increaseAmount);
		this.recalculate();
	}
	
	public void decreasePiecesCompleted(int decreaseAmount) {
		this.gems.get(this.getSelectedGem()).decreasePiecesCompleted(decreaseAmount);
		this.recalculate();
	}
	
	private void recalculate() {
		this.totalPieces = 0;
		this.totalCompleted = 0;
		
		for (Gem gem : this.gems) {
			this.totalCompleted += gem.getPiecesCompleted();
			this.totalPieces += gem.getNumPieces();
		}
	}
	
	public int getTotalPieces() {
		return this.totalPieces;
	}
	
	public int getTotalCompleted() {
		return this.totalCompleted;
	}
	
	public String getPercentageComplete() {
		if (this.totalCompleted == this.totalPieces) return "100";
		if (this.totalCompleted == 0) return "0";
		String str = String.valueOf(((double) this.totalCompleted / (double) this.totalPieces) * 100.0D);
		BigDecimal bd = new BigDecimal(str).setScale(1, BigDecimal.ROUND_HALF_EVEN);
		return bd.toPlainString();
	}
	
	public void addTimeWorked(long additionalTime) {
		this.timeWorked += additionalTime;
	}
	
	public long getTimeWorked() {
		return TimerHandler.getElapsedTime() + this.timeWorked;
	}
	
	public double gemsPerMinute() {
		if (this.getTimeWorked() == 0L) return 0.0D;
		double seconds = (this.getTimeWorked() / 1000.0D);
		BigDecimal bd = new BigDecimal(String.valueOf((this.totalCompleted / seconds) * 60)).setScale(1, BigDecimal.ROUND_HALF_EVEN);
		return bd.doubleValue();
	}
	
	public String serialize() {
		String serializedString = "";
		for (Gem gem : this.getGems()) {
			serializedString += gem.getCode() + "=" + gem.getPiecesCompleted() + "|";
		}
		
		return serializedString;
	}
	
	public void deserialize(String serializedString) {
		String[] gemStrings = serializedString.split("\\|");
		for (String gemString : gemStrings) {
			String[] gemInfo = gemString.split("=");
			if (gemInfo.length < 1) continue;
			
			int code = Integer.valueOf(gemInfo[0]);
			int numCompleted = Integer.valueOf(gemInfo[1]);
			
			Gem gem = this.getGemByCode(code);
			
			if (gem == null) continue;
			
			gem.setPiecesCompleted(numCompleted);
		}
		
		this.recalculate();
	}
	
	private Gem getGemByCode(int code) {
		for (Gem gem : this.getGems()) {
			if (gem.getCode() == code) return gem;
		}
		
		return null;
	}
}