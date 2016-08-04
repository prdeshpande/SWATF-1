package com.app.objects;

import com.app.reusable.Component;
import org.openqa.selenium.WebDriver;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
public class TextField extends Component{

    public TextField(WebDriver driver, String query, Component parent) {
        super(driver, query, parent);
    }

    public void searchKeyword(String value){
        getElement().sendKeys(value);
    }
}