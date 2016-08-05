package com.app.testcases;

import com.app.pom.WebPage2;
import com.app.testbase.WebDriverBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Oscar Garcia on 8/4/2016.
 */
public class WebPage2Test extends WebDriverBase{
    WebPage2 _page;

    @BeforeClass
    public void testInit(){
        _driver.get(websiteUrl);
        initPageObject();
    }

    @Override
    protected void initPageObject(){
        try {
            //strong typed page object
            _page = new WebPage2(_driver, _driver.getCurrentUrl());
            //page object used by the base class
            _pageObject = _page;
            _pageObject.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups ={"smoke"}, testName = "TC03_HomePage")
    public void TC03_HomePage() throws InterruptedException {
        _page.login("tkmad3j","F3rn4nd4");
        Assert.assertTrue(true);
    }

    @AfterMethod
    @Attachment(value = "Screenshot on failure captured", type = "image/png")
    public byte[] screenCapture(ITestResult tr){

        if(tr.getStatus()==ITestResult.FAILURE) {
            String imgPath = "./target/" + tr.getName() + ".png";
            String path = tr.getName() + ".png";
            File screenshot = new File(imgPath);
            try {
                FileOutputStream screenshotStream = new FileOutputStream(screenshot);
                byte[] bytes = ((TakesScreenshot) _driver)
                        .getScreenshotAs(OutputType.BYTES);
                screenshotStream.write(bytes);
                screenshotStream.close();
                return bytes;
            } catch (IOException unableToWriteScreenshot) {
                System.err.println("Unable to write "
                        + screenshot.getAbsolutePath());
                unableToWriteScreenshot.printStackTrace();
            }
        }
        return null;
    }

}
