package com.app.reusable;

import com.app.testbase.WebDriverBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by OS344312 on 8/17/2016.
 */
public class OnSuccess extends TestListenerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(OnSuccess.class);

    @Step("Failure captured, documented with: ")
    @Override
    public void onTestSuccess(ITestResult tr) {
        createAttachment(tr);
        screenCapture(tr);
    }

    @Attachment("Test Case Name:")
    private String createAttachment(ITestResult tr) {
        return tr.getName();
    }


    @Attachment(value = "Screenshot Captured on Success !!", type = "image/png")
    public byte[] screenCapture(ITestResult tr) {

        String imgPath = "./target/" + tr.getName() + "_OnSuccess.png";
        //String path = tr.getName() + ".png";
        File screenshot = new File(imgPath);
        try {
            FileOutputStream screenshotStream = new FileOutputStream(screenshot, false);
            //WebDriver augmentedDriver = new Augmenter().augment((((WebDriverBase)tr.getInstance()).getDriver()));
            WebDriver augmentedDriver = new Augmenter().augment(WebDriverBase.getThreadDriver());
            byte[] bytes = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
            screenshotStream.write(bytes);
            screenshotStream.close();
            return bytes;
        } catch (IOException unableToWriteScreenshot) {
            System.err.println("Unable to write " + screenshot.getAbsolutePath());
            logger.info("Context", Arrays.toString(unableToWriteScreenshot.getStackTrace()));
        }
        return null;
    }
}
