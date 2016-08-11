package com.app.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.testbase.WebDriverBase;

public class DataProviderTest extends WebDriverBase {
	
	@Test(dataProvider="testDataProvider")
    public void test(Object[] data) {
		System.err.println(data);
		for(Object d : data) {
			System.out.println(d);
		}
    	
    }

}
