package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.TimePage;
import hooks.hooks;

public class TimePageSteps {

    private TimePage timePage;

    @And("I navigate to the Time page")
    public void iNavigateToTheTimePage() {
        timePage = new TimePage(hooks.getDriver());
        timePage.goToTimePage();
        Assert.assertTrue(timePage.isAt(), "Not on Time page");
    }

    @When("I enter employee name {string} in the timesheet search field")
    public void iEnterEmployeeNameInTheTimesheetSearchField(String employeeName) {
        timePage.enterEmployeeName(employeeName);
    }

    @And("I click the view button")
    public void iClickTheViewButton() {
        timePage.clickViewButton();
    }

    @Then("the timesheet for {string} should be displayed")
    public void theTimesheetForShouldBeDisplayed(String employeeName) {
        Assert.assertTrue(timePage.isTimesheetDisplayedFor(employeeName), "Timesheet not displayed for " + employeeName);
    }

    @When("I click the {string} button")
    public void iClickTheButton(String buttonName) {
        timePage.clickButtonByName(buttonName);
    }

    @Then("my timesheet should be displayed")
    public void myTimesheetShouldBeDisplayed() {
        Assert.assertTrue(timePage.isMyTimesheetDisplayed(), "My timesheet is not displayed");
    }
}