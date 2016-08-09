package com.app.utils;


import com.app.reusable.Component;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
public class JSHelper {

    private final static Logger logger = LoggerFactory.getLogger(JSHelper.class);
    public static PropertyLoader property = new PropertyLoader();

    public static String ensureLegalFileName(String filename) {
        String fn = filename.replace("\\", "%5C")
                .replace("/", "-")
                .replace(":", "-")
                .replace("?", "-")
                .replace("*", "-")
                .replace("<", "-")
                .replace(">", "-")
                .replace("|", "-")
                .replace("\"", "-")
                .replace(",", "-")
                .replace(";", "-");
        return fn;
    }

    public static void injectJQ (WebDriver driver){
        logger.info("Injecting JQuery.js ...");
        try {
            injectJS(driver, "return this.$ === undefined","jquery.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void injectUS (WebDriver driver){
        logger.info("Injecting underscore.js ...");
        try {
            injectJS(driver, "return this._ === undefined", "underscore.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void forceJQ (WebDriver driver){
        try {
            injectJS(driver, "return true","jquery.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*public static void injectJS (WebDriver driver, String jqueryString) throws IOException{
        logger.info("Injecting buildXpath.js ...");
        returnXpath(driver, jqueryString, "buildXpath.js");
    }*/

    private static void injectJS (WebDriver driver, String js, String fileLib) throws IOException{

        JavascriptExecutor je = (JavascriptExecutor) driver;
        boolean inject = (boolean) (je.executeScript(js));
        if (inject){
            try{
                URL _url = Resources.getResource(fileLib);
                js = Resources.toString(_url, Charsets.UTF_8);
                je.executeScript(js);
                logger.info("Injection of "+ fileLib+" success...");
            }catch (Exception e){
                logger.error("Error during JS injection: "+ fileLib);
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

    }




}