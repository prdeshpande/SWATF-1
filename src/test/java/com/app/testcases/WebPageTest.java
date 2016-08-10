package com.app.testcases;

import com.app.pom.WebPage;
import com.app.testbase.WebDriverBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */

@Features("Demostration of the Features annotation")

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
    @Stories("Search Functionality")
    @Test(groups ={"smoke"}, testName = "TC01_Home")
    public void TC01_Home(){
        _page.search("Summer");
    }

    @Stories("Root Search")
    @Test (groups ={"smoke"}, testName = "TC02_Search", enabled = true)
    public void TC02_Search() {
        _page.loginPage();
    }

    @Test (enabled = false)
    public void playButton3() {
        Assert.assertTrue(false);
    }


}
