package com.deviget.model;


public class Game {

	private static Game instance = null;
	private Player player1;
	private Player player2;
	private Board board;
	private Engine engine;
	private Move lastMove;
	private Player turn;
	private GameStatus gameStatus;

	private Game() {
		this.player1 = new Player(new Integer(1),"yellow");
		this.turn = this.player1;
		this.player2 = new Player(new Integer(2),"red");
		this.board = new Board();
		this.engine = new Engine(this.board);
		this.gameStatus = GameStatus.STARTED;
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public boolean newGame() {
		if (this.gameStatus != GameStatus.STARTED) {
			instance = new Game();
			return true;
		} else {
			return false;
		}
	}

	public Move getLastMove() {
		return this.lastMove;
	}

	public Player getPlayerTurn() {
		return turn;
	}

	public boolean makeMove(Integer idPlayer, Integer row, Integer column) {
		if (this.turn.getId().equals(idPlayer)) {
			Move move = new Move(this.turn, row, column);
			if (this.engine.makeMove(move)) {
				this.lastMove = move;
				this.updateGameStatus(move);
				this.nextTurn();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void updateGameStatus(Move move) {
		if (this.engine.check4InLine(move)) {
			if (this.turn == this.player1) {
				this.gameStatus = GameStatus.PLAYER1_WON;
			} else {
				this.gameStatus = GameStatus.PLAYER2_WON;
			}
		}
	}

	private void nextTurn() {
		if (this.turn == this.player1) {
			this.turn = this.player2;
		} else {
			this.turn = this.player1;
		}
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Board getBoard() {
		return board;
	}

	public Engine getEngine() {
		return engine;
	}

	public GameStatus getStatus() {
		return gameStatus;
	}
}
