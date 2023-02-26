package framework.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static constants.DomainAppConstants.*;

public class DriverManager {
    public static final DriverConfig driverConfig = DriverConfig.getInstance();
    private static DriverManager instance;
    private WebDriver driver;
    private Wait<WebDriver> wait;

    protected DriverManager() {
        initialize();
    }

    public static DriverManager getInstance() {
        if (instance == null || instance.driver == null) {
            instance = new DriverManager();
        }
        return instance;
    }
    private static  final String pathAdBlocker = System.getProperty("user.dir") + File.separator + "src/test/resources/Utils/extensions/ublock.crx";
    private void initialize() {
        switch (driverConfig.getBrowser()) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                chromeOptions.addExtensions(new File(pathAdBlocker));
                chromeOptions.addArguments("--password-store=basic");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);

                if (driverConfig.getHeadlessMode()) {
                    chromeOptions.addArguments("--headless");
                }

                driver = new ChromeDriver(chromeOptions);
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                edgeOptions.setExperimentalOption("useAutomationExtension", false);
                edgeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

                edgeOptions.addArguments("--password-store=basic");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                edgeOptions.setExperimentalOption("prefs", prefs);

                if (driverConfig.getHeadlessMode()) {
                    edgeOptions.addArguments("--headless");
                }

                driver = new EdgeDriver(edgeOptions);
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                firefoxOptions.setLogLevel(FirefoxDriverLogLevel.FATAL);
                if (driverConfig.getHeadlessMode()) {
                    firefoxOptions.addArguments("--headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(driverConfig.getImplicitWaitTime());
        wait = new FluentWait<>(driver)
                .withTimeout(driverConfig.getTimeout())
                .pollingEvery(driverConfig.getPollingTime())
                .ignoring(NoSuchElementException.class)
                .ignoring(NotFoundException.class)
                .ignoring(StaleElementReferenceException.class);

    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public Wait<WebDriver> getFluentWait() {
        return wait;
    }

    public void quitWebDriver() {
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        driver = null;
    }
}
