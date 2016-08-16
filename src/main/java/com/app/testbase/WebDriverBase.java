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
public class WebDriverBase {
    static ThreadLocal<WebDriver> threadDriver= new ThreadLocal<>();
    protected WebDriver _driver;
    protected String websiteUrl;
    protected String hubUrl;

    public static final PropertyLoader property= new PropertyLoader();


    private final static Logger logger = LoggerFactory.getLogger(WebDriverBase.class);

    @BeforeClass
    public void init()  {
        logger.info("Thread id = "+ Thread.currentThread().getId());
        logger.info("Hashcode of Web Driver: "+WebDriverBase.getDriver().hashCode());
        WebDriverBase.getDriver();
    }

    // Implementation of page object
    protected PageObject _pageObject;

    public  static WebDriver getDriver(){
        return threadDriver.get();
        //return _driver;
    }

    public static void setDriver(WebDriver driver){
        threadDriver.set(driver);
        getDriver().manage().window().maximize();
    }

    /*
    * This method is empty to be Override
     */
    protected void initPageObject(){}


    @AfterClass(alwaysRun = true)
    public void tearDown(){
        _driver = WebDriverBase.getDriver();
        if (_driver != null) {
            _driver.quit();
        }

    }
}