package weather.service;

import weather.model.CityWeather;

public interface CityWeatherRepositoryService {
	 CityWeather findByCityAndDate(String city, String date);
	 void deleteByCityAndDate(String city, String date);
	 void createCityWeather(CityWeather cityWeather, String cityKey, String dateToday);
}
