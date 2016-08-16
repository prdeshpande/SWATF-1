package com.app.testbase;

import com.app.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * Created by OS344312 on 8/16/2016.
 */
public class WebDriverListener implements IInvokedMethodListener {

    public static final PropertyLoader property= new PropertyLoader();

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            //String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            String browserName = property.loadProperty("browser.name");
            WebDriver driver = WebDriverFactory.createInstance(browserName);
            WebDriverBase.setThreadDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = WebDriverBase.getThreadDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}