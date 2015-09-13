package com.deviget.model;

public class Move {
	private Player player;
	private Integer row;
	private Integer column;

	public Move(Player player, Integer row, Integer column) {
		super();
		this.player = player;
		this.row = row;
		this.column = column;
	}

	public Player getPlayer() {
		return player;
	}

	public Integer getRow() {
		return row;
	}

	public Integer getColumn() {
		return column;
	}
}
