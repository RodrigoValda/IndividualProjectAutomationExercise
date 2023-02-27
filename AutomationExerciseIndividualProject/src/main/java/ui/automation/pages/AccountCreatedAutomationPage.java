package ui.automation.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.automation.BaseAutomationPage;

public class AccountCreatedAutomationPage extends BaseAutomationPage {
    @FindBy(xpath = "//h2[@data-qa='account-created']")
    WebElement accountCreatedText;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    public AccountCreatedAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        accountCreatedText = wait.until(ExpectedConditions.visibilityOf(accountCreatedText));
    }

    public boolean messageAccountCreatedDisplayed(){
        return accountCreatedText.isDisplayed();
    }

    public void continueButtonClick(){
        continueButton.click();
    }
}
