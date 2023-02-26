package ui.automation.components;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.automation.pages.ProductAutomationPage;

public class MessageAddToCart extends BasePageObject {
    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement productMessage;

    public MessageAddToCart(){
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        productMessage = wait.until(ExpectedConditions.visibilityOf(productMessage));
    }
    public boolean isProductMessageDisplayed(){
        return productMessage.isDisplayed();
    }
}
