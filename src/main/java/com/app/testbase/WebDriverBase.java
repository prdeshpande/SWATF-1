package com.app.testbase;

import com.app.testdata.ITestDataReader;
import com.app.testdata.TestDataReaderFactory;
import com.app.utils.PropertyLoader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
@Features("Smoke Suite")
@Stories("Set of test that integrate the Smoke suite")
public class WebDriverBase {
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

    //@BeforeClass
    public void init() throws IOException, InterruptedException {

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
                logger.info("Chrome browser selected");
                break;
            case BROWSER_FIREFOX:
                //firefoxBin = property.loadProperty("firefox.binary");
                desiredCapabilities = DesiredCapabilities.firefox();
                //desiredCapabilities.setCapability("firefox.binary", firefoxBin);
                logger.debug("Firefox browser selected");
                break;
            case BROWSER_PHANTOMJS: desiredCapabilities = DesiredCapabilities.phantomjs(); break;
            default:
                logger.debug("Could not find any supported browser");
                throw new IllegalArgumentException("Could not find supported browser: " + browserName);
        }

        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability("takesScreenshot", false);
        desiredCapabilities.setBrowserName(browserName);

        _driver = new RemoteWebDriver(new URL(hubUrl), desiredCapabilities);
        _driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        _driver.manage().window().maximize();
    }

    // Implementation of page object
    protected PageObject _pageObject;

    public  WebDriver getDriver(){
        return _driver;
    }

    protected void initPageObject(){}


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception{
        _driver.close();
        _driver.quit();
    }
    
    @DataProvider(name="testDataProvider")
    public Object[][] testDataProvider(Method method, ITestContext testContext) {
    	
    	//testContext.
    	
    	String testDataSource = property.loadProperty("testdata.sourceType");
    	
    	ITestDataReader testDatReader = TestDataReaderFactory.getTestDataReader(testDataSource);
    	
    	return testDatReader.getTestData(method.getName());
    }
    
}