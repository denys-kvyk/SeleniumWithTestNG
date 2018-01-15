package com.demo.sort_test;

import com.demo.Pages.HomePage;
import com.demo.Pages.ResultsPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class SortingTest {

    WebDriver driver;
    HomePage homePage;
    ResultsPage resultsPage;
    public static Logger logger = Logger.getLogger(HomePage.class);

    @BeforeMethod
    public void setUp() {
        driver = WebDriverManager.startDriver();
        logger.info("Starting the driver");
        homePage = new HomePage(this.driver);
        resultsPage = new ResultsPage(this.driver);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.stopDriver(driver);
    }

    @DataProvider
    public Object[][] getData(){
        Object[][] queryData=new Object[1][2];
        queryData[0][0]="Berlin";
        queryData[0][1]="Prague";
        return queryData;
    }

    @Test(dataProvider="getData")
    public void verifyIfThePriceOfFirstCheapestTrainTripNotNull(String strDeparture, String strArrival) throws InterruptedException {
        logger.info("verifyIfThePriceOfFirstCheapestTrainTripNotNull test Starts");
        enterSearch(strDeparture,strArrival);
        verifyThePriceOfFirstCheapestTrainTrip();
        logger.info("verifyIfThePriceOfFirstCheapestTrainTripNotNull test Finished");
    }

    @Test(dataProvider="getData")
    public void verifyIfThePriceOfFirstFastestTrainTripNotNull(String strDeparture, String strArrival) throws InterruptedException {
        logger.info("verifyIfThePriceOfFirstFastestTrainTripNotNull test Starts");
        enterSearch(strDeparture,strArrival);
        verifyThePriceOfFirstFastestTrainTrip();
        logger.info("verifyIfThePriceOfFirstFastestTrainTripNotNull test Finished");
    }

    @Test(dataProvider="getData")
    public void verifyIfThePriceOfCheapestFromFastestTripNotNull(String strDeparture, String strArrival) throws InterruptedException {
        logger.info("verifyIfThePriceOfCheapestFromFastestTripNotNull test Starts");
        enterSearch(strDeparture,strArrival);
        verifyThePriceOfCheapestFromFastestTrip();
        logger.info("verifyIfThePriceOfCheapestFromFastestTripNotNull test Finished");
    }



    private void verifyThePriceOfFirstCheapestTrainTrip() {
        resultsPage.goToTrainMode();
        resultsPage.goToCheapestTab();
        Assert.assertNotEquals(resultsPage.returnFirstTrainTripObject().getPrice(), null);
    }
    private void verifyThePriceOfFirstFastestTrainTrip() {
        resultsPage.goToTrainMode();
        resultsPage.goToFastestTab();
        Assert.assertNotEquals(resultsPage.returnFirstTrainTripObject().getPrice(), null);
    }
    private void verifyThePriceOfCheapestFromFastestTrip() {
        resultsPage.goToTrainMode();
        resultsPage.goToFastestTab();
        Assert.assertNotEquals(resultsPage.returnCheapestOfTheFastestTrainTrip().get(0).getPrice(), null);
    }



    private void enterSearch(String strDeparture,String strArrival) {
        homePage.enterQuery(strDeparture,strArrival);
        Assert.assertEquals(resultsPage.waitForResultsPage(), "GoEuro");
    }

    private void verifyAndLog(String strTravelMode) throws InterruptedException {
        boolean blnVerifyPriceAscending = resultsPage.verifyIfPriceArrangedAscending();
        logger.info("Price in ascending order for "+strTravelMode+" is asserted to be: " + blnVerifyPriceAscending);
        Assert.assertTrue(blnVerifyPriceAscending,"Price not found in ascending order for "+strTravelMode);
    }

}
