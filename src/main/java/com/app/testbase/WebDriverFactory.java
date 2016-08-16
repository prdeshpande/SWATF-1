package com.app.testbase;

import com.app.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


/**
 * Created by OS344312 on 8/16/2016.
 */
public class WebDriverFactory {


    protected static String hubUrl;
    protected static String firefoxBin;
    protected static String chromeBinary;
    protected static String chromeDriver;

    protected static final String BROWSER_CHROME    = "chrome";
    protected static final String BROWSER_FIREFOX   = "firefox";

    public static final PropertyLoader property= new PropertyLoader();
    private final static Logger logger = LoggerFactory.getLogger(PropertyLoader.class);

    static WebDriver createInstance(String browserName){
        WebDriver driver=null;

        // Getting the properties
        hubUrl = property.loadProperty("hub.address");
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
                desiredCapabilities.setJavascriptEnabled(true);
                desiredCapabilities.setBrowserName(desiredCapabilities.getBrowserName());

                break;
            case BROWSER_FIREFOX:
                //firefoxBin = property.loadProperty("firefox.binary");
                desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setJavascriptEnabled(true);
                desiredCapabilities.setBrowserName(desiredCapabilities.getBrowserName());
                break;
            default:
                throw new IllegalArgumentException("Could not find supported browser: " + browserName);
        }
        try {
            driver = (new RemoteWebDriver(new URL(hubUrl), desiredCapabilities));
        } catch (MalformedURLException e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        }
        return driver;
    }


}
