package com.metro;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.hamcrest.Matchers;
import org.junit.Test;

public class GameControllerTest {

	Properties prop = GameUtils.getProperties();

	@Test
	public void boardSizeIsNumber() {
		assertTrue("Boardsize: Value should be number.",
				prop.getProperty("boardsize").matches("-?\\d+(\\.\\d+)?"));
	}
	@Test
	public void boardSizeNotNullTest() {
		assertThat(Integer.parseInt(prop.getProperty("boardsize")), Matchers.notNullValue());
	}
	@Test
	public void boardSizeMinRangeTest() {
		assertThat("Boardsize: Value should be >= 3.", Integer.parseInt(prop.getProperty("boardsize")),
				Matchers.greaterThanOrEqualTo(3));
	}
	@Test
	public void boardSizeMaxRangeTest() {
		assertThat("Boardsize: Value should be <= 10.", Integer.parseInt(prop.getProperty("boardsize")),
				Matchers.lessThanOrEqualTo(10));
	}

	@Test
	public void playersNotNullValueTest() {
		assertThat("Player1: Should be not null.",
				prop.getProperty("player1"), Matchers.notNullValue());
		assertThat("Player2: Should be not null.",
				prop.getProperty("player2"), Matchers.notNullValue());
		assertThat("Computer Player: Should be not null.",
				prop.getProperty("computer"), Matchers.notNullValue());
	}

	@Test
	public void playersLengthTest() {
		assertThat("Player1: Should be 1 character long.", prop.getProperty("player1").length(),
					Matchers.equalTo(1));
		assertThat("Player2: Should be 1 character long.", prop.getProperty("player2").length(),
					Matchers.equalTo(1));
		assertThat("Computer Player: Should be 1 character long.", prop.getProperty("computer").length(),
					Matchers.equalTo(1));
	}

}
