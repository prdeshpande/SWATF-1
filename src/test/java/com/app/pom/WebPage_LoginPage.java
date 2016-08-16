package com.app.pom;

import com.app.objects.Button;
import com.app.objects.TextField;
import com.app.testbase.PageObject;
import com.app.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

/**
 * Created by OS344312 on 8/8/2016.
 */
public class WebPage_LoginPage extends PageObject{


    private TextField username;
    private TextField password;
    private Button  submit;

    protected static String userNameProp;
    protected static String passwordProp;

    public static final PropertyLoader property= new PropertyLoader();


    public WebPage_LoginPage(WebDriver driver, String url) throws IOException {
        super(driver, url);
    }

    @Step ("Step 1: Objects initialization")
    public void initObjects(){

        username = new TextField(_driver,"input#form-username",null);
        password = new TextField(_driver,"input#form-password",null);
        submit = new Button(_driver,"input#btnSubmit.button",null);
    }

    @Step ("Step 2: Perform login process")
    public void doLogin(){
        userNameProp = property.loadProperty("username.value");
        passwordProp = property.loadProperty("password.value");
        username.sendKeys(userNameProp);
        password.sendKeys(passwordProp);
        submit.click();
    }

}
