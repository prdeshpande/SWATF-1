package com.app.reporter;

import com.app.utils.JSHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Oscar Garcia on 8/2/2016.
 */
public class HTMLReport {

    private final static Logger logger = LoggerFactory.getLogger(JSHelper.class);

    @Attachment("Request/Response")
    public static String attachReport(String strValue){

        return strValue;
    }



    public static void createReportProperties(){
        try{
            File file = new File("target/allure-results");
            if(!file.exists()){
                file.mkdirs();
            }
            FileWriter fw = new FileWriter("target/allure-results/environment.properties");
            // Get values from a properties file
            fw.write("Manual Added property= This is a property added");
            fw.close();
        }
        catch (IOException e){
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        }
    }

    public static void addReportingProperties(String strProp){
        try{
            File file = new File("target/allure-results");
            if(!file.exists()){
                file.mkdirs();
            }
            FileWriter fw = new FileWriter("target/allure-results/environment.properties", true);
            // Get values from a properties file
            fw.write(strProp + "\n");
            fw.close();
        }
        catch (IOException e){
            logger.info("Context", Arrays.toString(e.getStackTrace()));
        }
    }
}

