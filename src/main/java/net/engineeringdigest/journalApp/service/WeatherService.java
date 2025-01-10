package net.engineeringdigest.journalApp.service;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholder;
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

    @Autowired
    AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse1 = redisService.get("Weather_of_" + city, WeatherResponse.class);
        if(weatherResponse1 != null) {
            return weatherResponse1;
        }else {
            String api = appCache.APP_CACHE.get(AppCache.keys.weather_api.toString());
            String final_api =  appCache.APP_CACHE.get(AppCache.keys.weather_api.toString()).replace(Placeholder.API_KEY, API_KEY).replace(Placeholder.CITY, city);
            ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(final_api, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse weatherResponse = exchange.getBody();
            if(weatherResponse != null) {
                redisService.set("Weather_of_" + city, weatherResponse, 300L);
            }
            return weatherResponse;
        }
    }
}
