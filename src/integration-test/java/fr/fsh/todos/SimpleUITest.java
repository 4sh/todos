package fr.fsh.todos;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
public class SimpleUITest {

    private WebDriver driver = null;
    private DesiredCapabilities testCapability;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
                { DesiredCapabilities.chrome() },
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
}
