package com.deviget.model;

public class Engine {
	private Board board;

	public Engine(Board board) {
		this.board = board;
	}

	public boolean makeMove(Move move) {
		if (this.validMove(move.getRow(), move.getColumn())) {
			this.board.setSquare(move.getRow(), move.getColumn(), move.getPlayer().getIdStatus());
			return true;
		} else {
			return false;
		}
	}

	private boolean validMove(Integer row, Integer col) {
		if (col > Board.COLUMNS || col < 0)
			return false;
		if (row > Board.ROWS || col < 0)
			return false;
		if (row<(Board.ROWS-1) && board.getSquare(1+row, col).getStatus().compareTo(SquareStatus.EMPTY) == 0)
			return false;
		if (this.board.getSquare(row, col).getStatus().compareTo(SquareStatus.EMPTY) != 0) {
			return false;
		}

		return true;
	}

	public boolean check4InLine(Move move) {
		if (this.check4InRow(move.getRow(), move.getColumn(), move.getPlayer().getIdStatus())) {
			return true;
		}
		if (this.check4InColumn(move.getRow(), move.getColumn(), move.getPlayer().getIdStatus())) {
			return true;
		}
		if (this.check4InDiagonalAsc(move.getRow(), move.getColumn(), move.getPlayer().getIdStatus())) {
			return true;
		}
		if (this.check4InDiagonalDesc(move.getRow(), move.getColumn(), move.getPlayer().getIdStatus())) {
			return true;
		}
		return false;
	}

	private boolean check4InRow(Integer row, Integer column, SquareStatus status) {
		int countInLine = 0;
		for (int idx = 0; idx < Board.ROWS; idx++) {
			if (this.board.getSquare(idx, column).getStatus().compareTo(status) == 0) {
				countInLine++;
			} else {
				countInLine = 0;
			}
			if (countInLine == 4) {
				return true;
			}
		}
		return false;
	}

	private boolean check4InColumn(Integer row, Integer column, SquareStatus status) {
		int countInLine = 0;
		for (int idx = 0; idx < Board.COLUMNS; idx++) {
			if (this.board.getSquare(row, idx).getStatus().compareTo(status) == 0) {
				countInLine++;
			} else {
				countInLine = 0;
			}
			if (countInLine == 4) {
				return true;
			}
		}
		return false;
	}

	private boolean check4InDiagonalAsc(Integer row, Integer column, SquareStatus status) {
		int rowAsc = row;
		int colAsc = column;
		while (rowAsc >= 0 && rowAsc < Board.ROWS && colAsc >= 0 && colAsc < Board.COLUMNS) {
			rowAsc++;
			colAsc--;
		}
		rowAsc--;
		colAsc++;
		int countInLine = 0;
		while (rowAsc >= 0 && rowAsc < Board.ROWS && colAsc >= 0 && colAsc < Board.COLUMNS) {
			if (this.board.getSquare(rowAsc, colAsc).getStatus().compareTo(status) == 0) {
				countInLine++;
			} else {
				countInLine = 0;
			}
			if (countInLine == 4) {
				return true;
			}
			rowAsc--;
			colAsc++;
		}
		return false;
	}

	private boolean check4InDiagonalDesc(Integer row, Integer column, SquareStatus status) {
		int rowDesc = row;
		int colDesc = column;
		while (rowDesc >= 0 && rowDesc < Board.ROWS && colDesc >= 0 && colDesc < Board.COLUMNS) {
			rowDesc--;
			colDesc--;
		}
		rowDesc++;
		colDesc++;
		int countInLine = 0;
		while (rowDesc >= 0 && rowDesc < Board.ROWS && colDesc >= 0 && colDesc < Board.COLUMNS) {
			if (this.board.getSquare(rowDesc, colDesc).getStatus().compareTo(status) == 0) {
				countInLine++;
			} else {
				countInLine = 0;
			}
			if (countInLine == 4) {
				return true;
			}
			rowDesc++;
			colDesc++;
		}
		return false;
	}
}
