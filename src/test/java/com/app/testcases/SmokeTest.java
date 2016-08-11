package com.app.testcases;

import com.app.pom.WebPage_LoginPage;
import com.app.pom.WebPage_SNHome;
import com.app.testbase.PageObject;
import com.app.testbase.WebDriverBase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;

/**
 * Created by OS344312 on 8/8/2016.
 */
@Features("Smoke Suite")
@Stories("Set of test that integrate the Smoke suite")
public class SmokeTest extends WebDriverBase{

    WebPage_LoginPage pageLP;


    @BeforeClass
    public void testInit(){
        // Load of the initial Web URL
        _driver.get(websiteUrl);
        // Initialization of the page to create the objects
        initPageObject();
    }

    @Override
    protected void initPageObject(){
        try{
            pageLP = new WebPage_LoginPage(_driver, _driver.getCurrentUrl());
            _pageObject = pageLP;
            _pageObject.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Test (groups = {"SmokeSuite"})
    public void loginPage(){
        pageLP.doLogin(userName,password);
    }

    /*

    @Test(groups={"SmokeSuite", "ServiceNow"}, description = "Looking for the Home Page Elements of the Service Now Home Page", dependsOnMethods = "loginPage")
    public void homePageCheck(){
        pageHP.checkHomeLink();
    }*/


}
