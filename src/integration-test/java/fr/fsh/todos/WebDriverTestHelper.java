package fr.fsh.todos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author fcamblor
 */
public class WebDriverTestHelper {
    public static WebDriver createWebDriver(String testName, DesiredCapabilities templateCapability) throws MalformedURLException {
        String driverImpl = System.getProperty("integTest.driver", "remoteWebDriver");
        String driverParam = System.getProperty("integTest.driver.param");

        // Overriding current testCapability name to log
        // desired permutation for current test
        DesiredCapabilities capability = new DesiredCapabilities(templateCapability);
        capability.setCapability("name", testName + " (" + capability.toString() + ")");

        WebDriver driver = null;
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

        initialGet(driver);
        return driver;
    }

    public static DesiredCapabilities createDesiredCapabilitiesFor(String browser){
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

    public static void initialGet(WebDriver driver) {
        String baseUrl = System.getProperty("integTest.baseUrl", "http://qa.todos.4sh.fr/");
        driver.get(baseUrl);
    }
}
