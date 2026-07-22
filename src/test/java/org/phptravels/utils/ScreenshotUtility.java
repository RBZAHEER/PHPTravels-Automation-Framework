package org.phptravels.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtility {
    public static void takeScreenshot(WebDriver driver, String testName){
        try{
            //Convert Driver into SS object
            TakesScreenshot ts = (TakesScreenshot) driver;
            //Create temp SS
            File source = ts.getScreenshotAs(OutputType.FILE);
            //Unique name for SS
            String time = String.valueOf(System.currentTimeMillis());
            File destination = new File("screenshots/" + testName + "_" + time + ".png");
            //Copy SS
            Files.copy(source.toPath(), destination.toPath());
            //Print sop("SS TAKEN")
            System.out.println("Screenshot Taken");

        } catch (Exception e){
            System.out.println("Error in Screenshot Util");
            e.printStackTrace();
        }
    }
}
