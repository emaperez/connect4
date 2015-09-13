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
	private Integer numberOfPlays = 0;
	private final static Integer MAX_PLAYS = Board.ROWS*Board.COLUMNS;
	
	private Game() {
		this.player1 = new Player(new Integer(1),"yellow");
		this.turn = this.player1;
		this.player2 = new Player(new Integer(2),"red");
		this.board = new Board();
		this.engine = new Engine(this.board);
		this.gameStatus = GameStatus.NOT_STARTED;
		this.lastMove = null;
		numberOfPlays = 0;
	}

	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	public Game newGame() {
		if (this.gameStatus != GameStatus.STARTED) {
			instance = new Game();
		} 
		return instance;
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
				numberOfPlays++;
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
		this.gameStatus = GameStatus.STARTED;
		if (this.engine.check4InLine(move)) {
			if (this.turn == this.player1) {
				this.gameStatus = GameStatus.PLAYER1_WON;
			} else {
				this.gameStatus = GameStatus.PLAYER2_WON;
			}
		}
		else if (MAX_PLAYS.equals(numberOfPlays)) {
			this.gameStatus = GameStatus.DRAW;
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
