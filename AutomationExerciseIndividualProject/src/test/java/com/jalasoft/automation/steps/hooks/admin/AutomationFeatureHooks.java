package com.jalasoft.automation.steps.hooks.admin;

import io.cucumber.java.After;
import ui.automation.pages.HomeAutomationPage;
import ui.methods.CommonMethods;


public class AutomationFeatureHooks {

    @After("@LoginSuccessful or @SignupUser or @SignupWithoutInformation")
    public void afterLogin(){
        CommonMethods.logout();
    }

    @After("@InvalidEmailSignup")
    public void goToLogin(){
        CommonMethods.goLogin();
    }
}
