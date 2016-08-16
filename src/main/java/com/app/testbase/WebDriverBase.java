package com.app.testbase;

import com.app.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
public abstract class WebDriverBase {


    private static ThreadLocal<WebDriver> threadDriver= new ThreadLocal<>();
    protected WebDriver _driver;
    protected static String websiteUrl;


    public static final PropertyLoader property= new PropertyLoader();
    //private final static Logger logger = LoggerFactory.getLogger(WebDriverBase.class);


    public static WebDriver getThreadDriver() {
        return threadDriver.get();
    }

    public static void setThreadDriver(WebDriver driver) {
        threadDriver.set(driver);
        websiteUrl = property.loadProperty("site.url");
    }


    // Implementation of page object
    protected PageObject _pageObject;



    /*
    * This method is empty to be Override
     */
    protected <E> void initPageObject(Class <E> E){}

    protected abstract <E> void initPageObject(E page);


}