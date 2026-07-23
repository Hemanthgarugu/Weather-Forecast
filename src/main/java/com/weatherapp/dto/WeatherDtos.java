package com.weatherapp.dto;

import java.util.List;

public class WeatherDtos {

    public static class WeatherResponseDto {
        private String city;
        private String country;
        private Double temp;
        private Double feelsLike;
        private Double tempMin;
        private Double tempMax;
        private Integer humidity;
        private Integer pressure;
        private Double windSpeed;
        private Integer windDegree;
        private String condition;
        private String description;
        private String icon;
        private Integer uvIndex;
        private Integer airQualityIndex;
        private Long visibility;
        private Long sunrise;
        private Long sunset;
        private Double lat;
        private Double lon;
        private List<ForecastDayDto> forecast;

        public WeatherResponseDto() {}

        public WeatherResponseDto(String city, String country, Double temp, Double feelsLike, Double tempMin, Double tempMax, Integer humidity, Integer pressure, Double windSpeed, Integer windDegree, String condition, String description, String icon, Integer uvIndex, Integer airQualityIndex, Long visibility, Long sunrise, Long sunset, Double lat, Double lon, List<ForecastDayDto> forecast) {
            this.city = city;
            this.country = country;
            this.temp = temp;
            this.feelsLike = feelsLike;
            this.tempMin = tempMin;
            this.tempMax = tempMax;
            this.humidity = humidity;
            this.pressure = pressure;
            this.windSpeed = windSpeed;
            this.windDegree = windDegree;
            this.condition = condition;
            this.description = description;
            this.icon = icon;
            this.uvIndex = uvIndex;
            this.airQualityIndex = airQualityIndex;
            this.visibility = visibility;
            this.sunrise = sunrise;
            this.sunset = sunset;
            this.lat = lat;
            this.lon = lon;
            this.forecast = forecast;
        }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }

        public Double getTemp() { return temp; }
        public void setTemp(Double temp) { this.temp = temp; }

        public Double getFeelsLike() { return feelsLike; }
        public void setFeelsLike(Double feelsLike) { this.feelsLike = feelsLike; }

        public Double getTempMin() { return tempMin; }
        public void setTempMin(Double tempMin) { this.tempMin = tempMin; }

        public Double getTempMax() { return tempMax; }
        public void setTempMax(Double tempMax) { this.tempMax = tempMax; }

        public Integer getHumidity() { return humidity; }
        public void setHumidity(Integer humidity) { this.humidity = humidity; }

        public Integer getPressure() { return pressure; }
        public void setPressure(Integer pressure) { this.pressure = pressure; }

        public Double getWindSpeed() { return windSpeed; }
        public void setWindSpeed(Double windSpeed) { this.windSpeed = windSpeed; }

        public Integer getWindDegree() { return windDegree; }
        public void setWindDegree(Integer windDegree) { this.windDegree = windDegree; }

        public String getCondition() { return condition; }
        public void setCondition(String condition) { this.condition = condition; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }

        public Integer getUvIndex() { return uvIndex; }
        public void setUvIndex(Integer uvIndex) { this.uvIndex = uvIndex; }

        public Integer getAirQualityIndex() { return airQualityIndex; }
        public void setAirQualityIndex(Integer airQualityIndex) { this.airQualityIndex = airQualityIndex; }

        public Long getVisibility() { return visibility; }
        public void setVisibility(Long visibility) { this.visibility = visibility; }

        public Long getSunrise() { return sunrise; }
        public void setSunrise(Long sunrise) { this.sunrise = sunrise; }

        public Long getSunset() { return sunset; }
        public void setSunset(Long sunset) { this.sunset = sunset; }

        public Double getLat() { return lat; }
        public void setLat(Double lat) { this.lat = lat; }

        public Double getLon() { return lon; }
        public void setLon(Double lon) { this.lon = lon; }

        public List<ForecastDayDto> getForecast() { return forecast; }
        public void setForecast(List<ForecastDayDto> forecast) { this.forecast = forecast; }

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private String city;
            private String country;
            private Double temp;
            private Double feelsLike;
            private Double tempMin;
            private Double tempMax;
            private Integer humidity;
            private Integer pressure;
            private Double windSpeed;
            private Integer windDegree;
            private String condition;
            private String description;
            private String icon;
            private Integer uvIndex;
            private Integer airQualityIndex;
            private Long visibility;
            private Long sunrise;
            private Long sunset;
            private Double lat;
            private Double lon;
            private List<ForecastDayDto> forecast;

            public Builder city(String city) { this.city = city; return this; }
            public Builder country(String country) { this.country = country; return this; }
            public Builder temp(Double temp) { this.temp = temp; return this; }
            public Builder feelsLike(Double feelsLike) { this.feelsLike = feelsLike; return this; }
            public Builder tempMin(Double tempMin) { this.tempMin = tempMin; return this; }
            public Builder tempMax(Double tempMax) { this.tempMax = tempMax; return this; }
            public Builder humidity(Integer humidity) { this.humidity = humidity; return this; }
            public Builder pressure(Integer pressure) { this.pressure = pressure; return this; }
            public Builder windSpeed(Double windSpeed) { this.windSpeed = windSpeed; return this; }
            public Builder windDegree(Integer windDegree) { this.windDegree = windDegree; return this; }
            public Builder condition(String condition) { this.condition = condition; return this; }
            public Builder description(String description) { this.description = description; return this; }
            public Builder icon(String icon) { this.icon = icon; return this; }
            public Builder uvIndex(Integer uvIndex) { this.uvIndex = uvIndex; return this; }
            public Builder airQualityIndex(Integer airQualityIndex) { this.airQualityIndex = airQualityIndex; return this; }
            public Builder visibility(Long visibility) { this.visibility = visibility; return this; }
            public Builder sunrise(Long sunrise) { this.sunrise = sunrise; return this; }
            public Builder sunset(Long sunset) { this.sunset = sunset; return this; }
            public Builder lat(Double lat) { this.lat = lat; return this; }
            public Builder lon(Double lon) { this.lon = lon; return this; }
            public Builder forecast(List<ForecastDayDto> forecast) { this.forecast = forecast; return this; }

            public WeatherResponseDto build() {
                return new WeatherResponseDto(city, country, temp, feelsLike, tempMin, tempMax, humidity, pressure, windSpeed, windDegree, condition, description, icon, uvIndex, airQualityIndex, visibility, sunrise, sunset, lat, lon, forecast);
            }
        }
    }

    public static class ForecastDayDto {
        private String date;
        private String dayOfWeek;
        private Double tempMin;
        private Double tempMax;
        private String condition;
        private String icon;
        private Integer pop;

        public ForecastDayDto() {}

        public ForecastDayDto(String date, String dayOfWeek, Double tempMin, Double tempMax, String condition, String icon, Integer pop) {
            this.date = date;
            this.dayOfWeek = dayOfWeek;
            this.tempMin = tempMin;
            this.tempMax = tempMax;
            this.condition = condition;
            this.icon = icon;
            this.pop = pop;
        }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public String getDayOfWeek() { return dayOfWeek; }
        public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

        public Double getTempMin() { return tempMin; }
        public void setTempMin(Double tempMin) { this.tempMin = tempMin; }

        public Double getTempMax() { return tempMax; }
        public void setTempMax(Double tempMax) { this.tempMax = tempMax; }

        public String getCondition() { return condition; }
        public void setCondition(String condition) { this.condition = condition; }

        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }

        public Integer getPop() { return pop; }
        public void setPop(Integer pop) { this.pop = pop; }

        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private String date;
            private String dayOfWeek;
            private Double tempMin;
            private Double tempMax;
            private String condition;
            private String icon;
            private Integer pop;

            public Builder date(String date) { this.date = date; return this; }
            public Builder dayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; return this; }
            public Builder tempMin(Double tempMin) { this.tempMin = tempMin; return this; }
            public Builder tempMax(Double tempMax) { this.tempMax = tempMax; return this; }
            public Builder condition(String condition) { this.condition = condition; return this; }
            public Builder icon(String icon) { this.icon = icon; return this; }
            public Builder pop(Integer pop) { this.pop = pop; return this; }

            public ForecastDayDto build() {
                return new ForecastDayDto(date, dayOfWeek, tempMin, tempMax, condition, icon, pop);
            }
        }
    }
}
