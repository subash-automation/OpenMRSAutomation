package businessFlow;

import entity.Users;
import enums.LoginPageEnum;
import org.openqa.selenium.support.PageFactory;
import page.AbstractBasePage;
import page.DashboardPage;
import page.LoginPage;

import static core.DriverManager.getDriver;

public class LoginBL {

    private void LoginBL() {
    }

    static LoginBL loginBL;

    /**
     * Singleton instance
     * @return
     */
    public static LoginBL getInstance() {
        if (loginBL == null) {
            loginBL = new LoginBL();
        }
        return loginBL;
    }

    /**
     * Login with username and password
     * @param users
     */
    public DashboardPage loginWithCreds(Users users){
        LoginPage loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        loginPage.fillFields(LoginPageEnum.USERNAME, users.getUserName(), true);
        loginPage.fillFields(LoginPageEnum.PASSWORD, users.getPassword(), true);
        loginPage.clickOnElement(LoginPageEnum.LOCATION, users.getLocation().toLowerCase());
        loginPage.clickOnElement(LoginPageEnum.LOGIN_BTN);

        return PageFactory.initElements(getDriver(), DashboardPage.class);
    }


}
