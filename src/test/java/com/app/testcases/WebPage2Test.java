package com.app.testcases;

import com.app.pom.WebPage2;
import com.app.testbase.WebDriverBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    @Test(groups ={"smoke"}, testName = "TC01_Home")
    public void TC01_Home() throws InterruptedException {
        _page.login("tkmad3j","F3rn4nd4");
        Assert.assertTrue(true);
    }
}
