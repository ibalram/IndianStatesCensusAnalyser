package com.cg.censusanalyser;

public class CSVBuilderFactory {
	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
