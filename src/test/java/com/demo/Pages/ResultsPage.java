package com.demo.Pages;
import java.util.ArrayList;
import java.util.List;

import com.demo.models.Trip;
//import com.sun.tools.javac.util.Convert;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.lang.Thread.sleep;

public class ResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private double doubleInitialPrice;
    //my selectors
    private By elements = By.xpath("//*[@class='Result__journeyWrapper___19rVi']");
    private By tableResult = By.xpath("//*[@class='Results__tabsBody___2LwJ4']/div");
    private By tripPrice = By.xpath(".//*[@class='Result__priceContainer___387uI']");
    private By tripHours = By.xpath(".//*[@class='Journey__duration___3p4ct']");
    private By cheapestTab=By.xpath("//span[@data-key='dw.sorting.price']");
    private By fastestTab=By.xpath("//span[@data-key='dw.sorting.traveltime']");
    //my selectors
    private By tranportModeElement = By.cssSelector("li a[class*=ResultTabs__]");
    private By element = By.cssSelector("div[class*=Paging__pagingContainer___289JA] div[class*=Paging__cell] >span[data-key='dw.paging.next']");
    private By tabResults = By.className("Results__rightBar___3Ates");
    private By labelPrice = By.cssSelector("div[class*=Results__tabsBody] div[class*=Result__result__] span[data-test='price']");
    private By tabAlternativeResults = By.cssSelector("div[class*=Results__alternativeResultsDivider]");

    private static Logger logger = Logger.getLogger(ResultsPage.class);


    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public String waitForResultsPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabResults));
        logger.info("Result page");
        logger.info(driver.getTitle());
        return driver.getTitle();
    }

    private boolean comparePrice() {

        List<WebElement> resultsTab =
                driver.findElements(labelPrice);
        for (WebElement result : resultsTab) {
            String strPrice = result.getText().replaceAll("€", "").replaceAll(",", "");
            double doublePrice = Double.parseDouble(strPrice);
            if(driver.findElements(tabAlternativeResults).size()!=0){
                doubleInitialPrice=0.0;
            }
            if (doublePrice < doubleInitialPrice) {
                logger.info("Price found in decending order");
                return false;
            } else {
                doubleInitialPrice = doublePrice;
            }
        }
        return true;
    }

    private boolean clickNextAndContinueCompare() throws InterruptedException {
        boolean blnAscendingOrder = true;

        while (driver.findElements(element).size() != 0) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Actions action = new Actions(driver);
            sleep(1000);
            action.moveToElement(driver.findElement(element)).click().perform();
            logger.info("Clicking NEXT button");
            wait.until(ExpectedConditions.visibilityOfElementLocated(tabResults));
//            comparePrice();
            if (!comparePrice()){
                return false;
            }
        }
        return true;
    }

    public boolean verifyIfPriceArrangedAscending() throws InterruptedException {
        boolean blnAscendingOrder;
        doubleInitialPrice = 0.0;
        blnAscendingOrder = comparePrice();
        if (!blnAscendingOrder) {
            return false;
        }
        blnAscendingOrder = clickNextAndContinueCompare();
        return blnAscendingOrder;
    }

    public void goToCheapestTab(){
        driver.findElement(cheapestTab).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        logger.info("Cheapest Tab");
    }

    public void goToFastestTab() {
        driver.findElement(fastestTab).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Fastest Tab");
    }


    public void goToTrainMode() {
        driver.findElements(tranportModeElement).get(0).click();
        logger.info("Train mode");
    }

    public void goToAirMode() {
        driver.findElements(tranportModeElement).get(1).click();
        logger.info("Air mode");
    }

    public void goToBusMode() {
        driver.findElements(tranportModeElement).get(2).click();
        logger.info("Bus mode");
    }

    private WebElement trainTableResult() {
        return driver.findElements(tableResult).get(1);
    }

    private WebElement planeTableResult() {
        return driver.findElements(tableResult).get(3);
    }

    private WebElement busTableResult() {
        return driver.findElements(tableResult).get(2);
    }

    public List<String> getPriceOfTrip(WebElement mode) {
        List<String> result = new ArrayList();
        for (WebElement current : mode.findElements(tripPrice)) {
            result.add(current.getText());
        }
        return result;
    }

    public List<String> getPriceOfTrainTrip() {
        List<String> result = new ArrayList();
        for (WebElement current : trainTableResult().findElements(tripPrice)) {
            result.add(current.getText());
        }
        return result;
    }

    public List<String> getPriceOfPlaneTrip() {
        List<String> result = new ArrayList();
        for (WebElement current : planeTableResult().findElements(tripPrice)) {
            result.add(current.getText());
        }
        return result;
    }

    public List<String> getPriceOfBusTrip() {
        List<String> result = new ArrayList();
        for (WebElement current : busTableResult().findElements(tripPrice)) {
            result.add(current.getText());
             System.out.print(result);
        }
        return result;
    }

    public List<String> getHoursOfTrip(WebElement mode) {
        List<String> result = new ArrayList();
        for (WebElement current : mode.findElements(tripHours)) {
            result.add(current.getText());
        }
        return result;
    }

    public List<String> getHoursOfTrainTrip() {
        List<String> result = new ArrayList();
        for (WebElement current : trainTableResult().findElements(tripHours)) {
            result.add(current.getText());
        }
        return result;
    }

    public List<String> getHoursOfPlaneTrip() {
        List<String> result = new ArrayList();
        for (WebElement current : planeTableResult().findElements(tripHours)) {
            result.add(current.getText());
        }
        return result;
    }

    public List<String> getHoursOfBusTrip() {
        List<String> result = new ArrayList();
        for (WebElement current : busTableResult().findElements(tripHours)) {
            result.add(current.getText());
        }
        return result;
    }

    public Trip returnFirstTripObject(List priceOfTrip, List hourOfTrip, WebElement tableResult) {
        List price = priceOfTrip;
        List hour = hourOfTrip;
        Trip trip=new Trip();

        for (WebElement current : tableResult.findElements(elements)) {
            String fondPrice = current.findElement(tripPrice).getText();
            String fondHours = current.findElement(tripHours).getText();
            if(fondPrice.equals(price.get(0)) && fondHours.equals(hour.get(0))  )
            {
                String strPrice = fondPrice.replaceAll("€", "").replaceAll(",", "");
                trip.setPrice(Double.parseDouble(strPrice));
                trip.setHour(fondHours);
            }
        }
        logger.info(trip);
        return trip;
    }

    public Trip returnFirstTrainTripObject() {
        List price = getPriceOfTrainTrip();
        List hour = getHoursOfTrainTrip();
        Trip trip=new Trip();

        for (WebElement current : trainTableResult().findElements(elements)) {
           String fondPrice = current.findElement(tripPrice).getText();
            String fondHours = current.findElement(tripHours).getText();
            if(fondPrice.equals(price.get(0)) && fondHours.equals(hour.get(0))  )
            {
                String strPrice = fondPrice.replaceAll("€", "").replaceAll(",", "");
            trip.setPrice(Double.parseDouble(strPrice));
            trip.setHour(fondHours);
            }
        }
           logger.info(trip);
    return trip;
    }

    public List<Trip> getFastestsTrip(List priceOfTrip, List hourOfTrip, WebElement tableResult) {
        List<Trip> fastest = new ArrayList<Trip>();
        List price = priceOfTrip;
        List hour = hourOfTrip;

        for (WebElement current : tableResult.findElements(elements)) {

            String fondPrice = current.findElement(tripPrice).getText();
            String fondHours = current.findElement(tripHours).getText();
            if( fondHours.equals(hour.get(0))  )
            { Trip trip=new Trip();
                String strPrice = fondPrice.replaceAll("€", "").replaceAll(",", "");
                trip.setPrice(Double.parseDouble(strPrice));
                trip.setHour(fondHours);
                fastest.add(trip);
            }
        }
        return fastest;
    }

    public List<Trip> getFastestsTrainTrip() {
        List<Trip> fastest = new ArrayList<Trip>();
        List price = getPriceOfTrainTrip();
        List hour = getHoursOfTrainTrip();

        for (WebElement current : trainTableResult().findElements(elements)) {

            String fondPrice = current.findElement(tripPrice).getText();
            String fondHours = current.findElement(tripHours).getText();
            if( fondHours.equals(hour.get(0))  )
            { Trip trip=new Trip();
                String strPrice = fondPrice.replaceAll("€", "").replaceAll(",", "");
                trip.setPrice(Double.parseDouble(strPrice));
                trip.setHour(fondHours);
                fastest.add(trip);
            }
        }
        return fastest;
    }

    public List<Trip> returnCheapestOfTheFastestTrip(List priceOfTrip, List hourOfTrip, WebElement tableResult) {
        double min=getFastestsTrip(priceOfTrip, hourOfTrip, tableResult).get(0).getPrice();
        int size  =getFastestsTrip(priceOfTrip, hourOfTrip, tableResult).size();
        for (int i=1;i<size;i++) {
            if(getFastestsTrip(priceOfTrip, hourOfTrip, tableResult).get(i).getPrice()<=min)
                min=getFastestsTrip(priceOfTrip, hourOfTrip, tableResult).get(i).getPrice();

        }
        List<Trip> cheapest = new ArrayList<Trip>();
        for(Trip element : getFastestsTrip(priceOfTrip, hourOfTrip, tableResult))
        {if(element.getPrice()==min){
            cheapest.add(element);
            logger.info(element);
        }
        }
        return cheapest;
    }

    public List<Trip> returnCheapestOfTheFastestTrainTrip() {
        double min=getFastestsTrainTrip().get(0).getPrice();
        for (int i=1;i<getFastestsTrainTrip().size();i++) {
            if(getFastestsTrainTrip().get(i).getPrice()<=min)
                min=getFastestsTrainTrip().get(i).getPrice();

        }
        List<Trip> cheapest = new ArrayList<Trip>();
        for(Trip element : getFastestsTrainTrip())
        {if(element.getPrice()==min){
            cheapest.add(element);
            logger.info(element);
        }
        }
        return cheapest;
    }

}
