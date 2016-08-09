package com.app.pom;

import com.app.testbase.PageObject;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by OS344312 on 8/9/2016.
 */
public class DummyClass extends PageObject {
    public DummyClass(WebDriver driver, String url) throws IOException {
        super(driver, url);
    }
}
