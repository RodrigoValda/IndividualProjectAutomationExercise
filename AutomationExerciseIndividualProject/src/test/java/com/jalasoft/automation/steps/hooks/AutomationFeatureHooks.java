package com.jalasoft.automation.steps.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import ui.controller.UIController;
import ui.methods.CommonMethods;
import utils.LoggerManager;

public class AutomationFeatureHooks {

    private final UIController controller;

    public AutomationFeatureHooks(UIController controller) {
        this.controller = controller;
    }


    @After()
    private void afterLogin(){
        CommonMethods.logout();
    }

}
