package com.app.testcases;

import com.app.pom.DummyClass;
import com.app.pom.WebPage_LoginPage;
import com.app.pom.WebPage_SNHome;
import com.app.testbase.WebDriverBase;
import com.app.utils.JSHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;



/**
 * Created by OS344312 on 8/8/2016.
 */
@Features("Smoke Suite")
@Stories("Set of test that integrate the Smoke suite")
public class SmokeTest extends WebDriverBase{


    DummyClass dummyPage;
    WebPage_LoginPage pageLP;
    WebPage_SNHome pageSN;

    private final static Logger logger = LoggerFactory.getLogger(SmokeTest.class);

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
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void initPage(){
        try{
            pageSN = new WebPage_SNHome(_driver, _driver.getCurrentUrl());
            _pageObject = pageSN;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    @BeforeMethod
    public void injectJQ(){
        JSHelper.injectJQ(_driver);
        //JSHelper.injectUS(_driver);
        logger.info("Happening Before Test");
        JSHelper.forceJQ(_driver);
    }


    @Test (groups = {"SmokeSuite"}, testName = "Redirection Login to Kohl's from web")
    public void KohlsLoginFromWeb(){
        if (_driver.getCurrentUrl().toLowerCase().toString() == "https://kohls.service-now.com/nav_to.do?uri=%2Fhome.do"){
            // This is to skip login process if no redirection (Kohl's domain)
            Assert.assertTrue(true);
        }else {
            pageLP.doLogin(userName, password);
        }
    }



    @Test(groups={"SmokeSuite", "ServiceNow"}, description = "Looking for the Home Page Elements of the Service Now Home Page", dependsOnMethods = "KohlsLoginFromWeb", testName = "Playing at Service Now Site")
    public void ServiceNowNavigation(){
        initPage();
        pageSN.findItLink();
        pageSN.serviceCatalog();
        pageSN.homeLink();
    }

    @Test (groups = {"SmokeSuite"}, dependsOnMethods = "KohlsLoginFromWeb")
    public void DemoFailureTest(){
        initPage();
        pageSN.mustFail();
    }



}
