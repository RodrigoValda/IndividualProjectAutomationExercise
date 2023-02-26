package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialsManager {
    private Properties properties;
    private static final String envFilePath = System.getProperty("user.dir") + File.separator + "environments.properties";
    private static CredentialsManager instance;
    private String envId;

    private CredentialsManager() {
        initialize();
    }

    private void initialize(){
        String autEnvironmentId = System.getProperty("envId");
        if ((autEnvironmentId == null) || (autEnvironmentId.isEmpty())) {
            envId = "local";
        } else {
            envId = autEnvironmentId.toLowerCase();
        }
        properties = new Properties();
        Properties envProperties = new Properties();
        try {
            envProperties.load(new FileInputStream(envFilePath));
        } catch (IOException e) {
            System.out.println("error to load properties");
        }
        properties.putAll(envProperties);
    }

    public static CredentialsManager getInstance() {
        if (instance == null) {
            instance = new CredentialsManager();
        }
        return instance;
    }
    private String getEnvironmentSetting(String setting) {
        return (String) getInstance().properties.get(setting);
    }

    public String getEnvId() {
        return envId;
    }

    public String getBaseURL() {
        return getEnvironmentSetting(getEnvId() + ".baseURL");
    }

    public String getEmail(String userRole) {
        return getEnvironmentSetting(getEnvId() + "." + userRole + ".email");
    }

    public String getPassword(String userRole) {
        return getEnvironmentSetting(getEnvId() + "." + userRole + ".password");
    }

    public String getName(String userRole) {
        return getEnvironmentSetting(getEnvId() + "." + userRole + ".name");
    }

    public String getLastName(String userRole) {
        return getEnvironmentSetting(getEnvId() + "." + userRole + ".lastname");
    }

    public String getAdminURL() {
        return getBaseURL() + getEnvironmentSetting(getEnvId() + ".user.loginURL");
    }
    public String getProductURL(){
        return getBaseURL() + getEnvironmentSetting(getEnvId() + ".user.productURL");
    }

    public String getSignupURL() {
        return getBaseURL() + getEnvironmentSetting(getEnvId() + ".user.signupURL");
    }

    public String getProductDetails() {
        return getBaseURL() + getEnvironmentSetting(getEnvId() + ".user.productDetailsURL");
    }
    public String getBrandProducts(){
        return getBaseURL() + getEnvironmentSetting(getEnvId() + ".user.brandProductsURL");
    }

}
