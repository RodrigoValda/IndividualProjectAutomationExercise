package ui.automation.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.automation.BaseAutomationPage;
import ui.automation.components.TopBarMenu;

public class HomeAutomationPage extends BaseAutomationPage {

    @FindBy(xpath = "//a[text()=' Logged in as ']")
    WebElement loggedText;



    public HomeAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
        topBarMenu = new TopBarMenu();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        loggedText = wait.until(ExpectedConditions.visibilityOf(loggedText));
    }
}
