package ui.automation.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.PageTransporter;
import ui.automation.BaseAutomationPage;

public class BrandProductsAutomationPage extends BaseAutomationPage {
    @FindBy(xpath = "//h2[@class='title text-center']")
    WebElement titleText;

    @FindBy(xpath = "//li[@class='active']")
    WebElement brandTag;

    public BrandProductsAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        titleText = wait.until(ExpectedConditions.visibilityOf(titleText));
    }

    public boolean titleTextIsDisplayed(){
        return titleText.isDisplayed();
    }
    public boolean brandTagIsDisplayed(){
        return brandTag.isDisplayed();
    }
}
