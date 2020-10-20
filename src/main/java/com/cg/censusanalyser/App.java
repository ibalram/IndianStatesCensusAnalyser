package com.cg.censusanalyser;

public class App {
	private static String CENSUS_FILE = "./IndiaStateCensusData.csv";
	private static String STATE_CODE_FILE = "./IndiaStateCode.csv";

	public static void main(String[] args) {
		System.out.println("Indian States Census Analyser");
		StateCensusAnalyser obj = new StateCensusAnalyser();
		try {
			System.out.println("Count of Census data: " + obj.loadStateCsvData(CENSUS_FILE));
			System.out.println("Count of State Codes: " + obj.loadStateCode(STATE_CODE_FILE));
		} catch (StateCensusAnalyserException e) {
			System.out.println("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
