package com.app.objects;

import com.app.reusable.Component;
import org.openqa.selenium.WebDriver;

/**
 * Created by Oscar Garcia on 8/4/2016.
 */
public class Button extends Component {

    public Button(WebDriver driver, String query, Component parent) {
        super(driver, query, parent);
    }
}
