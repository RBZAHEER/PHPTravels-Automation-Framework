package org.phptravels.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.phptravels.utils.ConfigUtility;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {

        String browser = ConfigUtility.getProperties("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else {

            throw new RuntimeException("Browser not found in config file");
        }

        driver.manage().window().maximize();
        driver.get(ConfigUtility.getProperties("url"));
    }

    public void tearDown() {
        driver.quit();
    }
}