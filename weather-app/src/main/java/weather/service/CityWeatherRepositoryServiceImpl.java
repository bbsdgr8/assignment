package weather.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weather.model.CityWeather;
import weather.repository.CityWeatherRepository;

@Service
public class CityWeatherRepositoryServiceImpl implements CityWeatherRepositoryService {

	@Autowired
	CityWeatherRepository cityWeatherRepository;
	
	@Override
	public CityWeather findByCityAndDate(String city, Date date) {
		return cityWeatherRepository.findByCityAndDate(city, date);
	}

	@Override
	public void createCityWeather(CityWeather cityWeather, String cityKey, Date dateToday) {		
		cityWeather.setDate(dateToday);
	    cityWeather.setCity(cityKey);
		cityWeatherRepository.save(cityWeather);		
	}

	@Override
	public void deleteByDate(Date date) {
		cityWeatherRepository.deleteByDate(date);
	}

}
