package pages;

import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CalculatorPage {
    public CalculatorPage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(css = "iframe#fullframe")
    public WebElement iFrame;

    @FindBy(css = "div#animation_container canvas")
    public WebElement calculatorCanvas;

    public void switchToCanvas(){
        DriverManager.getDriver().switchTo().frame(iFrame);
    }

}
