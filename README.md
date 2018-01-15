# SeleniumWithTestNG
# Task :
1. Download project from GitHub https://github.com/harithah/SeleniumWithTestNG
2. Import project into your fav IDE (e.g. Intellij IDEA)
3. Get familiar with Java and TestNG
4. in Result page write methods for 'Cheapest', 'Fastest' tab.
5. Write test (in TestNG) for find 1 trip in Cheapest, 1 in Fastest, and Cheapest among Fastest results  
  
# WITHOUT PATTERN!

# Created class Trip :
Сontains the price and trip time

# Added methods :  
goToCheapestTab()

goToFastestTab() 


goToTrainMode() 

goToAirMode() 

goToBusMode() 
    
    
trainTableResult() 

planeTableResult() 

busTableResult() 
     
  getPriceOfTrip(WebElement mode) -- should be a universal method for finding prices according to the transport tab(is not used in test )
   
getPriceOfTrainTrip() 

getPriceOfPlaneTrip() 

getPriceOfBusTrip() 
   
   getHoursOfTrip(WebElement mode) -- should be a universal method for finding hours according to the transport tab(is not used in test ) 
getHoursOfTrainTrip() 

getHoursOfPlaneTrip() 

getHoursOfBusTrip() 
     
   returnFirstTripObject(List priceOfTrip, List hourOfTrip, WebElement tableResult) -- should be a universal method for finding trip according to the transport tab(is not used in test )  
   
returnFirstTrainTripObject() 
      
   getFastestsTrip(List priceOfTrip, List hourOfTrip, WebElement tableResult) -- should be a universal method for finding trip according to the transport tab(is not used in test )  
   
getFastestsTrainTrip() 

   returnCheapestOfTheFastestTrip(List priceOfTrip, List hourOfTrip, WebElement tableResult) -- should be a universal method for finding trip according to the transport tab(is not used in test )  
   
returnCheapestOfTheFastestTrainTrip() 
