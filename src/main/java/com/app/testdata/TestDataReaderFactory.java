package com.app.testdata;

public class TestDataReaderFactory {

	public static ITestDataReader getTestDataReader(String sourceDataType) {
		if("csv".equalsIgnoreCase(sourceDataType)) {
			return new CSVTestDataReader();
		}
		return null;
	}
	
}
	