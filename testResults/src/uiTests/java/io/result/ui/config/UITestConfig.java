package io.result.ui.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = {"io.result"})
@PropertySource("classpath:application-uiTests.properties")
public class UITestConfig {

    @Value("${url}")
    private String url;

    @Value("${browser}")
    private String browser;

    @Value("${default_implicit_wait}")
    private int default_implicit_wait;

    @Bean
    public WebDriver driver() {
        WebDriver driver;

        switch (browser) {
            case "chrome":
                String opSystem = System.getProperty("os.name");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized", "--ignore-certificate-errors");
                setUpChromeDriverAccordingToOS(opSystem, options);

                driver = new ChromeDriver(options);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new AssertionError("Browser " + browser + " is not supported yet. ");
        }
        driver.manage().timeouts().implicitlyWait(default_implicit_wait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    private void setUpChromeDriverAccordingToOS(String opSystem, ChromeOptions options) {
        if (opSystem.contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "src/uiTests/resources/chromedriver");
        } else if (opSystem.contains("Linux")) {
            options.addArguments("disable-gpu");
            options.addArguments("no-sandbox");
            System.setProperty("webdriver.chrome.driver", "src/uiTests/resources/chromedriver_linux");
        } else if (opSystem.contains("Win")) {
            System.setProperty("webdriver.chrome.driver", "src/uiTests/resources/chromedriver.exe");
        } else {
            throw new AssertionError("Your system is: " + opSystem + ", no chrome driver for it");
        }
    }
}
