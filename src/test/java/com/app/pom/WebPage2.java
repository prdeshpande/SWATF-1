package com.app.pom;

import com.app.objects.Button;
import com.app.objects.TextField;
import com.app.testbase.PageObject;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

/**
 * Created by Oscar Garcia on 8/4/2016.
 */
public class WebPage2 extends PageObject{

    private TextField username=null;
    private TextField password=null;
    private Button loginBtn=null;


    public WebPage2(WebDriver driver, String url) throws IOException {
        super(driver, url);
        initObjects();
    }

    @Step
    public void initObjects(){
        username = new TextField(_driver,"input#form-username",null);
        password = new TextField(_driver,"input#form-password",null);
        loginBtn = new Button(_driver,"input#btnSubmit.button", null);
    }

    public void login(String user, String Password){
        username.sendKeys(user);
        password.searchKeyword(Password);
        loginBtn.click();
    }
}
