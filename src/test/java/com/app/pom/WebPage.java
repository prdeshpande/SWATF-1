package com.app.pom;

import com.app.objects.Link;
import com.app.objects.TextField;
import com.app.testbase.PageObject;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
public class WebPage extends PageObject {

    /*
    * This is the old way to do POM
     */
    private TextField searchField;
    private Link signIn;

    public WebPage(WebDriver driver, String url) throws IOException {
        super(driver, url);
        initObjects();
    }

    @Step
    public void initObjects(){
        searchField = new TextField(_driver,"input#search.input-text",null);
        signIn = new Link(_driver,"a#pb_signin.utility-item-link",null);
    }

    public void search(String value){
        searchField.searchKeyword(value);
    }

    public  void loginPage(){
        signIn.click();
    }
}
