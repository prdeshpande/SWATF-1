package com.app.testcases;

import com.app.testbase.DummyPage;
import com.app.pom.WebPage_LoginPage;
import com.app.pom.WebPage_SNHome;
import com.app.testbase.DummyPage;
import com.app.testbase.PageObject;
import com.app.testbase.WebDriverBase;
import com.app.utils.JSHelper;
import org.apache.tools.ant.taskdefs.ManifestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
//import org.junit.Test;



/**
 * Created by OS344312 on 8/8/2016.
 */
@Features("Smoke Suite")
@Stories("Set of test that integrate the Smoke suite")
public class SmokeTest extends WebDriverBase{

    // List of objects that would reference the Pages (views)

    WebPage_LoginPage pageLP= null;
    WebPage_SNHome pageSN = null;



    private Object obj=null;

    private final static Logger logger = LoggerFactory.getLogger(SmokeTest.class);

    public SmokeTest() throws IOException {
    }

    /*
    @BeforeClass
    public void testInit(){
        // Load of the initial Web URL
        logger.info("Thread id = "+ Thread.currentThread().getId());
        logger.info("Hashcode of Web Driver: "+WebDriverBase.getDriver().hashCode());
        WebDriverBase.getDriver().get(websiteUrl);
        initPageObject();
    }*/


    @Override
    protected <E> void initPageObject(E page){

        E myObjClass = page;
        obj = myObjClass;

        try {
            myObjClass.getClass().getMethod("initObjects").invoke(myObjClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    protected <E> void performMethod(E page, String meth){
        E myObjClass = page;
        try {

for(Method me: myObjClass.getClass().getDeclaredMethods()){
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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
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


    @org.junit.Test
    @Test (groups = {"SmokeSuite"}, testName = "Redirection Login to Kohl's from web")
    public void KohlsLoginFromWeb() throws IOException {
        //Open URL
        WebDriverBase.getThreadDriver().get(websiteUrl);
        // Initialize the page object
        pageLP= new WebPage_LoginPage(WebDriverBase.getThreadDriver(), WebDriverBase.getThreadDriver().getCurrentUrl());
        // Method to init the new page and apply the approach
        initPageObject(pageLP.getClass());
        injectJQ();
        performMethod(pageLP.getClass(),"doLogin");

    }

    /*
    @org.junit.Test
    @Test(groups={"SmokeSuite", "ServiceNow"}, description = "Looking for the Home Page Elements of the Service Now Home Page", dependsOnMethods = "KohlsLoginFromWeb", testName = "Playing at Service Now Site")
    public void ServiceNowNavigation(){
        //initPage();
        WebDriverBase.getThreadDriver().get(websiteUrl);

        pageSN.findItLink();
        pageSN.serviceCatalog();
        pageSN.homeLink();
    }

    @org.junit.Test
    @Test (groups = {"SmokeSuite"}, dependsOnMethods = "KohlsLoginFromWeb")
    public void DemoFailureTest(){
        //initPage();
        WebDriverBase.getThreadDriver().get(websiteUrl);

        pageSN.mustFail();
    }
    */


}
