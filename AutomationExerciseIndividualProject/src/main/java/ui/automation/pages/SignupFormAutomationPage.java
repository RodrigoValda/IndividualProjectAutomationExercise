package ui.automation.pages;

import framework.CredentialsManager;
import framework.selenium.UIMethods;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.automation.BaseAutomationPage;

public class SignupFormAutomationPage extends BaseAutomationPage {
    @FindBy(xpath = "//input[@data-qa='name']")
    WebElement nameTextBox;

    @FindBy(xpath = "//input[@data-qa='email']")
    WebElement emailTextBox;

    @FindBy(xpath = "//input[@data-qa='password']")
    WebElement passwordTextBox;

    @FindBy(xpath = "//input[@data-qa='first_name']")
    WebElement firstNameTextBox;

    @FindBy(xpath = "//input[@data-qa='last_name']")
    WebElement lastNameTextBox;

    @FindBy(xpath = "//input[@data-qa='address']")
    WebElement addressTextBox;

    @FindBy(xpath = "//input[@data-qa='state']")
    WebElement stateTextBox;

    @FindBy(xpath = "//input[@data-qa='city']")
    WebElement cityTextBox;

    @FindBy(xpath = "//input[@data-qa='zipcode']")
    WebElement zipCodeTextBox;

    @FindBy(xpath = "//input[@data-qa='mobile_number']")
    WebElement mobileNumberTextBox;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement createAccountButton;

    public SignupFormAutomationPage(){
        PageFactory.initElements(driver,this);
        waitUntilPageObjectIsLoaded();
    }

    public void setPasswordTextBox(String password) {
        UIMethods.scrollDownUntilElement(passwordTextBox);
        passwordTextBox.click();
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }
    public void setFirstNameTextBox(String firstName) {
        UIMethods.scrollDownUntilElement(firstNameTextBox);
        firstNameTextBox.click();
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(firstName);
    }
    public void setLastNameTextBox(String lastName) {
        UIMethods.scrollDownUntilElement(lastNameTextBox);
        lastNameTextBox.click();
        lastNameTextBox.clear();
        lastNameTextBox.sendKeys(lastName);
    }
    public void setAddressTextBox(String address) {
        UIMethods.scrollDownUntilElement(addressTextBox);
        addressTextBox.click();
        addressTextBox.clear();
        addressTextBox.sendKeys(address);
    }
    public void setStateTextBox(String state) {
        UIMethods.scrollDownUntilElement(stateTextBox);
        stateTextBox.click();
        stateTextBox.clear();
        stateTextBox.sendKeys(state);
    }
    public void setCityTextBox(String city) {
        UIMethods.scrollDownUntilElement(cityTextBox);
        cityTextBox.click();
        cityTextBox.clear();
        cityTextBox.sendKeys(city);
    }
    public void setZipCodeTextBox(String zipcode) {
        UIMethods.scrollDownUntilElement(zipCodeTextBox);
        zipCodeTextBox.click();
        zipCodeTextBox.clear();
        zipCodeTextBox.sendKeys(zipcode);
    }
    public void setMobileNumberTextBox(String mobileNumber) {
        UIMethods.scrollDownUntilElement(mobileNumberTextBox);
        mobileNumberTextBox.click();
        mobileNumberTextBox.clear();
        mobileNumberTextBox.sendKeys(mobileNumber);
    }
    public void clickSignupButton() {
        createAccountButton.submit();
    }
    public AccountCreatedAutomationPage signupNewUser(String password,String firstName,String lastName,String address, String state, String city, String zipcode, String mobileNumber){
        setPasswordTextBox(password);
        setFirstNameTextBox(firstName);
        setLastNameTextBox(lastName);
        setAddressTextBox(address);
        setStateTextBox(state);
        setCityTextBox(city);
        setZipCodeTextBox(zipcode);
        setMobileNumberTextBox(mobileNumber);
        clickSignupButton();
        return new AccountCreatedAutomationPage();
    }
    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        nameTextBox = wait.until(ExpectedConditions.visibilityOf(nameTextBox));
        emailTextBox = wait.until(ExpectedConditions.visibilityOf(emailTextBox));
    }


    public boolean isNameTextBoxDisplayed(){
        return nameTextBox.isDisplayed();
    }
    public boolean isEmailTextBoxDisplayed(){
        return emailTextBox.isDisplayed();
    }
}
