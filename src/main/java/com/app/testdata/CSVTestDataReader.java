package com.app.testdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.app.testbase.WebDriverBase;

public class CSVTestDataReader implements ITestDataReader {

	@Override
	public Object[][] getTestData(String testId) {
		
		String csvFileLoc = WebDriverBase.property.loadProperty("testdata.csvLoc");
		
		String line = "";
        String cvsSplitBy = ",";

        List<Object[]> testDataList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileLoc))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);
                testDataList.add(row);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Object[][] testData = new Object[testDataList.size()][]; 
        int index = 0;
        for(Object[] testRow : testDataList) {
        	testData[index++] = new Object[]{testRow};
        }
        
		// TODO Auto-generated method stub
		return testData;
	}

	
	
}
