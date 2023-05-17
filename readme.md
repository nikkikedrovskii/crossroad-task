# Crossroad task
## The application simulates the operation of a crossroad.

## Input data: 
northTraffic - The number of cars passing on the north road per minute(Can't be 0).  
southTraffic - The number of cars passing on the south road per minute(Can't be 0).  
westTraffic - The number of cars passing on the west road per minute(Can't be 0).  
eastTraffic - The number of cars passing on the east road per minute(Can't be 0).  
trafficDelay - The time it takes the car to leave the crossroad.  

northSouthTrafficLightDuration - How many seconds the traffic light on the north-south direction is green.  
westEastTrafficLightDuration - How many seconds the traffic light on the west-east direction is green.  

durationOfSimulation - The time interval in seconds that is simulated.  

## Description:
Before running the program, specify the desired values in the configuration.properties file.  
At start, 1 car is generated in each direction and arrives at the intersection at zero seconds.  
These cars will leave the intersection after the time specified in the configuration.properties(trafficDelay).  
After the car leaves the intersection the next car is generated.  
The dwell time of these machines is - (the time when the previous car left the crossroad + time the method getExponentialRandomVariable(double trafficRate) generates. Where trafficRate is the number of cars that can pass in a minute)  

Each interval the traffic light is checked and if the trafficLightSegmentDuration variable is equal to the value specified in the configuration   
(northSouthTrafficLightDuration or westEastTrafficLightDuration - depending on which traffic light is green) the traffic light is switched.  

## The car is not able to pass the intersection in case:
1) A red light is on in the direction of the car.  
2) If the time until the traffic light is switched is less than the time it takes the car to cross the intersection (trafficDelay)  
In this case a new departureCarEvent is created with time equal to (currentTime +1). The process will stop when the car is allowed to pass.  

After the car leaves the counter of the Road class is increased by 1.  
The number of cars that have traveled on each road will be displayed in the console after the simulation ends.  


## Example:

### Input data:   

northTraffic=30  
southTraffic=5  
westTraffic=8  
eastTraffic=5  
northSouthTrafficLightDuration=20  
westEastTrafficLightDuration=12  
trafficDelay=2  
durationOfSimulation=60  

### Output data:  
North count car - 12  
South count car - 6  
West count car - 7  
East count car - 2
