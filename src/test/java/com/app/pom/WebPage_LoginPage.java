package com.app.pom;

import com.app.objects.Button;
import com.app.objects.Link;
import com.app.objects.TextField;
import com.app.testbase.PageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

/**
 * Created by OS344312 on 8/8/2016.
 */
public class WebPage_LoginPage extends PageObject{

    WebPage_SNHome pageHP;
    PageObject _page;

    private TextField username;
    private TextField password;
    private Button  submit;

    public WebPage_LoginPage(WebDriver driver, String url) throws IOException {
        super(driver, url);
        initObjects();
    }

    @Step ("Objects initialization")
    public void initObjects(){
        username = new TextField(_driver,"input#form-username",null);
        password = new TextField(_driver,"input#form-password",null);
        submit = new Button(_driver,"input#btnSubmit.button",null);
    }

    // Initialization of the pages needed to run the smoke suite
    @BeforeGroups("ServiceNow")
    public void groupInit(){
        try {
            pageHP = new WebPage_SNHome(_driver, _driver.getCurrentUrl());
            _page = pageHP;
            _page.load();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Step ("Login function")
    public void doLogin(String value1, String value2){
        username.sendKeys(value1);
        password.sendKeys(value2);
        submit.click();
    }

}
