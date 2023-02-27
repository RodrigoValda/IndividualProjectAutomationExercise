package com.jalasoft.automation.steps.automation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.PageTransporter;
import ui.automation.pages.BrandProductsAutomationPage;
import ui.automation.pages.HomeProductsAutomationPage;
import ui.automation.pages.ProductAutomationPage;
import ui.automation.pages.ViewProductAutomationPage;

import java.util.List;
import java.util.Map;

public class ProductsSteps {
    private final PageTransporter pageTransporter;

    private HomeProductsAutomationPage homeProductsAutomationPage;
    private ProductAutomationPage productAutomationPage;
    private ViewProductAutomationPage viewProductAutomationPage;
    private BrandProductsAutomationPage brandProductsAutomationPage;
    public ProductsSteps(){
        this.pageTransporter=PageTransporter.getInstance();
    }

    @Given("I navigate to Automation product page")
    public void navigateHomeProductPage(){
        homeProductsAutomationPage = pageTransporter.navigateProductHomePage();
        productAutomationPage = homeProductsAutomationPage.clickProductsButton();


    }
    @Given("The user adds the product to the cart")
    public void addProductToCart(){
        productAutomationPage.clickAddToCartButton();
    }
    @When("The message should be displayed")
    public void verifyMessageProductAddedToCart(){
        Assert.assertTrue(productAutomationPage.productMessageDisplayed());
        productAutomationPage.continueShopping();
    }

    @When("I go to the product details of the selected product")
    public void viewProduct(){
        viewProductAutomationPage = productAutomationPage.clickViewProductButton();
    }

    @Then("I should be redirected to the product details")
    public void verifyDetailsProduct(){
        boolean detailsProductIsDisplayed = viewProductAutomationPage.productInformationDisplayed();
        Assert.assertTrue(detailsProductIsDisplayed);
    }

    @When("^I deploy the category options of the \"(.*?)\" selected$")
    public void deployCategorySelected(String category){
        productAutomationPage = productAutomationPage.clickCategory(category);
    }

    @Then("The options of the \"(.*?)\" selected should deploy correctly$")
    public void verifyOptionsDeploy(String brand){
        boolean womenCategoriesDisplayed = productAutomationPage.categoryIsDisplayed(brand);
        Assert.assertTrue(womenCategoriesDisplayed);
    }

    @When("I should be redirected to the product page")
    public void selectABrand(){
        homeProductsAutomationPage.clickProductsButton();
    }

    @Then("I should be redirected to the products of the \"(.*?)\" selected$")
    public void verifyRedirectToTheBrand(String brand){
        brandProductsAutomationPage = productAutomationPage.clickBrand(brand);

    }

    @Then("The products of the selected \"(.*?)\" should be displayed$")
    public void displayBrandSelected(String brand){
        boolean brandSelectedRedirected = brandProductsAutomationPage.titleTextIsDisplayed();
        boolean brandTagDisplayed = brandProductsAutomationPage.brandTagIsDisplayed();
        String actualResult = brandProductsAutomationPage.brandTagGetText();

        Assert.assertTrue(brandSelectedRedirected,"Non redirected to the brand selected");
        Assert.assertTrue(brandTagDisplayed,"The tag of the brand selected is not displayed");
        Assert.assertEquals(brand, actualResult,"No redirected to the brand selected");
    }

    @Then("I modify the \"(.*?)\" of products$")
    public void modifyQuantity(String quantity){
        viewProductAutomationPage.setQuantity(quantity);
    }

    @Then("I add the product to the cart")
    public void addProductCart(){
        viewProductAutomationPage = viewProductAutomationPage.clickAddProductToCart();
    }
    @Then("The message of add selected product should be displayed")
    public void addToCartQuantity(){
        boolean messageIsDisplayed = viewProductAutomationPage.messageAddProductToCartQuantity();
        Assert.assertTrue(messageIsDisplayed);
        viewProductAutomationPage.clickSuccessfulAlertMessageAddCart();
    }
    @Then("The message of add product should be displayed")
    public void errorDisplayErrorMessage(){
        boolean messageIsDisplayed = viewProductAutomationPage.messageAddProductToCart();
        Assert.assertTrue(messageIsDisplayed);
        viewProductAutomationPage.setContinueShopping();
    }

    @Then("I send the review of the product$")
    public void sendTheReview(DataTable table){
        List<Map<String,Object>> queryParamList = table.asMaps(String.class,Object.class);
        Map<String, Object> values = queryParamList.get(0);
        String name = (String) values.get("name");
        String email = (String) values.get("email");
        String review = (String) values.get("review");
        viewProductAutomationPage.setReview(name,email,review);
    }
    @And("The successful alert should be displayed")
    public void verifySuccessfulAlert(){
        boolean successfulAlertDisplayed = viewProductAutomationPage.successAlertDisplayed();
        Assert.assertTrue(successfulAlertDisplayed);
    }

}
