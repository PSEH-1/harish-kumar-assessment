This is Harish kumar's (9980927696) assignment on weather forecase.

Code compiling:
>.\mvnw.cmd clean

Code running:
>.\java -jar target\assessment-0.0.1-SNAPSHOT.jar

Rest URLs:
1. http://localhost:8080/forecast/{city}/{countrycode}/prediction - returns the prediction for next 3 days whether you carry an umbrella or be ready for hot sun.
2. http://localhost:8080/forecast/{city}/{countrycode}/highTemperature - gives high Temperature for next 3 days.
3. http://localhost:8080/forecast/{city}/{countrycode}/lowTemperature - gives low temperature for next 3 days.

Example urls:
1. http://localhost:8080/forecast/Mumbai/IN/prediction - returns the prediction for next 3 days whether you carry an umbrella or be ready for hot sun.
2. http://localhost:8080/forecast/Mumbai/IN/highTemperature - gives high Temperature for next 3 days.
3. http://localhost:8080/forecast/Mumbai/IN/lowTemperature - gives low temperature for next 3 days.