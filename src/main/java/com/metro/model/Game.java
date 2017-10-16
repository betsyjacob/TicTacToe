package com.metro.model;

public class Game {
	
	private Board board;
	private String[] players;
	private int currentPlayer;
	private boolean isGameOver;
	
	
	public Game(int size, String[] players, int startPlayer) {
		super();
		this.board = new Board(size);
		this.players = players;
		this.currentPlayer = startPlayer;
		this.isGameOver = false;
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String[] getPlayers() {
		return players;
	}

	public void setPlayers(String[] players) {
		this.players = players;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}

