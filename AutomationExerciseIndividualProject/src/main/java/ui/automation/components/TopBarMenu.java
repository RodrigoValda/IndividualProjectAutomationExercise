package ui.automation.components;

import framework.selenium.UIMethods;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.automation.pages.LoginAutomationPage;

public class TopBarMenu extends BasePageObject {
    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//ul[@class='nav navbar-nav']")
    WebElement navBar;

    @FindBy(xpath = "//a[@href='/delete_account']")
    WebElement deleteAccountButton;

    @FindBy(xpath = "")
    WebElement loginButton;
    public TopBarMenu(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        navBar = wait.until(ExpectedConditions.visibilityOf(navBar));
    }

    public void clickLogoutButton(){
        logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public boolean isLogoutButtonDisplayed(){
        logoutButton = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        return logoutButton.isDisplayed();
    }

    public boolean isNavBarDisplayed(){
        return navBar.isDisplayed();
    }
    public boolean isDeleteAccountButtonIsDisplayed(){
        deleteAccountButton = wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton));
        return deleteAccountButton.isDisplayed();
    }

    public void logout(){
        clickLogoutButton();
    }

    public void goToLoginButton(){
        loginButton.click();
    }
}
