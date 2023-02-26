package framework.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverConfig {
    private static final String webDriverFilePath = System.getProperty("user.dir") + File.separator + "webdriver.properties";
    private static DriverConfig instance;
    private String browser;
    private Properties properties;

    protected DriverConfig() {
        initialize();
    }

    public static DriverConfig getInstance() {
        if (instance == null || instance.properties == null) {
            instance = new DriverConfig();
        }
        return instance;
    }

    private void initialize() {
        String selectedBrowser = System.getProperty("browser");
        if ((selectedBrowser == null) || (selectedBrowser.isEmpty())) {
            browser = "chrome";
        } else {
            browser = selectedBrowser.toLowerCase();
        }

        properties = new Properties();
        Properties webDriverProperties = new Properties();
        try {
            webDriverProperties.load(new FileInputStream(webDriverFilePath));
        } catch (IOException e) {
            System.out.println("properties not read");
        }
        properties.putAll(webDriverProperties);
    }

    public String getBrowser() {
        return browser;
    }

    private String getWebDriverSetting(String setting) {
        return (String) properties.get(setting);
    }

    public Duration getImplicitWaitTime() {
        long seconds = Long.parseLong(getWebDriverSetting("webdriver.implicit.wait.time"));
        return Duration.ofMillis(seconds);
    }

    public Duration getTimeout() {
        long seconds = Long.parseLong(getWebDriverSetting("webdriver.timeout"));
        return Duration.ofMillis(seconds);
    }

    public Duration getPollingTime() {
        long seconds = Long.parseLong(getWebDriverSetting("webdriver.polling.time"));
        return Duration.ofMillis(seconds);
    }

    public boolean getHeadlessMode() {
        return Boolean.parseBoolean(getWebDriverSetting("webdriver.headless.mode"));
    }
}
