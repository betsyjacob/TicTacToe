package com.metro.model;

public class Board {
	
	private final int size;
	Cell[][] cell;
	
	public Board(int size) {
		super();
		this.size = size;
		this.cell = new Cell[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				this.cell[i][j] = new Cell("");
			}
		}
	}
	
	public Cell[][] getCell() {
		return cell;
	}
	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}
	public int getSize() {
		return size;
	}
}

