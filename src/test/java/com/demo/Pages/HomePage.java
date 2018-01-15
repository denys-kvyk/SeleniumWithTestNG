package com.demo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;


public class HomePage {
    WebDriver driver;
    private By departureTextElement = By.id("departureCity");
    private By arrivalTextElement = By.id("arrivalCity");
    private By btnSubmit =By.cssSelector(".sb-col.sb-span-3-t.sb-span-3-d");
    private By checkBox = By.className("sb-checkbox");
    private static Logger logger = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterQuery(String strDeparture,String strArrival) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            logger.info("On the home page");
            driver.findElement(departureTextElement).sendKeys(strDeparture);

            driver.findElement(arrivalTextElement).sendKeys(strArrival);

            driver.findElement(checkBox).click();

            wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
            driver.findElement(btnSubmit).click();
            logger.info("Wait for Result page");

        } catch (Exception exception) {
            logger.error("Exception in the home page:" + exception);
        }
    }
}

