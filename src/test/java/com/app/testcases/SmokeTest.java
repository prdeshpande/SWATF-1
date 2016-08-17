package com.app.testcases;


import com.app.pom.WebPage_LoginPage;
import com.app.pom.WebPage_SNHome;
import com.app.testbase.WebDriverBase;
import com.app.utils.JSHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.Class;
import java.util.Arrays;
//import org.junit.Test;



/**
 * Created by OS344312 on 8/8/2016.
 */
@Features("Feature: Smoke Suite")
@Stories(" Story Set: Set of test cases that integrate the Smoke suite")
public class SmokeTest extends WebDriverBase{

    // List of objects that would reference the Pages (views)
    WebPage_LoginPage pageLP= null;
    WebPage_SNHome pageSN = null;

    private final static Logger logger = LoggerFactory.getLogger(SmokeTest.class);

    public SmokeTest() throws IOException {
    }


    protected <E> void performMethod(E page, String meth) throws ClassNotFoundException {
        E myObjClass = page;
        Class _class = null;
        _class = Class.forName(page.getClass().getName());
        ;
        try {

        for(Method me: _class.getMethods()){
            System.out.println(me.getName());
            if( me.getName().contains(meth))
                   myObjClass.getClass().getMethod((meth)).invoke(myObjClass);;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

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


    //@BeforeMethod
    public void injectJQ(){
        JSHelper.injectJQ(WebDriverBase.getThreadDriver());
        //JSHelper.injectUS(_driver);
        logger.info("Happening Before Test");
        JSHelper.forceJQ(WebDriverBase.getThreadDriver());
    }


    //@org.junit.Test
    @Test (groups = {"SmokeSuite"}, testName = "Redirection Login to Kohl's from web")
    public void KohlsLoginFromWeb() throws IOException, ClassNotFoundException, InterruptedException {
        //Open URL
        WebDriverBase.getThreadDriver().get(websiteUrl);
        // Initialize the page object
        pageLP= new WebPage_LoginPage(WebDriverBase.getThreadDriver(), WebDriverBase.getThreadDriver().getCurrentUrl());
        _pageObject = pageLP;
        // Method to init the new page and apply the approach
        injectJQ();
        pageLP.doLogin();
    }

    @Stories("Story: Kohls.com Navigation")
    @Test (groups = {"SmokeSuite"})
    public void KohlsDotCom(){
        WebDriverBase.getThreadDriver().get("http://www.kohls.com");
    }

    @Test (groups = {"SmokeSuite"})
    public void GoogleThread(){
        WebDriverBase.getThreadDriver().get("http://www.google.com");
    }


    @org.junit.Test
    @Stories("Story: Service Now Navigation")
    @Test(groups={"SmokeSuite", "ServiceNow"}, description = "Looking for the Home Page Elements of the Service Now Home Page", dependsOnMethods = "KohlsLoginFromWeb", testName = "Playing at Service Now Site")
    public void ServiceNowNavigation() throws IOException {
        //initPage();
        WebDriverBase.getThreadDriver().get(websiteUrl);
        pageLP= new WebPage_LoginPage(WebDriverBase.getThreadDriver(), WebDriverBase.getThreadDriver().getCurrentUrl());
        injectJQ();
        pageLP.doLogin();
        pageSN= new WebPage_SNHome(WebDriverBase.getThreadDriver(), WebDriverBase.getThreadDriver().getCurrentUrl());
        injectJQ();
        pageSN.findItLink();
        pageSN.serviceCatalog();
        pageSN.homeLink();
    }

    @org.junit.Test
    @Test (groups = {"SmokeSuite"})
    public void FailTest() throws IOException {
        //initPage();
        WebDriverBase.getThreadDriver().get(websiteUrl);
        pageLP= new WebPage_LoginPage(WebDriverBase.getThreadDriver(), WebDriverBase.getThreadDriver().getCurrentUrl());
        injectJQ();
        pageLP.doLogin();
        pageSN= new WebPage_SNHome(WebDriverBase.getThreadDriver(), WebDriverBase.getThreadDriver().getCurrentUrl());
        injectJQ();
        pageSN.mustFail();
    }



}
