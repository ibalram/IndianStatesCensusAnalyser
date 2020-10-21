package com.cg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.cg.censusanalyser.opencsvbuilder.CSVBuilderException;
import com.cg.censusanalyser.opencsvbuilder.CSVBuilderFactory;
import com.cg.censusanalyser.opencsvbuilder.ICSVBuilder;

public class StateCensusAnalyser {
	public int loadStateCsvData(String CENSUS_FILE) throws StateCensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(CENSUS_FILE))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCensus> censusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
			return censusList.size();
		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV,
					"Incorrect csv file");
		} catch (IllegalStateException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_DELIMITER,
					"Incorrect Fields/delimiters");
		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION,
					"Runtime exception while parsing");
		} catch (CSVBuilderException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV,
					e.getMessage());
		}
	}

	public int loadStateCode(String STATE_CODE_FILE) throws StateCensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CODE_FILE))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCode> stateList = csvBuilder.getCSVFileList(reader, StateCode.class);
			return stateList.size();
		} catch (IOException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV,
					"Incorrect csv file");
		} catch (IllegalStateException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_DELIMITER,
					"Incorrect Fields/delimiters");
		} catch (RuntimeException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.RUNTIME_EXCEPTION,
					"Runtime exception while parsing");
		} catch (CSVBuilderException e) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV,
					e.getMessage());
		}
	}

	public <T> int getCount(Iterator<T> iterator) {
		int count = (int) StreamSupport.stream(((Iterable) iterator).spliterator(), false).count();
		return count;
	}
}
