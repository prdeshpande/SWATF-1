package com.app.objects;

import com.app.reusable.Component;
import org.openqa.selenium.WebDriver;

/**
 * Created by OS344312 on 8/3/2016.
 */
public class Link extends Component{

    public Link(WebDriver driver, String query, Component parent) {
        super(driver, query, parent);
    }

}
