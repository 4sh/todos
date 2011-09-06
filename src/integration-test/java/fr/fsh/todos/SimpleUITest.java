package fr.fsh.todos;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author fcamblor
 */
@RunWith(value = Parameterized.class)
public class SimpleUITest {

    private WebDriver driver;
    private DesiredCapabilities testCapability;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
                //{ DesiredCapabilities.chrome() },
                //{ DesiredCapabilities.iphone() },
                //{ DesiredCapabilities.internetExplorer() },
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

    protected void createWebDriver(String testName) throws MalformedURLException {
        String driverImpl = System.getProperty("integTest.driver", "remoteWebDriver");
        String driverParam = System.getProperty("integTest.driver.param");

        // Overriding current testCapability name to log
        // desired permutation for current test
        DesiredCapabilities capability = new DesiredCapabilities(this.testCapability);
        capability.setCapability("name", testName + " (" + capability.toString() + ")");

        if("remoteWebDriver".equalsIgnoreCase(driverImpl)){
            // Should we need driverImplParam everytime ???
            if(driverParam == null){
                throw new IllegalArgumentException("When using remoteWebDriver, you should set a integTest.driver.param property with your remote driver API key !");
            }
            driver = new RemoteWebDriver(
                    new URL(driverParam),
                    capability);
        } else if("htmlUnitWebDriver".equalsIgnoreCase(driverImpl)){
            driver = new HtmlUnitDriver(true);
        } else {
            throw new IllegalArgumentException("Unknown integTest.driver property value : "+driverImpl);
        }
    }

    protected DesiredCapabilities createDesiredCapabilitiesFor(String browser){
        if("firefox".equalsIgnoreCase(browser)){
            return DesiredCapabilities.firefox();
        } else if("chrome".equalsIgnoreCase(browser)){
            return DesiredCapabilities.chrome();
        } else if("android".equalsIgnoreCase(browser)){
            return DesiredCapabilities.android();
        } else if("htmlUnit".equalsIgnoreCase(browser)){
            return DesiredCapabilities.htmlUnit();
        } else if("internetExplorer".equalsIgnoreCase(browser)){
            return DesiredCapabilities.internetExplorer();
        } else if("iphone".equalsIgnoreCase(browser)){
            return DesiredCapabilities.iphone();
        } else if("htmlUnitWithJavascript".equalsIgnoreCase(browser)){
            return DesiredCapabilities.htmlUnitWithJavascript();
        } else if("opera".equalsIgnoreCase(browser)){
            return DesiredCapabilities.opera();
        } else {
            throw new IllegalArgumentException("Unknown browser : "+browser);
        }
    }

    private void initialGet() {
        String baseUrl = System.getProperty("integTest.baseUrl", "http://qa.todos.4sh.fr/");
        driver.get(baseUrl);
    }

    @Test
    public void shouldSelectAndUnselectChangeTitleClass() throws MalformedURLException {

        createWebDriver("Selecting & unselecting radio button");
        initialGet();

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

        createWebDriver("Adding new task");
        initialGet();

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
