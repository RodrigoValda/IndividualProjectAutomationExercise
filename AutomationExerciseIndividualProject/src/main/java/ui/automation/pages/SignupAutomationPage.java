package ui.automation.pages;

import framework.CredentialsManager;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.automation.BaseAutomationPage;
import utils.LoggerManager;
import utils.StringGenerator;

public class SignupAutomationPage extends BaseAutomationPage {
    private static final LoggerManager log = LoggerManager.getInstance();
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement nameFieldTextBox;
    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement emailFieldTextBox;
    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signupSubmitButton;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    WebElement errorMessage;

    public SignupAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        nameFieldTextBox = wait.until(ExpectedConditions.elementToBeClickable(nameFieldTextBox));
        emailFieldTextBox = wait.until(ExpectedConditions.elementToBeClickable(emailFieldTextBox));
        signupSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(signupSubmitButton));
    }
    public void setNameFieldTextBox(String name) {
        nameFieldTextBox.click();
        nameFieldTextBox.clear();
        nameFieldTextBox.sendKeys(name);
    }

    public void setEmailFieldTextBox(String email) {
        emailFieldTextBox.click();
        emailFieldTextBox.clear();
        emailFieldTextBox.sendKeys(email);
    }

    public void clickLoginButton() {
        signupSubmitButton.submit();
    }

    public SignupFormAutomationPage signupWithUser(){
        setNameFieldTextBox(StringGenerator.randomString());
        setEmailFieldTextBox(StringGenerator.randomEmailString());
        clickLoginButton();
        return new SignupFormAutomationPage();
    }

    public SignupAutomationPage signupWithAlreadyExistUser(String name,String email){
        setNameFieldTextBox(name);
        setEmailFieldTextBox(email);
        clickLoginButton();
        return this;
    }

    public boolean errorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }
    public boolean nameFieldDisplayed(){
        return nameFieldTextBox.isDisplayed();
    }
    public boolean emailFieldDisplayed(){
        return emailFieldTextBox.isDisplayed();
    }
}
