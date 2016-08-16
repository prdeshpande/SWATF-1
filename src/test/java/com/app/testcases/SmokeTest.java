package com.app.testcases;

import com.app.pom.WebPage_LoginPage;
import com.app.pom.WebPage_SNHome;
import com.app.testbase.WebDriverBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
//import org.junit.Test;



/**
 * Created by OS344312 on 8/8/2016.
 */
@Features("Smoke Suite")
@Stories("Set of test that integrate the Smoke suite")
public class SmokeTest extends WebDriverBase{


    WebPage_LoginPage pageLP;
    WebPage_SNHome pageSN;

    private final static Logger logger = LoggerFactory.getLogger(SmokeTest.class);

    /*
    @BeforeClass
    public void testInit(){
        // Load of the initial Web URL
        logger.info("Thread id = "+ Thread.currentThread().getId());
        logger.info("Hashcode of Web Driver: "+WebDriverBase.getDriver().hashCode());
        WebDriverBase.getDriver().get(websiteUrl);
        initPageObject();
    }*/

    /*
    @Override
    protected void initPageObject(){
        try{
            pageLP = new WebPage_LoginPage(WebDriverBase.getDriver(), WebDriverBase.getDriver().getCurrentUrl());
            _pageObject = pageLP;
        } catch (Exception e) {
            logger.info("Context", Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();

        }
    }*/

    /*
    private void initPage(){
        try{
            pageSN = new WebPage_SNHome(WebDriverBase.getDriver(), WebDriverBase.getDriver().getCurrentUrl());
            _pageObject = pageSN;
        }catch (Exception e){
            logger.info("Context", Arrays.toString(e.getStackTrace()));
            throw new RuntimeException();
        }
    }*/

    /*
    @BeforeMethod
    public void injectJQ(){
        JSHelper.injectJQ(WebDriverBase.getDriver());
        //JSHelper.injectUS(_driver);
        logger.info("Happening Before Test");
        JSHelper.forceJQ(WebDriverBase.getDriver());
    }*/


    @org.junit.Test
    @Test (groups = {"SmokeSuite"}, testName = "Redirection Login to Kohl's from web")
    public void KohlsLoginFromWeb(){
        WebDriverBase.getDriver().get(websiteUrl);
        pageLP.doLogin(userName, password);
        /*
        if (getDriver().getCurrentUrl().toLowerCase().toString() == "https://kohls.service-now.com/nav_to.do?uri=%2Fhome.do"){
            // This is to skip login process if no redirection (Kohl's domain)
            Assert.assertTrue(true);
        }else {

        }*/
    }

    @org.junit.Test
    @Test(groups={"SmokeSuite", "ServiceNow"}, description = "Looking for the Home Page Elements of the Service Now Home Page", dependsOnMethods = "KohlsLoginFromWeb", testName = "Playing at Service Now Site")
    public void ServiceNowNavigation(){
        //initPage();
        WebDriverBase.getDriver().get(websiteUrl);
        pageLP.doLogin(userName, password);
        pageSN.findItLink();
        pageSN.serviceCatalog();
        pageSN.homeLink();
    }

    @org.junit.Test
    @Test (groups = {"SmokeSuite"}, dependsOnMethods = "KohlsLoginFromWeb")
    public void DemoFailureTest(){
        //initPage();
        WebDriverBase.getDriver().get(websiteUrl);
        pageLP.doLogin(userName, password);
        pageSN.mustFail();
    }



}
