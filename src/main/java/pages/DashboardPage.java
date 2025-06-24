package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    //Locators

    @FindBy(xpath = "//img[contains(@alt,'client brand logo')]")
    private WebElement profileLogo;

    @FindBy(xpath = "//span[contains(@class,'oxd-userdropdown-tab')]")
    private WebElement profileDropdown;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;



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

    public void clickPIMMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

}
