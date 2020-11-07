package main.geminfo;

import java.math.BigDecimal;

public class Gem {
	private int code;
	private char identifier;
	private int numPieces;
	private int piecesCompleted;
	
	public Gem(int code, char identifier, int numPieces) {
		this.code = code;
		this.identifier = identifier;
		this.numPieces = numPieces;
		this.piecesCompleted = 0;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public char getIdentifier() {
		return this.identifier;
	}
	
	public int getNumPieces() {
		return this.numPieces;
	}
	
	public int getPiecesCompleted() {
		return this.piecesCompleted;
	}
	
	public void setPiecesCompleted(int piecesCompleted) {
		this.piecesCompleted = piecesCompleted;
	}
	
	public void increasePiecesCompleted(int increaseAmount) {
		this.piecesCompleted += increaseAmount;
		if (this.piecesCompleted > this.numPieces) this.piecesCompleted = this.numPieces;
	}
	
	public void decreasePiecesCompleted(int decreaseAmount) {
		this.piecesCompleted -= decreaseAmount;
		if (this.piecesCompleted < 0) this.piecesCompleted = 0;
	}
	
	public String getPercentageComplete() {
		if (this.piecesCompleted == this.numPieces) return "100";
		if (this.piecesCompleted == 0) return "0";
		String str = String.valueOf(((double) this.piecesCompleted / (double) this.numPieces) * 100.0D);
		BigDecimal bd = new BigDecimal(str).setScale(1, BigDecimal.ROUND_HALF_EVEN);
		return bd.toPlainString();
	}
}