package com.cg.censusanalyser;

public class App {
	private static String CENSUS_FILE = "./IndiaStateCensusData.csv";
	public static void main(String[] args) {
		System.out.println("Indian States Census Analyser");
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			System.out.println("Count of data: " + obj.loadStateCsvData(CENSUS_FILE));
		} catch (StateCensusAnalyserException e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}
}
