package stepdefs;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import pages.CalculatorPage;
import utils.ImageReader;
import utils.WebHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorSteps {

    File resultImg;
    byte[] resultByte;
    String actualResult;
    private static CalculatorPage calculatorPage;

    @Before
    public static void setUp() {
        DriverManager.initDriver();
    }

    @After
    public void cleanUp(Scenario scenario) {
        // Attach result image to the report if failure
        if(!scenario.getStatus().equals(Status.PASSED)){
            scenario.attach(resultByte, "image/png", "Actual Result");
        }
        DriverManager.quitDriver();
    }

    @Given("I open chrome browser and start online calculator application")
    public void iOpenChromeBrowserAndStartOnlineCalculatorApplication(){
        DriverManager.getDriver().get("https://www.online-calculator.com/full-screen-calculator/");
        calculatorPage = new CalculatorPage();
        calculatorPage.switchToCanvas();
    }

    @Given("^I want check the scenario (.*)$")
    public void iWantCheckTheScenarioContext(String contextName) {
        System.out.printf("Verify that user should be able to %s%n", contextName);
    }

    @When("^I do the math (.*) (.*) by (.*)$")
    public void iPerformValueOperatorValue(String value1, String operator, String value2) {
        WebHelper.pressKey(value1);
        WebHelper.pressOperator(operator);
        WebHelper.pressKey(value2);
        WebHelper.pressKey(Keys.ENTER);

        String filePath = String.format("target/actual-results/%s.png", value1 + value2 + operator);

        //Take screenshot of actual result
        resultImg = calculatorPage.calculatorCanvas.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(resultImg, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crop image to get result bar only
        resultImg = calculatorPage.calculatorCanvas.getScreenshotAs(OutputType.FILE);
        resultByte = calculatorPage.calculatorCanvas.getScreenshotAs(OutputType.BYTES);
        try {
            BufferedImage bufferedImage = ImageIO.read(resultImg);
            BufferedImage croppedImage = bufferedImage.getSubimage(0, 0, 399, 70);
            ImageIO.write(croppedImage, "png", new File(filePath));

            //Read text from image with ORC Space
            actualResult = ImageReader.getText(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebHelper.waitFor(1);
    }

    @When("^I do the chain math (.*) (.*) by (.*) and (.*) by (.*)$")
    public void iPerformValueOperatorValueOperatorValue(String value1, String operator1, String value2, String operator2, String value3) {
        WebHelper.pressKey(value1);
        WebHelper.pressOperator(operator1);
        WebHelper.pressKey(value2);
        WebHelper.pressKey(Keys.ENTER);
        WebHelper.pressOperator(operator2);
        WebHelper.pressKey(value3);
        WebHelper.pressKey(Keys.ENTER);

        String filePath = String.format("target/actual-results/%s.png", value1 + operator1 + value2 + operator2 + value3);

        //Take screenshot of actual result
        resultImg = calculatorPage.calculatorCanvas.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(resultImg, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Crop image to get result bar only
        resultImg = calculatorPage.calculatorCanvas.getScreenshotAs(OutputType.FILE);
        resultByte = calculatorPage.calculatorCanvas.getScreenshotAs(OutputType.BYTES);
        try {
            BufferedImage bufferedImage = ImageIO.read(resultImg);
            BufferedImage croppedImage = bufferedImage.getSubimage(0, 0, 399, 70);
            ImageIO.write(croppedImage, "png", new File(filePath));

            //Read text from image with ORC Space
            actualResult = ImageReader.getText(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebHelper.waitFor(1);
    }

    @Then("^I should see the result is (.*)$")
    public void iShouldSeeTheResultI(String result) {
        assertThat(actualResult).isEqualTo(result);
    }

    @Then("I should see the error message")
    public void iShouldSeeTheErrorMessage() {
        assertThat(actualResult).isEqualTo("Error");
    }
}
