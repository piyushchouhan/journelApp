package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String API_KEY;

    private static final String API_URL = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public WeatherResponse getWeather(String city) {
        String final_api =  API_URL.replace("API_KEY", API_KEY).replace("CITY", city);
        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(final_api, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse weatherResponse = exchange.getBody();
        return weatherResponse;
    }
}
