package com.cg.censusanalyser;

public class StateCensusAnalyserException extends Exception {

	enum ExceptionType {
		INCORRECT_CSV, INCORRECT_DELIMITER, INCORRECT_HEADER, RUNTIME_EXCEPTION;
	}

	ExceptionType type;

	public StateCensusAnalyserException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}

}