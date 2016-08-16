package com.app.testbase;

import com.app.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
public class WebDriverBase {
    static ThreadLocal<WebDriver> threadDriver= new ThreadLocal<>();
    protected WebDriver _driver;
    protected String websiteUrl;
    protected String hubUrl;
    protected String browserName;
    protected String firefoxBin;
    protected String chromeBinary;
    protected String chromeDriver;
    protected String userName;
    protected String password;
    public static final PropertyLoader property= new PropertyLoader();

    protected static final String BROWSER_CHROME    = "chrome";
    protected static final String BROWSER_FIREFOX   = "firefox";
    protected static final String BROWSER_PHANTOMJS = "phantomjs";

    private final static Logger logger = LoggerFactory.getLogger(WebDriverBase.class);

    @BeforeClass
    public void init()  {

        websiteUrl = property.loadProperty("site.url");
        hubUrl = property.loadProperty("hub.address");
        browserName = property.loadProperty("browser.name");
        userName = property.loadProperty("username.value");
        password = property.loadProperty("password.value");

        DesiredCapabilities desiredCapabilities;


        switch (browserName) {
            case BROWSER_CHROME:
                chromeDriver = property.loadProperty("chrome.driverLoc");
                chromeBinary = property.loadProperty("chrome.binaryLoc");
                System.setProperty("webdriver.chrome.driver", chromeDriver);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                options.addArguments("disable-popup-blocking");
                desiredCapabilities = DesiredCapabilities.chrome();
                desiredCapabilities.setCapability("chrome.binary", chromeBinary);
                break;
            case BROWSER_FIREFOX:
                //firefoxBin = property.loadProperty("firefox.binary");
                desiredCapabilities = DesiredCapabilities.firefox();
                break;
            case BROWSER_PHANTOMJS: desiredCapabilities = DesiredCapabilities.phantomjs(); break;
            default:
                throw new IllegalArgumentException("Could not find supported browser: " + browserName);
        }

        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("takesScreenshot", false);
        desiredCapabilities.setBrowserName(desiredCapabilities.getBrowserName());

        logger.info("The WebDriver is running on the following web browser: "+(desiredCapabilities.getBrowserName()).toUpperCase());

        try {
            threadDriver.set(new RemoteWebDriver(new URL(hubUrl), desiredCapabilities));
            //_driver = new RemoteWebDriver(new URL(hubUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            logger.info("Context",Arrays.toString(e.getStackTrace()));
        }
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    // Implementation of page object
    protected PageObject _pageObject;

    public  static WebDriver getDriver(){
        return threadDriver.get();
        //return _driver;
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