package com.app.pom;

import com.app.objects.Link;
import com.app.testbase.PageObject;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

/**
 * Created by OS344312 on 8/8/2016.
 */
public class WebPage_SNHome extends PageObject {

    private Link home;
    private Link findit;
    private Link serviceCat;
    private Link failLink;


    public WebPage_SNHome(WebDriver driver, String url) throws IOException {
        super(driver, url);
        initObjects();
    }

    @Step ("Step 1: Object Initialization")
    public void initObjects(){
        home = new Link(_driver,"a[id^=\"menu_\"]",null);
        findit = new Link(_driver,"a[id^=\"menu_\"]:contains(\"Order It\")",null);
        serviceCat = new Link(_driver,"a.cms_menu_block_item:contains(\"Service Catalog\")",null);
        failLink = new Link(_driver,"NoValidElement",null);
    }

    @Step ("Step 2: Click on Find It Link")
    public void findItLink(){
        findit.click();
    }

    @Step ("Step 3: Click on Service Catalog Link")
    public void serviceCatalog(){
        serviceCat.click();
    }

    @Step ("Step 4: Click on Home Link")
    public void homeLink(){
        home.click();
    }

    @Step ("Step 5: Intentional Failure")
    public void mustFail(){
        failLink.click();
    }
}
