package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    //Locators

    @FindBy(xpath = "//img[contains(@alt,'client brand logo')]")
    private WebElement profileLogo;

    @FindBy(xpath = "//span[contains(@class,'oxd-userdropdown-tab')]")
    private WebElement profileDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutButton;



    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    LoginPage loginPage = new LoginPage(driver);
    public void logoutUser(){

        profileLogo.isDisplayed();
        profileDropdown.click();
        logoutButton.click();
        loginPage.userIsOnLoginPage();
    }

}
