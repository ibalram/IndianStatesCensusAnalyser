package com.cg.censusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cg.censusanalyser.StateCensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvBadConverterException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvRuntimeException;

public class StateCensusAnalyser {
	public int loadStateCsvData(String CENSUS_FILE) throws StateCensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(CENSUS_FILE))) {
			CsvToBeanBuilder<StateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<StateCensus> csvToBean = csvToBeanBuilder.withType(StateCensus.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<StateCensus> stateList = csvToBean.parse();
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
		}
	}
}