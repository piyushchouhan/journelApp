package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{
    public Request request;
    public Current current;

    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        public String observationTime;
        public int temperature;
        @JsonProperty("weather_code")
        public int weatherCode;
        public int feelslike;
        public int uv_index;
        public int visibility;
        public String is_day;
    }

    @Getter
    @Setter
    public class Request{
        public String type;
        public String query;
        public String language;
        public String unit;
    }
}

