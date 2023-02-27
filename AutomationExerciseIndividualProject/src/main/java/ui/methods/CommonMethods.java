package ui.methods;

import ui.PageTransporter;
import ui.automation.components.TopBarMenu;
import ui.automation.pages.HomeAutomationPage;
import ui.automation.pages.HomeProductsAutomationPage;
import utils.LoggerManager;

public class CommonMethods {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final PageTransporter pageTransporter = PageTransporter.getInstance();
    private static TopBarMenu topBarMenu;


    public static void logout() {
        topBarMenu = new TopBarMenu();
        topBarMenu.logout();
    }

    public static void goLogin(){
        topBarMenu = new TopBarMenu();
        topBarMenu.goToLoginButton();
    }
}
