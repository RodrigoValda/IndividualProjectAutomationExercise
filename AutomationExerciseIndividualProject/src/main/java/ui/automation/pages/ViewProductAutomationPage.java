package ui.automation.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.automation.BaseAutomationPage;

import javax.swing.text.View;

public class ViewProductAutomationPage extends BaseAutomationPage {
    @FindBy(xpath = "//div[@class='product-information']")
    WebElement productInformation;
    @FindBy(xpath = "//input[@id='quantity']")
    WebElement quantityTextBox;
    @FindBy(xpath = "//button[@class='btn btn-default cart']")
    WebElement addCartButton;
    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement addCartMessage;
    @FindBy(xpath = "//input[@id='name']")
    WebElement nameTextBox;
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailTextBox;
    @FindBy(xpath = "//textarea[@id='review']")
    WebElement reviewTextBox;
    @FindBy(xpath = "//button[@id='button-review']")
    WebElement reviewButton;
    @FindBy(xpath = "//div[@class='alert-success alert']//span")
    WebElement successfulAlert;
    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    WebElement buttonSuccessfulAlertButton;
    public ViewProductAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        productInformation = wait.until(ExpectedConditions.visibilityOf(productInformation));
    }
    public void setQuantity(String quantity){
        quantityTextBox.clear();
        quantityTextBox.sendKeys(quantity);
    }
    public void setReview(String name, String email, String review){
        nameTextBox.click();
        nameTextBox.clear();
        nameTextBox.sendKeys(name);
        emailTextBox.click();
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
        reviewTextBox.click();
        reviewTextBox.clear();
        reviewTextBox.sendKeys(review);
        reviewButton.submit();
    }

    public ViewProductAutomationPage clickAddProductToCart(){
        addCartButton.click();
        return this;
    }
    public boolean productInformationDisplayed(){
        return productInformation.isDisplayed();
    }
    public boolean messageAddProductToCart(){
        addCartMessage = wait.until(ExpectedConditions.visibilityOf(addCartMessage));
        return addCartMessage.isDisplayed();
    }

    public boolean messageAddProductToCartQuantity(){
        addCartMessage = wait.until(ExpectedConditions.visibilityOf(addCartMessage));
        buttonSuccessfulAlertButton = wait.until(ExpectedConditions.visibilityOf(buttonSuccessfulAlertButton));
        return addCartMessage.isDisplayed();
    }
    public void clickSuccessfulAlertMessageAddCart(){
        buttonSuccessfulAlertButton.click();
    }

    public boolean successAlertDisplayed(){
        return successfulAlert.isDisplayed();
    }
}
