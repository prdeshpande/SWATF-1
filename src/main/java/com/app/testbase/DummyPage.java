package com.app.testbase;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by OS344312 on 8/16/2016.
 */
public class DummyPage extends PageObject {

    public DummyPage(WebDriver driver, String url) throws IOException {
        super(driver, url);
    }
}
