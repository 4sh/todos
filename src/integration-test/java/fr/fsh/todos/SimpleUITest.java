package fr.fsh.todos;

import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author fcamblor
 */
@RunWith(value = Parameterized.class)
public class SimpleUITest extends SeleneseTestBase {

    private WebDriver driver = null;
    private DesiredCapabilities testCapability;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
                //{ DesiredCapabilities.chrome() },
                //{ DesiredCapabilities.iphone() },
                { DesiredCapabilities.internetExplorer() },
                { DesiredCapabilities.firefox() }
        });
    }

    public SimpleUITest(DesiredCapabilities cap){
        this.testCapability = cap;
    }

    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
        super.checkForVerificationErrors();
    }

    @Test
    public void shouldSelectAndUnselectChangeTitleClass() throws MalformedURLException {

        this.driver = WebDriverTestHelper.createWebDriver("Selecting & unselecting radio button", this.testCapability);

        // Asserting initial state
        assertThat(driver.findElements(By.cssSelector("tr.task")).size(), is(equalTo(3)));
        assertThat(driver.findElements(By.cssSelector("tr.task.done")).size(), is(equalTo(0)));

        driver.findElement(By.cssSelector("tr#task3 > td.title")).click();
        driver.findElement(By.cssSelector("tr#task2 > td.title")).click();
        driver.findElement(By.cssSelector("tr#task1 > td.title")).click();

        driver.findElement(By.cssSelector("#checkTask")).click();

        assertThat(driver.findElements(By.cssSelector("tr.task.done")).size(), is(equalTo(1)));
        assertThat(driver.findElements(By.cssSelector("tr#task1.done")).size(), is(equalTo(1)));

        driver.findElement(By.cssSelector("#checkTask")).click();

        assertThat(driver.findElements(By.cssSelector("tr.task.done")).size(), is(equalTo(0)));

        System.out.println(driver.getTitle());
    }

    @Test
    public void shouldAddTaskButtonEffectivelyAddsANewTask() throws MalformedURLException {

        this.driver = WebDriverTestHelper.createWebDriver("Selecting & unselecting radio button", this.testCapability);

        assertThat(driver.findElements(By.cssSelector("tr.task")).size(), is(equalTo(3)));
        assertThat(driver.findElements(By.cssSelector("tr.task.done")).size(), is(equalTo(0)));

        assertThat(driver.findElements(By.cssSelector("tr#task4")).size(), is(equalTo(0)));

        driver.findElement(By.cssSelector("#newTaskTitle")).sendKeys("My task 4");
        driver.findElement(By.cssSelector("#newTask")).click();

        assertThat(driver.findElements(By.cssSelector("tr.task")).size(), is(equalTo(4)));
        assertThat(driver.findElements(By.cssSelector("tr#task4")).size(), is(equalTo(1)));
        assertThat(driver.findElement(By.cssSelector("tr#task4 > td.title")).getText(), is(equalTo("My task 4")));

        System.out.println(driver.getTitle());
    }

    @Test
    public void shouldSelectTaskNotDoneDisplayUncheckedCheckbox() throws MalformedURLException {
        driver = WebDriverTestHelper.createWebDriver("Select task not done", this.testCapability);
        Selenium selenium = new WebDriverBackedSelenium(driver, WebDriverTestHelper.getBaseUrl());

        // copy / pasted code goes here
        // when in state



        // Pour tests...
        selenium.open("/");
        selenium.waitForPageToLoad("60000");

        // Switch & select task 3
        selenium.click("xpath=//tr[@id='task3']//td[@class='title']");
        verifyFalse(selenium.isChecked("id=checkTask"));
        selenium.click("id=checkTask");
        verifyTrue(selenium.isChecked("id=checkTask"));

        // Switch to task 2 : checkbox should not be selected
        selenium.click("xpath=//tr[@id='task2']//td[@class='title']");
        verifyFalse(selenium.isChecked("id=checkTask"));

        // Back to task 3 : checkbox should be selected !
        selenium.click("xpath=//tr[@id='task3']//td[@class='title']");
        verifyTrue(selenium.isChecked("id=checkTask"));

        System.out.println(driver.getTitle());
    }
    
}