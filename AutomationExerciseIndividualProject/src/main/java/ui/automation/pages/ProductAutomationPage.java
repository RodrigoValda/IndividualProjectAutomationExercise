package ui.automation.pages;

import framework.selenium.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.automation.BaseAutomationPage;
import ui.automation.components.MessageAddToCart;

public class ProductAutomationPage extends BaseAutomationPage {

    @FindBy(xpath = "//div[@class = 'productinfo text-center']//a[@data-product-id='1']")
    WebElement productsButton;
    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement productMessage;
    @FindBy(xpath = "//a[@href='/product_details/1']")
    WebElement viewProductButton;
    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    WebElement continueShoppingButton;

    public ProductAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        productsButton = wait.until(ExpectedConditions.visibilityOf(productsButton));
    }

    public void clickAddToCartButton(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@data-product-id='1']")));
    }

    public ViewProductAutomationPage clickViewProductButton(){
        UIMethods.scrollDownUntilElement(viewProductButton);
        viewProductButton.click();
        return new ViewProductAutomationPage();
    }

    public ProductAutomationPage clickCategory(String category){
        String categoryButton = String.format("//a[@href='#%s']",category);
        WebElement categoryWebButton = driver.findElement(By.xpath(categoryButton));
        categoryWebButton = wait.until(ExpectedConditions.elementToBeClickable(categoryWebButton));
        categoryWebButton.click();
        return this;
    }

    public BrandProductsAutomationPage clickBrand(String brand){
        String brandButton = String.format("//a[@href='/brand_products/%s']",brand);
        WebElement brandButtonWeb = driver.findElement(By.xpath(brandButton));
        brandButtonWeb = wait.until(ExpectedConditions.elementToBeClickable(brandButtonWeb));
        brandButtonWeb.click();
        return new BrandProductsAutomationPage();
    }


    public boolean productMessageDisplayed(){
        productMessage = wait.until(ExpectedConditions.visibilityOf(productMessage));
        return productMessage.isDisplayed();
    }

    public void continueShopping(){
        continueShoppingButton.click();
    }

    public boolean categoryIsDisplayed(String brand){
        String divCategory = String.format("//div[@id='%s']",brand);
        WebElement categoryButton = driver.findElement(By.xpath(divCategory));
        return categoryButton.isDisplayed();
    }
}
