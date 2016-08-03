package com.app.pom;

import com.app.objects.TextField;
import com.app.testbase.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
public class WebPage extends PageObject {

    /*
    * This is the old way to do POM
     */
    @FindBy(id="search")
    private WebElement searchBox;

    public TextField searchField;

    public WebPage(WebDriver driver, String url) throws IOException {
        super(driver, url);
        initObjects();
    }

    @Step
    public void initObjects(){
        searchField = new TextField(_driver,"input#search.input-text",null);
    }
}
