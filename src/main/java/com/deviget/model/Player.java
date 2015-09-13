package com.deviget.model;


public class Player {
	private final Integer id;
	private final SquareStatus idStatus;
	private final String color;

	public Player(Integer id, String color) {
		this.id = id;
		if (id == 1) {
			this.idStatus = SquareStatus.PLAYER1;
		} else {
			this.idStatus = SquareStatus.PLAYER2;
		}
		this.color = color;
	}

	public SquareStatus getIdStatus() {
		return idStatus;
	}

	public String getColor() {
		return color;
	}

	public Integer getId() {
		return id;
	}
}
