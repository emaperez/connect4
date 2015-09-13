package com.deviget.model;

public class Board {

	public static final int COLUMNS = 7;
	public static final int ROWS = 6;

	private Square[][] board;

	public Board() {
		this.board = new Square[ROWS][COLUMNS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				this.board[row][col] = new Square();
			}
		}
	}

	public void setSquare(int row, int col, SquareStatus squareStatus) {
		this.board[row][col].setStatus(squareStatus);
	}
 
	public Square getSquare(int row, int col) {
		return this.board[row][col];
	}
}
