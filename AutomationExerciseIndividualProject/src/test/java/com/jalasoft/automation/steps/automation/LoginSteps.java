package com.jalasoft.automation.steps.automation;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.PageTransporter;
import ui.automation.pages.*;

import java.util.List;
import java.util.Map;

public class LoginSteps {
    private final PageTransporter pageTransporter;
    private LoginAutomationPage loginAutomationPage;
    private LoginSuccessfulAutomationPage loginSuccessfulAutomationPage;
    private HomeAutomationPage homeAutomationPage;
    private SignupAutomationPage signupAutomationPage;
    private SignupFormAutomationPage signupFormAutomationPage;
    private AccountCreatedAutomationPage accountCreatedAutomationPage;

    public LoginSteps(){
        this.pageTransporter = PageTransporter.getInstance();
    }
    @Given("^I navigate to Automation Login page")
    public void navigateToLoginPage(){
        loginSuccessfulAutomationPage = pageTransporter.navigateToLoginPage();
    }

    @Given("^I navigate to Automation Login page to logs in")
    public void navigateToLoginPageWithWrongCredentials(){
        loginAutomationPage = pageTransporter.navigateToAdminLoginPage();
    }

    @Given("I login to Automation Exercise page")
    public void loginInThePage(){
        homeAutomationPage = loginSuccessfulAutomationPage.loginWithUser();
    }

    @Then("I should login successfully")
    public void verifyLogin(){
        boolean isNavBarDisplayed = homeAutomationPage.topBarMenu.isNavBarDisplayed();
        boolean isDeleteAccountDisplayed = homeAutomationPage.topBarMenu.isDeleteAccountButtonIsDisplayed();
        boolean isLogoutButtonDisplayed = homeAutomationPage.topBarMenu.isLogoutButtonDisplayed();

        Assert.assertTrue(isNavBarDisplayed,"The user can not login");
        Assert.assertTrue(isDeleteAccountDisplayed,"The user can not login");
        Assert.assertTrue(isLogoutButtonDisplayed,"The user can not login");
    }

    @Given("I provide wrong credentials to login in the page with the following parameters$")
    public void loginWrongCredentials(DataTable table){
        List<Map<String,Object>> queryParamList = table.asMaps(String.class,Object.class);
        Map<String, Object> values = queryParamList.get(0);
        String email = (String) values.get("email");
        String password = (String) values.get("password");
        loginAutomationPage.tryLoginSendingParameters(email,password);
    }

    @Then("The error \"(.*?)\" should be displayed$")
    public void errorMessageDisplayed(String errorMessage){
        boolean errorMessageDisplayed = loginAutomationPage.isErrorMessageDisplayed();
        String actualErrorMessage = loginAutomationPage.getErrorMessageText();

        Assert.assertTrue(errorMessageDisplayed);
        Assert.assertEquals(errorMessage,actualErrorMessage);
    }
    @Given("^The user navigates to Automation Signup page")
    public void navigateToSignupPage(){
         signupAutomationPage = pageTransporter.navigateToSignupPage();
    }

    @Given("The user signup to automation exercise page")
    public void signupInThePage(){
        signupFormAutomationPage = signupAutomationPage.signupWithUser();
    }
    @Then("The user should fill the signup form")
    public void verifyRedirectedPage(){
        boolean isNameTextDisplayed = signupFormAutomationPage.isNameTextBoxDisplayed();
        boolean isEmailTextBox = signupFormAutomationPage.isEmailTextBoxDisplayed();
        Assert.assertTrue(isNameTextDisplayed);
        Assert.assertTrue(isEmailTextBox);
    }
    @Then("The user should signup successfully")
    public void signupUser(DataTable table){
        List<Map<String,Object>> queryParamList = table.asMaps(String.class,Object.class);
        Map<String, Object> values = queryParamList.get(0);
        String password = (String) values.get("password");
        String firstName = (String) values.get("firstName");
        String lastName = (String) values.get("lastName");
        String address = (String) values.get("address");
        String state = (String) values.get("state");
        String city = (String) values.get("city");
        String zipcode = (String) values.get("zipcode");
        String mobileNumber = (String) values.get("mobileNumber");
        accountCreatedAutomationPage = signupFormAutomationPage.signupNewUser(password,firstName,
                lastName,address,state,city,zipcode,mobileNumber);

    }

    @Then("The user should be redirected to the account created page")
    public void redirectUserAccountCreated(){
        boolean AccountCreatedTextDisplayed = accountCreatedAutomationPage.messageAccountCreatedDisplayed();
        Assert.assertTrue(AccountCreatedTextDisplayed);
    }

    @Given("The user tries to signup with an already exist user$")
    public void signupAlreadyExistUser(DataTable table){
        List<Map<String,Object>> queryParamList = table.asMaps(String.class,Object.class);
        Map<String, Object> values = queryParamList.get(0);
        String name = (String) values.get("name");
        String email = (String) values.get("email");
        signupAutomationPage = signupAutomationPage.signupWithAlreadyExistUser(name,email);
    }

    @Then("An error \"(.*?)\" should be displayed$")
    public void errorMessageAccountAlreadyRegistered(String errorMessage){
        boolean errorMessageAccountAlreadyRegistered = signupAutomationPage.errorMessageDisplayed();
        String actualErrorMessage = signupAutomationPage.getErrorMessage();

        Assert.assertTrue(errorMessageAccountAlreadyRegistered);
        Assert.assertEquals(errorMessage,actualErrorMessage);
    }

    @When("The user tries to signup with empty credentials")
    public void signupWithEmptyCredentials(){
        String name = "";
        String email = "";
        signupAutomationPage = signupAutomationPage.signupWithAlreadyExistUser(name,email);
    }

    @Then("The user shouldn't be redirected to the home page")
    public void notRedirectedToTheHomePage(){
        boolean nameDisplayed = signupAutomationPage.nameFieldDisplayed();
        boolean emailDisplayed = signupAutomationPage.emailFieldDisplayed();
        Assert.assertTrue(nameDisplayed);
        Assert.assertTrue(emailDisplayed);
    }
    @When("The user tries to signup with invalid email$")
    public void signupWithInvalidEmail(DataTable table){
        List<Map<String,Object>> queryParamList = table.asMaps(String.class,Object.class);
        Map<String, Object> values = queryParamList.get(0);
        String name = (String) values.get("name");
        String email = (String) values.get("email");
        signupAutomationPage = signupAutomationPage.signupWithAlreadyExistUser(name,email);
    }

    @Then("The user shouldn't fill the signup form")
    public void signupUser(){

        String password = "";
        String firstName = "";
        String lastName = "";
        String address = "";
        String state = "";
        String city = "";
        String zipcode = "";
        String mobileNumber = "";
        accountCreatedAutomationPage = signupFormAutomationPage.signupNewUser(password,firstName,
                lastName,address,state,city,zipcode,mobileNumber);
        boolean nameTextBox = signupFormAutomationPage.isNameTextBoxDisplayed();
        boolean emailTextBox = signupFormAutomationPage.isEmailTextBoxDisplayed();
        Assert.assertTrue(nameTextBox);
        Assert.assertTrue(emailTextBox);
    }

    @When("I provide empty values to the login")
    public void provideEmptyValuesLogin(){
        String email = "";
        String password= "";
        loginAutomationPage.tryLoginSendingParameters(email,password);
    }

    @Then("The user shouldn't be redirected to the home page from the login")
    public void notRedirectedToTheHomePageFromLogin(){
        boolean nameDisplayed = loginAutomationPage.isEmailFieldDisplayed();
        boolean emailDisplayed = loginAutomationPage.isPasswordFieldDisplayed();
        Assert.assertTrue(nameDisplayed);
        Assert.assertTrue(emailDisplayed);
    }
}
