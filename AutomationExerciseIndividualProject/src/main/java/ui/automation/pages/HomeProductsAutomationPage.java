package ui.automation.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.automation.BaseAutomationPage;

public class HomeProductsAutomationPage extends BaseAutomationPage {
    @FindBy(xpath = "//a[@href = '/products']")
    WebElement productsButton;

    public HomeProductsAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        productsButton = wait.until(ExpectedConditions.elementToBeClickable(productsButton));
    }

    public ProductAutomationPage clickProductsButton(){
        productsButton.click();
        return new ProductAutomationPage();
    }
}
