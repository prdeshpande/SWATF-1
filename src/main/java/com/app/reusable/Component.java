package com.app.reusable;

import com.app.utils.Conversion;
import com.app.utils.JSHelper;
import com.app.utils.PropertyLoader;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.io.Resources;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
//public abstract class Component implements WebElement {
public abstract class Component {

    private final static Logger logger = LoggerFactory.getLogger(Component.class);
    protected WebDriver _driver;
    protected int retry=0;
    /**
     * ExtJS component query that can uniquely identify this component.
     */
    protected String _query;
    protected String _HTMLID;
    protected WebElement _element;
    protected Component _parent = null;
    private Boolean flag = false;

    @SuppressWarnings("unused")
    private int iWait;
    public static PropertyLoader property = new PropertyLoader();


    public Component(WebDriver driver, String query, Component parent) {
        _driver = driver;
        _query = query;
        _parent = parent;
    }

    /*
     * Get the WebElement using JQuery to be pass to WebDriver
     */
    public WebElement getElement() {
        if (waitForRendered()){
            String xp = null;
            try {
                logger.info("Returning XPath...");
                xp = returnXpath(_driver,"buildXpath.js");
                logger.info("XPath build: "+xp);
                return _driver.findElement(By.xpath(xp));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }//logger.info("Xpath value: "+xp);
        }else{
            logger.info("--------------------------------------------");
            logger.info("No element found visible");
            logger.info("--------------------------------------------");
            logger.info("Injecting JQuery and trying again");
            logger.info("--------------------------------------------");
            logger.info("Trying: "+ retry);
            return retryFunction();
        }
        //String id = waitForComponent();
    }

    private WebElement retryFunction() {
        retry++;
        if (retry <2){
            JSHelper.forceJQ(_driver);
            return getElement();
        }else{
            return null;
        }

    }


    private String waitForComponent() {
        try {
            FluentWait<Component> wait = new FluentWait<Component>(this);
            String ret = wait
                    .withTimeout(this.getWait(),TimeUnit.SECONDS)
                    .pollingEvery(250, TimeUnit.MILLISECONDS)
                    .ignoring(WebDriverException.class)
                    .until(new Function<Component, String>() {
                        public String apply(Component c) {
                            String query = c.getFullQuery();
                            String js = "return $('"+ query + "')[0].id;";
                            String id = (String) ((JavascriptExecutor) _driver).executeScript(js);
                            return id;
                        }
                    });
            return ret;
        } catch (TimeoutException e) {
            logger.debug("Exception found: "+ e.getMessage());
            e.printStackTrace();
            //throw new RuntimeException(e);
            return null;
        }
    }


    private Boolean waitForRendered() {

        FluentWait<Component> wait = new FluentWait<Component>(this);
        Boolean checker=false;
       try{
           checker =wait
                   .withTimeout(this.getWait(), TimeUnit.SECONDS)
                   .ignoring(WebDriverException.class)
                   .pollingEvery(450, TimeUnit.MILLISECONDS)
                   .until(new Function<Component, Boolean>() {
                       public Boolean apply(Component c) {
                           String js = "return $('"+getFullQuery()+"').is(':visible');";
                           logger.info("Searching for element: $('"+getFullQuery()+"').is(':visible')");
                           return (Boolean) ((JavascriptExecutor) _driver).executeScript(js);
                       }
                   });
           return checker;
       }catch (TimeoutException e){
           //throw new RuntimeException(e);
           return checker;

       }
    }

    protected String getFullQuery() {
        String query = _query;
        Component parent = _parent;
        while (parent != null) {
            query = parent._query + " " + query;
            parent = parent._parent;
        }
        //logger.info("Full query including Parent if exist: "+query);
        return query;
    }

    private String returnXpath (WebDriver driver,  String fileLib) throws IOException {

        Object rXpath=null;
        JavascriptExecutor je = (JavascriptExecutor) driver;
        try{
            URL _url = Resources.getResource(fileLib);
            String xpathStr = Resources.toString(_url, Charsets.UTF_8);
            //String xpathStr = FileUtils.readFileToString(new File(fileLib));
            rXpath = je.executeScript(xpathStr.replaceAll("jqueryselector",getFullQuery()));
        }catch (Exception e){
            logger.error("Error during building XPath for JQuery String: "+ fileLib);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return (String) rXpath;
    }


    public int getWait() {

        return Conversion.StringToInt(property.loadProperty("implicitWait"));
    }


    public void click() {
        getElement().click();
    }

    public void sendKeys(CharSequence... keysToSend) {
        getElement().sendKeys(keysToSend);

    }

    public void clear() {
        getElement().clear();
    }

    /*



    @Override
    public void submit() {
        getElement().submit();
    }



    @Override
    public String getTagName() {
        return getElement().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return getElement().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return getElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    @Override
    public String getText() {
        return getElement().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return getElement().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return getElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getElement().getSize();
    }

    @Override
    public Rectangle getRect(){return getElement().getRect();}

    @Override
    public String getCssValue(String propertyName) {
        return getElement().getCssValue(propertyName);
    }
    */






}