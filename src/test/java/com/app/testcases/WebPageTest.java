package com.app.testcases;

import com.app.pom.WebPage;
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
 * Created by Oscar Garcia on 8/2/2016.
 */
public class WebPageTest extends WebDriverBase{

    WebPage _page;

    @BeforeClass
    public void testInit(){
        _driver.get(websiteUrl);
       initPageObject();
    }

    @Override
    protected void initPageObject(){
        try {
            //strong typed page object
            _page = new WebPage(_driver, _driver.getCurrentUrl());
            //page object used by the base class
            _pageObject = _page;
            _pageObject.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(groups ={"smoke"}, testName = "TC01_Home")
    public void TC01_Home() throws InterruptedException {
        _page.search("Nalgas");
        Assert.assertTrue(true);
    }

    @Test (groups ={"smoke"}, testName = "TC02_Search")
    public void TC02_Search() {
        _page.loginPage();
        Assert.assertTrue(true);
    }

    @Test (enabled = false)
    public void playButton3() {
        Assert.assertTrue(false);
    }

    @AfterMethod
    @Attachment(value = "Screenshot on failure captured", type = "image/png")
    public byte[] screenCapture(ITestResult tr){

        if(tr.getStatus()==ITestResult.FAILURE) {
            String imgPath = "./target/" + tr.getName() + ".png";
            String path = tr.getName() + ".png";
            File screenshot = new File(imgPath);
            try {
                FileOutputStream screenshotStream = new FileOutputStream(screenshot, false);
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
