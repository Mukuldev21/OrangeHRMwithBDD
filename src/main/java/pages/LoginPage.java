package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {

    //Locators

    @FindBy(xpath = "//input[contains(@name,'username')]")
    private WebElement usernameField;

    @FindBy(xpath = "//input[contains(@name,'password')]")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement loginButton;

    @FindBy(xpath = "//img[contains(@alt,'client brand logo')]")
    private WebElement orangeHRMLogo;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    private WebElement errorMessage;

    @FindBy(xpath = "//p[text()='Forgot your password? ']")
    private WebElement forgotYourPasswordButton;

    @FindBy(xpath = "//h6[text()='Reset Password']")
    //@FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']")
    private WebElement resetPasswordHeader;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameInputOnResetPasswordPage;

    @FindBy(xpath = "//button[text()=' Reset Password ']")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//h6[text()='Reset Password link sent successfully']")
    private WebElement resetPasswordLinkMessage;

    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement loginHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {

        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {

        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickLoginButton() {

        loginButton.click();
    }

    public void clickOnForgotYourPassword(){

        wait.until(ExpectedConditions.visibilityOf(forgotYourPasswordButton));
        forgotYourPasswordButton.click();
    }

    public void enterUsernameOnResetPasswordPage() {

        wait.until(ExpectedConditions.visibilityOf(usernameInputOnResetPasswordPage)).sendKeys("John Wick");

    }

    public void resetPasswordMessageIsDisplayed() {

        wait.until(ExpectedConditions.visibilityOf(resetPasswordHeader));
        resetPasswordHeader.isDisplayed();
    }
    public void clickOnResetPassword() {

        wait.until(ExpectedConditions.visibilityOf(resetPasswordButton)).click();

    }



    public void loginWithValidCredentials(String username, String password){

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();


    }

    public void loggedInSuccessfully() {
        orangeHRMLogo.isDisplayed();
    }

    public void loginWithInvalidCredentials(String invalidUsername, String invalidPassword){

        enterUsername(invalidUsername);
        enterPassword(invalidPassword);
        clickLoginButton();

    }



    public void resetPassword(){


        wait.until(ExpectedConditions.visibilityOf(resetPasswordLinkMessage)).isDisplayed();
        Assert.assertTrue(resetPasswordLinkMessage.isDisplayed(), "Reset password link message is not displayed");
    }

    public void userIsOnLoginPage(){
        wait.until(ExpectedConditions.visibilityOf(loginHeader)).isDisplayed();
        Assert.assertTrue(loginHeader.isDisplayed(), "User is not on the login page");
    }

    public void errorMessageIsDisplayed() {
        errorMessage.isDisplayed();
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
    }
}
