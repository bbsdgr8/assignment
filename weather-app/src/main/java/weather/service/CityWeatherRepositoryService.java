package weather.service;

import java.util.Date;

import weather.model.CityWeather;

public interface CityWeatherRepositoryService {
	 CityWeather findByCityAndDate(String city, Date date);
	 void deleteByDate(Date date);
	 void createCityWeather(CityWeather cityWeather, String cityKey, Date dateToday);
}
