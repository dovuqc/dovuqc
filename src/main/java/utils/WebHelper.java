package utils;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import pages.CalculatorPage;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class WebHelper {

    public static void waitFor(long seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pressKey(Keys key){
        DriverManager.getDriver().findElement(By.xpath("//body")).sendKeys(key);
        WebHelper.waitFor(1);
    }

    public static void pressOperator(String operator){
        switch (operator) {
            case "subtract" :
                WebHelper.pressKey(Keys.SUBTRACT);
                break;
            case "add" :
                WebHelper.pressKey(Keys.ADD);
                break;
            case "divide" :
                WebHelper.pressKey(Keys.DIVIDE);
                break;
            default:
                System.out.println("Invalid operator name");
                break;
        }
    }

    public static void pressKey(String keyName){
        DriverManager.getDriver().findElement(By.xpath("//body")).sendKeys(keyName);
        WebHelper.waitFor(1);
    }

    public static String convertImageToBase64(String pathName){
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
