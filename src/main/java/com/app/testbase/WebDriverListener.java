package com.app.testbase;

import com.app.reusable.OnFailure;
import com.app.reusable.OnSuccess;
import com.app.utils.PropertyLoader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;


/**
 * Created by OS344312 on 8/16/2016.
 */
public class WebDriverListener implements IInvokedMethodListener {

    public static final PropertyLoader property= new PropertyLoader();
    OnFailure of = new OnFailure();
    OnSuccess os = new OnSuccess();

    /*
    * This would create a Web Driver instance for each test method
     */
    @Step ("Launching Web Driver Instance creation ")
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            //String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
            String browserName = property.loadProperty("browser.name");
            WebDriver driver = WebDriverFactory.createInstance(browserName);
            WebDriverBase.setThreadDriver(driver);
        }
    }


    @Step ("Web Driver Instance in progress to be close and clean")
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        //Screenshot
        if (testResult.isSuccess()){
            os.screenCapture(testResult);
        }else{
            of.onTestFailure(testResult);
        }
        if (method.isTestMethod()) {
            WebDriver driver = WebDriverBase.getThreadDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}