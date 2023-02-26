package ui.automation.pages;

import framework.CredentialsManager;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.automation.BaseAutomationPage;
import utils.LoggerManager;

public class LoginAutomationPage extends BaseAutomationPage {
    private static final LoggerManager log = LoggerManager.getInstance();
    @FindBy(xpath = "//input[(@type='email') and (@data-qa='login-email')]")
    WebElement emailFieldTextBox;

    @FindBy(xpath = "//input[(@type='password') and (@data-qa='login-password')]")
    WebElement passwordFieldTextBox;

    @FindBy(xpath = "//input[(@data-qa='login-password')]")
    WebElement loginSubmitButton;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    WebElement errorMessage;


    public LoginAutomationPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        emailFieldTextBox = wait.until(ExpectedConditions.elementToBeClickable(emailFieldTextBox));
        passwordFieldTextBox = wait.until(ExpectedConditions.elementToBeClickable(passwordFieldTextBox));
        loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton));
    }

    public void setEmailTextBox(String email) {
        emailFieldTextBox.click();
        emailFieldTextBox.clear();
        emailFieldTextBox.sendKeys(email);
    }

    public void setPasswordTextBox(String password) {
        passwordFieldTextBox.click();
        passwordFieldTextBox.clear();
        passwordFieldTextBox.sendKeys(password);
    }

    public void clickLoginButton() {
        loginSubmitButton.submit();
        errorMessage = wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public HomeAutomationPage loginWithUser(){
        setEmailTextBox(CredentialsManager.getInstance().getEmail("user"));
        setPasswordTextBox(CredentialsManager.getInstance().getPassword("user"));
        System.out.println(CredentialsManager.getInstance().getEmail("user"));
        System.out.println(CredentialsManager.getInstance().getPassword("user"));
        clickLoginButton();
        return new HomeAutomationPage();
    }

    public void tryLoginSendingParameters(String email, String password){
        emailFieldTextBox.sendKeys(email);
        passwordFieldTextBox.sendKeys(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }
    public boolean isEmailFieldDisplayed(){
        return emailFieldTextBox.isDisplayed();
    }
    public boolean isPasswordFieldDisplayed(){
        return passwordFieldTextBox.isDisplayed();
    }

    public String getErrorMessageText(){
        return errorMessage.getText();
    }
}
