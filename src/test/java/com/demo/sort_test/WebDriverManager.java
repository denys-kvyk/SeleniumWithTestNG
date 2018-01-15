package com.demo.sort_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.demo.Pages.HomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static String url="http://www.goeuro.com/";
    public static Logger logger = Logger.getLogger(HomePage.class);

    public static WebDriver startDriver() {
        logger.info("Waiting for start chrome driver ");
        System.setProperty("webdriver.chrome.driver",
                "src\\test\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);

        driver.get("http://www.goeuro.com");
        return driver;
    }

    public static void startBrowser(WebDriver driver) {
        driver.get(getUrl());
        //driver.manage().window().maximize();
    }

    public static String getUrl() {
        return url;
    }

    public static void stopDriver(WebDriver driver) {
        driver.quit();
    }
}
