package com.app.pom;

import com.app.objects.Button;
import com.app.objects.Link;
import com.app.objects.TextField;
import com.app.testbase.PageObject;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

/**
 * Created by OS344312 on 8/8/2016.
 */
public class WebPage_SNHome extends PageObject {

    private Link checkIn;
    private Link findit;


    public WebPage_SNHome(WebDriver driver, String url) throws IOException {
        super(driver, url);
        initObjects();
    }

    @Step
    public void initObjects(){
        checkIn = new Link(_driver,"h2:contains('Check In')",null);
        findit = new Link(_driver,"h2:contains('Find it')",null);
    }

    @Step ("Home Link Verification")
    public void checkHomeLink(){
        checkIn.click();
        findit.click();
    }
}
