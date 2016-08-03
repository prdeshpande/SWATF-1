package com.app.testcases;

import com.app.pom.WebPage;
import com.app.testbase.WebDriverBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        _page.searchField.enterValue("Beach");
        Assert.assertTrue(true);
        Thread.sleep(5000);

    }

    @Test (groups ={"smoke"}, testName = "TC02_Search")
    public void TC02_Search() {
        Assert.assertTrue(true);

    }

    @Test (enabled = false)
    public void playButton3() {
        Assert.assertTrue(false);

    }


}
