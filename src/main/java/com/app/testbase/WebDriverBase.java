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


    private static ThreadLocal<WebDriver> threadDriver= new ThreadLocal<>();
    protected WebDriver _driver;
    protected static String websiteUrl;
    protected static String userName;
    protected static String password;

    public static final PropertyLoader property= new PropertyLoader();
    private final static Logger logger = LoggerFactory.getLogger(WebDriverBase.class);


    public static WebDriver getThreadDriver() {
        return threadDriver.get();
    }

    public static void setThreadDriver(WebDriver driver) {
        threadDriver.set(driver);
        websiteUrl = property.loadProperty("site.url");
    }
    /*
    @BeforeClass
    public void init()  {
        userName = property.loadProperty("username.value");
        password = property.loadProperty("password.value");
        websiteUrl = property.loadProperty("site.url");
    }*/

    // Implementation of page object
    protected PageObject _pageObject;



    /*
    * This method is empty to be Override
     */
    protected void initPageObject(){}

    /*
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        _driver = WebDriverBase.getDriver();
        if (_driver != null) {
            _driver.quit();
        }

    }*/
}