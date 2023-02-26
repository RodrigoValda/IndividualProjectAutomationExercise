package ui;

import framework.CredentialsManager;
import framework.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import ui.automation.pages.*;

public class PageTransporter {
    private static final CredentialsManager credentialsManager = CredentialsManager.getInstance();
    private WebDriver driver;
    private String loginAdminURL;
    private String adminURL;
    private String signupURL;
    private String productsURL;
    private static PageTransporter instance;
    protected PageTransporter() {
        initialize();
    }
    public static PageTransporter getInstance() {
        if (instance == null) {
            instance = new PageTransporter();
        }
        return instance;
    }
    private void initialize() {
        loginAdminURL = credentialsManager.getAdminURL();
        adminURL = credentialsManager.getBaseURL();
        signupURL = credentialsManager.getSignupURL();
        productsURL = credentialsManager.getProductURL();
        driver = DriverManager.getInstance().getWebDriver();
    }
    private void goToURL(String url) {
        driver.navigate().to(url);
    }

    public boolean isOnLoginAdminPage() {
        return driver.getCurrentUrl().contains(loginAdminURL);
    }
    public boolean isOnAdminPage() {
        return driver.getCurrentUrl().contains(adminURL);
    }
    public boolean isOnHomePage(){ return  driver.getCurrentUrl().contains(adminURL);}
    public boolean isOnSignupPage(){ return driver.getCurrentUrl().contains(signupURL);}
    public boolean isOnLoginPage(){ return driver.getCurrentUrl().contains(loginAdminURL);}
    public boolean isOnProductPage(){ return driver.getCurrentUrl().contains(productsURL); }

    public LoginAutomationPage navigateToAdminLoginPage() {
        if (!isOnLoginAdminPage()) {
            goToURL(loginAdminURL);
        }
        return new LoginAutomationPage();
    }

    public HomeAutomationPage navigateToHomePage() {
        if (!isOnAdminPage()) {
            goToURL(adminURL);
        }
        return new HomeAutomationPage();
    }

    public SignupAutomationPage navigateToSignupPage() {
        if (!isOnSignupPage()) {
            goToURL(signupURL);
        }
        return new SignupAutomationPage();
    }

    public LoginSuccessfulAutomationPage navigateToLoginPage() {
        if (!isOnLoginPage()) {
            goToURL(loginAdminURL);
        }
        return new LoginSuccessfulAutomationPage();
    }

    public HomeProductsAutomationPage navigateProductHomePage(){
        if (!isOnHomePage()) {
            goToURL(adminURL);
        }
        return new HomeProductsAutomationPage();
    }
    public ProductAutomationPage navigateProductPage(){
        if (!isOnProductPage()) {
            goToURL(productsURL);
        }
        return new ProductAutomationPage();
    }
}
