package com.cg.censusanalyser;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class StateCodeTest {
	public static final String STATE_CODE_FILE = "./IndiaStateCode.csv";
	public static final String STATE_CODE_INCORECT_DELIMITER_FILE = "./IndiaStateCensusData.csv";
	public static final String STATE_CODE_INCORECT_HEADER_FILE = "./IndiaStateCensusData.csv";
	public static final String STATE_CODE_INCORRECT_CSV_FILE = "./IncorrectCsvFile.txt";

	private StateCensusAnalyser censusAnalyser;

	@Before
	public void initialize() {
		censusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenStateCensusCSVFileShouldReturnNumberOfRecords() {
		int count;
		try {
			count = censusAnalyser.loadStateCode(STATE_CODE_FILE);
			Assert.assertEquals(37, count);
		} catch (StateCensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenStateCensusIncorrectCSVFileShouldThrowException() {
		try {
			censusAnalyser.loadStateCode(STATE_CODE_INCORRECT_CSV_FILE);
		} catch (StateCensusAnalyserException e) {
			assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV, e.type);
		}
	}

	@Test
	public void givenStateCensusIncorectDelimiterDelimiterShouldThrowException() {
		try {
			censusAnalyser.loadStateCode(STATE_CODE_INCORECT_DELIMITER_FILE);
		} catch (StateCensusAnalyserException e) {
			assertEquals(StateCensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION, e.type);
		}
	}

	@Test
	public void givenStateCensusIncorectDelimiterHeaderShouldThrowException() {
		try {
			censusAnalyser.loadStateCode(STATE_CODE_INCORECT_HEADER_FILE);
		} catch (StateCensusAnalyserException e) {
			assertEquals(StateCensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION, e.type);
		}
	}
}
