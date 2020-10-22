package com.cg.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.cg.censusanalyser.opencsvbuilder.CSVBuilderException;
import com.cg.censusanalyser.opencsvbuilder.CSVBuilderFactory;
import com.cg.censusanalyser.opencsvbuilder.ICSVBuilder;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	List<StateCensus> censusList = null;
	List<StateCode> stateCodeList = null;

	public int loadStateCsvData(String CENSUS_FILE) throws StateCensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(CENSUS_FILE))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusList = csvBuilder.getCSVFileList(reader, StateCensus.class);
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
			stateCodeList = csvBuilder.getCSVFileList(reader, StateCode.class);
			return stateCodeList.size();
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

	public String getStateWiseSortdCensusData(String stateCensusFile) throws StateCensusAnalyserException {
		if (censusList == null || censusList.size() == 0) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV,
					"No Census Data");
		}
		Comparator<StateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusList);
		return sortedStateCensusJson;
	}

	public String getStateCodeWiseSortdCensusData(String stateCensusFile) throws StateCensusAnalyserException {
		if (stateCodeList == null || stateCodeList.size() == 0) {
			throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_CSV,
					"No Census Data");
		}
		Comparator<StateCode> stateComparator = Comparator.comparing(census -> census.stateCode);
		this.sortStateCode(stateComparator);
		String sortedStateCodeJson = new Gson().toJson(stateCodeList);
		return sortedStateCodeJson;
	}

	private void sort(Comparator<StateCensus> censusComparator) {
		for (int i = 0; i < censusList.size() - 1; ++i) {
			for (int j = 0; j < censusList.size() - i - 1; ++j) {
				StateCensus census1 = censusList.get(j);
				StateCensus census2 = censusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}
			}
		}
	}

	private void sortStateCode(Comparator<StateCode> stateComparator) {
		for (int i = 0; i < stateCodeList.size() - 1; ++i) {
			for (int j = 0; j < stateCodeList.size() - i - 1; ++j) {
				StateCode census1 = stateCodeList.get(j);
				StateCode census2 = stateCodeList.get(j + 1);
				if (stateComparator.compare(census1, census2) > 0) {
					stateCodeList.set(j, census2);
					stateCodeList.set(j + 1, census1);
				}
			}
		}
	}
}
