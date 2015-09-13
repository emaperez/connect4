package com.deviget.model;

public class Square {
	private SquareStatus status;

	public Square() {
		this.status = SquareStatus.EMPTY;
	}

	public SquareStatus getStatus() {
		return status;
	}

	public void setStatus(SquareStatus status) {
		this.status = status;
	}
}
