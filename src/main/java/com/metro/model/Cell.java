package com.metro.model;

public class Cell {
	private String value;
	
	public Cell(String value) {
		super();
		this.value = value;
	}
	public boolean hasBeenPlayed() {
		return (this.value != null && this.value.length() > 0);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return this.value+" |";
	}
}
