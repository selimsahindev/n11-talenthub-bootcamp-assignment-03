# 🌞 🌧 ⛈️ 🌦️ 🌈 Weather API - Patika.dev Homework 03

This project is a Spring Boot application that provides weather information for cities using the weatherstack.com API. It allows users to retrieve current weather data for a specific city, with the capability to cache the data in an H2 database for 30 minutes to reduce external API calls.

**Author:** [**selimsahindev**](https://github.com/selimsahindev/)

## Features
- **Current Weather Retrieval:** Users can fetch the current weather information for a specific city by making GET requests to the `/api/v1/weather/{city}` endpoint.

- **Rate Limiting:** The application is rate-limited using **Resilience4j**, allowing only 10 requests per minute to prevent abuse.

- **Caching:** Weather data fetched from the external API is cached in an H2 database. If a request is made within 30 minutes of the last update, cached data is returned instead of making another API call.

- **Dockerized Deployment:** The application can be easily deployed using Docker. A docker-compose.yml file is provided to spin up multiple replicas of the service.

## Usage

1. **Prerequisites:**
   - JDK 21
   - Docker and Docker Compose (optional for running with Docker)

2. **Clone the Repository:**
   ```bash
   git clone https://github.com/selimsahindev/n11-talenthub-bootcamp-assignment-03.git
   cd n11-talenthub-bootcamp-assignment-03
   ```

3. **Configure API Key:**
   Obtain an API key from [weatherstack.com](https://weatherstack.com/) and create a readme.md file in the same directory with the docker-compose.yml file and add
  ```API_KEY=<your_api_key>``` property to your newly created .env file.

4. **Run with Maven:**
   ```bash
   mvn spring-boot:run
   ```

6. **Access API:**
   Once the application is running, you can access the API endpoints. For example:
   ```bash
   curl http://localhost:8080/api/v1/weather/london
   ```

7. **Docker Deployment:**
   If you prefer Docker, you can deploy the application using Docker Compose:
   ```bash
   docker-compose up
   ```
   - This command will start multiple replicas of the service using ports 8080-8082.

8. **Additional Information:**
   - Rate limiting configurations can be adjusted in `application.properties`.
   - Database configurations can also be modified in `application.properties`.

# Entities

### [**Weather**](src/main/java/com/selimsahin/homework03/entity/Weather.java) Entity:

| Field              | Description                         |
|--------------------|-------------------------------------|
| `id`               | Unique identifier                   |
| `requestedCityName`| Name of the requested city          |
| `cityName`         | Name of the city                    |
| `country`          | Country of the city                 |
| `temperature`      | Current temperature in the city     |
| `updatedAt`        | Last update time                    |
| `responseLocalTime`| Local time of the weather response  |

# Services

### [**WeatherService**](src/main/java/com/selimsahin/homework03/service/WeatherService.java)

| Method                                      | Description                                                            |
|---------------------------------------------|------------------------------------------------------------------------|
| getWeatherByCityName(String city)           | Retrieves the current weather information for a specific city.         |


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

