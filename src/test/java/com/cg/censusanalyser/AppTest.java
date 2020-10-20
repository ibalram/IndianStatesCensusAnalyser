package com.cg.censusanalyser;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class AppTest {
	public static final String STATE_CENSUS_FILE = "./IndiaStateCensusData.csv";
	public static final String STATE_CENSUS_INCORECT_DELIMITER_FILE = "./WrongStateCensusData.csv";
	public static final String STATE_CENSUS_INCORRECT_CSV_FILE = "./IncorrectCsvFile.txt";

	private StateCensusAnalyser censusAnalyser;

	@Before
	public void initialize() {
		censusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenStateCensusCSVFileShouldReturnNumberOfRecords() {
		int count;
		try {
			count = censusAnalyser.loadStateCsvData(STATE_CENSUS_FILE);
			Assert.assertEquals(29, count);
		} catch (StateCensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenStateCensusIncorrectCSVFileShouldThrowException() {
		try {
			censusAnalyser.loadStateCsvData(STATE_CENSUS_INCORRECT_CSV_FILE);
		} catch (StateCensusAnalyserException e) {
			assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV, e.type);
		}
	}

	@Test
	public void givenStateCensusIncorectDelimiterHeaderShouldThrowException() {
		try {
			censusAnalyser.loadStateCsvData(STATE_CENSUS_INCORECT_DELIMITER_FILE);
		} catch (StateCensusAnalyserException e) {
			assertEquals(StateCensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION, e.type);
		}
	}
}
