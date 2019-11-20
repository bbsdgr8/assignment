package weather.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weather.model.CityWeather;
import weather.repository.CityWeatherRepository;
import weather.util.DateUtil;

@Service
public class CityWeatherRepositoryServiceImpl implements CityWeatherRepositoryService {

	@Autowired
	CityWeatherRepository cityWeatherRepository;
	
	@Override
	public CityWeather findByCityAndDate(String city, String date) {
		return cityWeatherRepository.findByCityAndDate(city, date);
	}

	@Override
	public void deleteByCityAndDate(String city, String date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCityWeather(CityWeather cityWeather, String cityKey, String dateToday) {		
		cityWeather.setDate(dateToday);
	    cityWeather.setCity(cityKey);
		cityWeatherRepository.save(cityWeather);		
	}

}
